package com.github.Lisim2023.quotes.test.common;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Arrays;


public class QueryCondition {

    private static final String FLAG_IN = ",";

    private static final String FLAG_LIKE = "*";

    private static final String FLAG_NE = "!";
    private static final String FLAG_GT = ">";
    private static final String FLAG_GE = ">=";
    private static final String FLAG_LT = "<";
    private static final String FLAG_LE = "<=";


    public static <T> void addCondition(QueryWrapper<T> queryWrapper, String column, String value) {
        if (value.contains(FLAG_IN)) {
            // in
            queryWrapper.in(column, Arrays.asList(value.split(FLAG_IN)));
        }else if (value.startsWith(FLAG_LIKE) && value.endsWith(FLAG_LIKE)){
            // like
            value = value.substring(FLAG_LIKE.length(), value.length() - FLAG_LIKE.length());
            queryWrapper.like(column, value);

        }else if (value.startsWith(FLAG_LIKE)) {
            // like left
            value = value.substring(FLAG_LIKE.length());
            queryWrapper.likeLeft(column, value);

        }else if (value.endsWith(FLAG_LIKE)){
            // like right
            value = value.substring(0, value.length() - FLAG_LIKE.length());
            queryWrapper.likeRight(column, value);

        }else if (value.startsWith(FLAG_NE)) {
            // not equals
            value = value.substring(FLAG_NE.length());
            queryWrapper.ne(column, value);

        }else if (value.startsWith(FLAG_GE)){
            // greater than or equal to
            value = value.substring(FLAG_GE.length());
            queryWrapper.ge(column, value);

        }else if (value.startsWith(FLAG_GT)){
            // greater than
            value = value.substring(FLAG_GT.length());
            queryWrapper.gt(column, value);

        }else if (value.startsWith(FLAG_LE)){
            // less than or equal to
            value = value.substring(FLAG_LE.length());
            queryWrapper.le(column, value);

        }else if (value.startsWith(FLAG_LT)){
            // less than
            value = value.substring(FLAG_LT.length());
            queryWrapper.lt(column, value);

        }else {
            // equals
            queryWrapper.eq(column, value);
        }
    }


    public static String unFlag(String value){
        return value.replace(FLAG_NE, "")
                .replace(FLAG_LE, "")
                .replace(FLAG_LT, "")
                .replace(FLAG_GE, "")
                .replace(FLAG_GT, "")
                .replace(FLAG_LIKE, "");
    }
}
