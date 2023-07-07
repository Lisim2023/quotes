package com.github.Lisim2023.quotes.test.config;

import com.github.Lisim2023.quotes.test.base.EntityLogic;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.Properties;

@Component
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MybatisInterceptor implements Interceptor {

    private static final String LOGIN_USER_ID = "a5f6799fff84251a585385d85ce00fc5";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object parameter = invocation.getArgs()[1];
        if (parameter instanceof MapperMethod.ParamMap){
            MapperMethod.ParamMap<?> p = (MapperMethod.ParamMap<?>) parameter;
            if (p.containsKey("et")) {
                parameter = p.get("et");
            } else {
                parameter = p.get("param1");
            }
        }

        if (parameter instanceof EntityLogic){
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
            if (SqlCommandType.INSERT.equals(sqlCommandType)){
                ((EntityLogic) parameter).setCreatedBy(LOGIN_USER_ID);
                ((EntityLogic) parameter).setCreatedAt(new Date());
            }else if (SqlCommandType.UPDATE.equals(sqlCommandType)){
                ((EntityLogic) parameter).setUpdatedBy(LOGIN_USER_ID);
                ((EntityLogic) parameter).setUpdatedAt(new Date());
            }
        }

        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }

}
