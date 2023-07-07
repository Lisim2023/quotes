
-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
 `id`           varchar(32)         NOT NULL,
 `name`         varchar(100)        NOT NULL        COMMENT '字典名称',
 `code`         varchar(100)        NOT NULL        COMMENT '字典特征码',
 `description`  varchar(255)        DEFAULT NULL    COMMENT '描述',
 `enabled`      int                 DEFAULT 1       COMMENT '是否启用(0: 否 1:是)',
 `created_by`   varchar(32)         DEFAULT NULL    COMMENT '创建人',
 `created_at`   datetime            DEFAULT NULL    COMMENT '创建时间',
 `updated_by`   varchar(32)         DEFAULT NULL    COMMENT '更新人',
 `updated_at`   datetime            DEFAULT NULL    COMMENT '更新时间',
 `del_flag`     int                 DEFAULT 0       COMMENT '删除状态',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('246cb0b513aaf40dd30d7167e6705e0c', '政治面貌', 'politic', NULL, 1, 'a5f6799fff84251a585385d85ce00fc5', '2022-09-28 11:35:09', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('3486f32803bb953e7155dab3513dc68b', '删除状态', 'del_flag', NULL, 1, 'a5f6799fff84251a585385d85ce00fc5', '2019-01-18 21:46:26', 'a5f6799fff84251a585385d85ce00fc5', '2019-03-30 11:17:11', 0);
INSERT INTO `sys_dict` VALUES ('3d9a351be3436fbefb1307d4cfb49bf2', '性别', 'sex', NULL, 1, 'a5f6799fff84251a585385d85ce00fc5', '2019-01-04 14:56:32', 'a5f6799fff84251a585385d85ce00fc5', '2019-03-30 11:28:27', 0);
INSERT INTO `sys_dict` VALUES ('4148eea8b2ae080f123ae46492d04c91', '民族', 'nation', NULL, 1, 'a5f6799fff84251a585385d85ce00fc5', '2022-09-23 16:03:26', 'a5f6799fff84251a585385d85ce00fc5', '2022-09-25 10:10:43', 0);
INSERT INTO `sys_dict` VALUES ('496d935245ad8161df6aeaa80e750fcf', '爱好', 'hobbies', NULL, 1, 'a5f6799fff84251a585385d85ce00fc5', '2023-06-01 16:05:18', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('4984b6d72c5cfe761f48afa6eae626d8', '文化程度', 'edu_level', NULL, 1, 'a5f6799fff84251a585385d85ce00fc5', '2022-09-28 10:07:36', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('a7adbcd86c37f7dbc9b66945c82ef9e6', '是否', 'yn', '1是0否', 1, 'a5f6799fff84251a585385d85ce00fc5', '2019-05-22 19:29:29', NULL, NULL, 0);
INSERT INTO `sys_dict` VALUES ('ac2f7c0c5c5775fcea7e2387bcb22f01', '菜单类型', 'menu_type', NULL, 1, 'a5f6799fff84251a585385d85ce00fc5', '2020-12-18 23:24:32', 'a5f6799fff84251a585385d85ce00fc5', '2019-04-01 15:27:06', 0);
INSERT INTO `sys_dict` VALUES ('c5ea96ffd9aa396a73b08593b80b0632', '婚姻状况', 'marital_status', NULL, 1, 'a5f6799fff84251a585385d85ce00fc5', '2022-09-28 11:42:21', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id`          varchar(32)     NOT NULL,
  `dict_id`     varchar(32)     NOT NULL        COMMENT '字典id',
  `dict_code`   varchar(255)    DEFAULT NULL    COMMENT '字典编码',
  `text`        varchar(100)    NOT NULL        COMMENT '字典项文本',
  `value`       varchar(100)    NOT NULL        COMMENT '字典项值',
  `description` varchar(255)    DEFAULT NULL    COMMENT '描述',
  `order_num`   int             DEFAULT NULL    COMMENT '排序',
  `enabled`     int             DEFAULT 0       COMMENT '是否启用(0: 否 1:是)',
  `created_at`  datetime        DEFAULT NULL    COMMENT '创建时间',
  `created_by`  varchar(32)     DEFAULT NULL    COMMENT '创建人',
  `updated_at`  datetime        DEFAULT NULL    COMMENT '更新时间',
  `updated_by`  varchar(32)     DEFAULT NULL    COMMENT '更新人',
  `del_flag`    int             DEFAULT 0       COMMENT '删除状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('0241e47040ae4572c8048ea0ee5db829', '4148eea8b2ae080f123ae46492d04c91', 'nation', '柯尔克孜族', '28', '', 28, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('05a2e732ce7b00aa52141ecc3e330b4e', '3486f32803bb953e7155dab3513dc68b', 'del_flag', '已删除', '1', NULL, NULL, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('09bc8df97c430c3c39ddb6bfc97e3884', '246cb0b513aaf40dd30d7167e6705e0c', 'politic', '致公党党员', '9', '', 9, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('09d7a10e1fecfd4b44b2f38cd56d2704', '4148eea8b2ae080f123ae46492d04c91', 'nation', '纳西族', '26', '', 26, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('0ad3af1b3525c9f12b2c2b258092443f', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '收藏', '15', NULL, 15, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('0c1b31948b229e4bebf4b6ca0a914f34', '4148eea8b2ae080f123ae46492d04c91', 'nation', '壮族', '9', '', 9, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('1099b7d223dd5594e92eb950c70e8a9d', '4148eea8b2ae080f123ae46492d04c91', 'nation', '撒拉族', '34', '', 34, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('18094bac06f7178ef60c9b66934b66df', '246cb0b513aaf40dd30d7167e6705e0c', 'politic', '共青团员', '3', '', 3, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('1ab2f70879dd81ef3bb599a51864b506', '4148eea8b2ae080f123ae46492d04c91', 'nation', '黎族', '18', '', 18, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('1b568a9dc081c0b3fcacd112e8ac613a', '246cb0b513aaf40dd30d7167e6705e0c', 'politic', '无党派人士', '12', '', 12, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('1ce32da410761eccd55153ede4de6aef', '246cb0b513aaf40dd30d7167e6705e0c', 'politic', '台盟盟员', '11', '', 11, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('1cf9183d7c5a2c1c68b747fa01cc0159', '4148eea8b2ae080f123ae46492d04c91', 'nation', '基诺族', '56', '', 56, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('1f00d39ef89b4f51089f5a2c966d17ca', '4148eea8b2ae080f123ae46492d04c91', 'nation', '京族', '49', '', 49, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('1f7e721a105e5f9f56e02a47834c3142', '4148eea8b2ae080f123ae46492d04c91', 'nation', '景颇族', '27', '', 27, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('2101c520551bb75df792bf4e7f091b3c', '246cb0b513aaf40dd30d7167e6705e0c', 'politic', '中共党员', '1', '', 1, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('21c4998cc2ba98ef12308e5e1c61186d', '4148eea8b2ae080f123ae46492d04c91', 'nation', '回族', '4', '', 4, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('22fc68494eefcdea113c49a4deb50f86', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '举重', '8', NULL, 8, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('24b4cdb7480760980e149ab1d5a46214', '4148eea8b2ae080f123ae46492d04c91', 'nation', '仡佬族', '36', '', 36, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('25868f2b9d7bde5c6b6eb89c7d9c247d', '4148eea8b2ae080f123ae46492d04c91', 'nation', '苗族', '7', '', 7, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('275bf17567fe30cec8918c7f6db75d21', '4148eea8b2ae080f123ae46492d04c91', 'nation', '拉祜族', '23', '(音：护)', 23, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('299b93e136e031f31b2d112ef8b0c1db', '246cb0b513aaf40dd30d7167e6705e0c', 'politic', '民革党员', '4', '', 4, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('2aacd585b27d482e5ef9dcddfd1b1d88', '4984b6d72c5cfe761f48afa6eae626d8', 'edu_level', '硕士研究生', '60', '', 7, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('2c9d1fd87a7f9dcccc99d772c8092fa7', 'c5ea96ffd9aa396a73b08593b80b0632', 'marital_status', '离异', '3', '', 3, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('308b556c7bf3be58cfd3d5bf49d0f138', '4148eea8b2ae080f123ae46492d04c91', 'nation', '珞巴族', '55', '', 55, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('317637f35ff8eb1824021426e0c2914c', '4148eea8b2ae080f123ae46492d04c91', 'nation', '毛南族', '35', '', 35, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('33a784f45fe019493bef034946e929b3', '4148eea8b2ae080f123ae46492d04c91', 'nation', '塔吉克族', '41', '', 41, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('36514b8de32b4f4cf33e5367b174c49d', '4148eea8b2ae080f123ae46492d04c91', 'nation', '羌族', '32', '', 32, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('36e61f7d625ce5d45a6e7661187041da', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '羽毛球', '2', NULL, 2, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('36fe0ec20d968cfae92654a85de5dca5', '246cb0b513aaf40dd30d7167e6705e0c', 'politic', '民建会员', '6', '', 6, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('3723cf0b57f2c12db066e6a3596aa42c', '4148eea8b2ae080f123ae46492d04c91', 'nation', '仫佬族', '31', '(音：目)', 31, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('37d2e5a6d4d23b99983cdf712990346e', '4148eea8b2ae080f123ae46492d04c91', 'nation', '塔塔尔族', '50', '', 50, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('42664578153b904826cd2223c5d60e57', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '篮球', '1', NULL, 1, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('43624e2d0b4c8ca6e87054ece24e1f8e', '4148eea8b2ae080f123ae46492d04c91', 'nation', '阿昌族', '38', '', 38, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('460fffe38c6857437fda436cdf6ce161', '4148eea8b2ae080f123ae46492d04c91', 'nation', '普米族', '39', '', 39, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('4816e3cbd8435af5fb5b7eb92c1f6df8', '4148eea8b2ae080f123ae46492d04c91', 'nation', '布依族', '10', '', 10, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('51222413e5906cdaf160bb5c86fb827c', 'a7adbcd86c37f7dbc9b66945c82ef9e6', 'yn', '是', '1', '', 1, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('5410390f7efc217dea0592da3541135f', '4148eea8b2ae080f123ae46492d04c91', 'nation', '白族', '13', '', 13, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('551ae16db65c6eb7bcce74b2f995682f', '4148eea8b2ae080f123ae46492d04c91', 'nation', '裕固族', '48', '', 48, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('57880884ba3184cdf74fc31988949db6', 'c5ea96ffd9aa396a73b08593b80b0632', 'marital_status', '未婚', '1', '', 1, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('59ec0483fb12b3ab373be27acff2be05', '0a2b24536bbcbfa40cd341d76b77ca21', NULL, '设置选项', '4', '', 4, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('5b0247a5a0c67083210c9a3bb730e929', '4984b6d72c5cfe761f48afa6eae626d8', 'edu_level', '大专', '40', '', 5, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('5ca11b58ccfd585744f2701c2f997e39', '4984b6d72c5cfe761f48afa6eae626d8', 'edu_level', '初中', '20', '', 2, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('5d84a8634c8fdfe96275385075b105c9', '3d9a351be3436fbefb1307d4cfb49bf2', 'sex', '女', '2', NULL, 2, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('5daea23222a4f50a8b26461da163cee6', '4148eea8b2ae080f123ae46492d04c91', 'nation', '怒族', '42', '', 42, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('60ca3706906f34318f1566431bd5066d', '4148eea8b2ae080f123ae46492d04c91', 'nation', '锡伯族', '37', '', 37, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('6373bcccf65928ff87e990939338eb1b', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '音乐', '9', NULL, 9, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('6572ce76f1a47aaae326a3bbc5fb0f60', '4984b6d72c5cfe761f48afa6eae626d8', 'edu_level', '硕士', '65', '', 8, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('660d2cfeb309815c7995630faa7294b3', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '小说', '13', NULL, 13, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('66223135d0d574da0334799051359541', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '积木', '14', NULL, 14, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('665909d91c5ec4b8ab60944fbc70c75c', '4148eea8b2ae080f123ae46492d04c91', 'nation', '鄂温克族', '45', '', 45, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('66729a1555e7034cb4eccff34bb735ad', '4148eea8b2ae080f123ae46492d04c91', 'nation', '傣族', '17', '', 17, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('70ebc876a45707a1f5c2db683486a85a', '4148eea8b2ae080f123ae46492d04c91', 'nation', '高山族', '22', '', 22, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('7596c5df1c4fef89f34393da7605b95a', '4148eea8b2ae080f123ae46492d04c91', 'nation', '俄罗斯族', '44', '', 44, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('7ccf7b80c70ee002eceb3116854b75cb', 'ac2f7c0c5c5775fcea7e2387bcb22f01', 'menu_type', '按钮', '2', NULL, 3, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('7f6144102641ad570fa949b7901bf029', '246cb0b513aaf40dd30d7167e6705e0c', 'politic', '农工党党员', '8', '', 8, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('82f7ce49ece3bf06b42a625f95c385eb', '4148eea8b2ae080f123ae46492d04c91', 'nation', '彝族', '8', '', 8, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('8522f4f2910677084e19b23f0a33fb3c', '4148eea8b2ae080f123ae46492d04c91', 'nation', '藏族', '5', '', 5, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('86b3a24ae2d56daa680b593afbdb3fe5', '4148eea8b2ae080f123ae46492d04c91', 'nation', '瑶族', '12', '', 12, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('86f19c7e0a73a0bae451021ac05b99dd', 'ac2f7c0c5c5775fcea7e2387bcb22f01', 'menu_type', '菜单', '1', NULL, 2, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('8854143da67057eeaf0f61f0d2049b90', '4148eea8b2ae080f123ae46492d04c91', 'nation', '东乡族', '25', '', 25, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('88ed5812c0e6a3b2005407c3af845ff6', '4148eea8b2ae080f123ae46492d04c91', 'nation', '汉族', '1', '', 1, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('8976ff2b6358130de81d0d58e6da5df7', '4148eea8b2ae080f123ae46492d04c91', 'nation', '畲族', '21', '', 21, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('897c43e354df30227929cc1f059e8620', '246cb0b513aaf40dd30d7167e6705e0c', 'politic', '群众(普通居民)', '13', '', 13, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('8b491bbc4667c487c0ac7616fd6d56e3', '4148eea8b2ae080f123ae46492d04c91', 'nation', '佤族', '20', '', 20, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('8bf9326e9f88934c3762407546f55874', '246cb0b513aaf40dd30d7167e6705e0c', 'politic', '民进会员', '7', '', 7, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('8fa398901d72c8854fbde1aef2a07b23', '4148eea8b2ae080f123ae46492d04c91', 'nation', '乌孜别克族', '43', '', 43, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('932bb3b3ede3771069702635b1f68d81', '4984b6d72c5cfe761f48afa6eae626d8', 'edu_level', '本科', '50', '', 6, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('93b36ff733922ac3e6020cacbb5dd8da', '4148eea8b2ae080f123ae46492d04c91', 'nation', '土家族', '14', '', 14, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('98c1085929b25c361e36c3a54daf5a5c', '4148eea8b2ae080f123ae46492d04c91', 'nation', '赫哲族', '53', '', 53, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('9926e7273e2299303aca656275b5a6ee', '4984b6d72c5cfe761f48afa6eae626d8', 'edu_level', '高中', '30', '', 3, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('9927edb87170a457583d1936fa436f15', 'c5ea96ffd9aa396a73b08593b80b0632', 'marital_status', '已婚', '2', '', 2, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('9a46d441fe474e9cb54479ca9670b1aa', '4148eea8b2ae080f123ae46492d04c91', 'nation', '保安族', '47', '', 47, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('9ffdcaad7a2c8efedaa79e1f18fa2374', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '绘画', '12', NULL, 12, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('a0ac774af85cc7e343ea7a3e0cff0dfd', '4984b6d72c5cfe761f48afa6eae626d8', 'edu_level', '小学', '10', '', 1, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('a1b16f62bef9ff49d40e9fd0b8a3ce26', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '跑步', '6', NULL, 6, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('a216e4c379b7b8757af59f0378415c58', '4148eea8b2ae080f123ae46492d04c91', 'nation', '独龙族', '51', '', 51, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('a7f1c7a0cceb393d4477baa221d9bcc6', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '乒乓球', '3', NULL, 3, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('aa0d8a8042a18715a17f0a888d360aa4', 'ac2f7c0c5c5775fcea7e2387bcb22f01', 'menu_type', '目录', '0', NULL, 1, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('ac42e02123088394bae2b8b1a10c5a2c', '246cb0b513aaf40dd30d7167e6705e0c', 'politic', '中共预备党员', '2', '', 2, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('ad10335288dd9961ecc76c23c506aede', '4984b6d72c5cfe761f48afa6eae626d8', 'edu_level', '博士后', '80', '', 11, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('af5ed7c5e6af6ea1689adea4b35aeb05', '246cb0b513aaf40dd30d7167e6705e0c', 'politic', '民盟盟员', '5', '', 5, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('b01469a47f91d8c6eaac934eb8d7f7a4', '4148eea8b2ae080f123ae46492d04c91', 'nation', '水族', '24', '', 24, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('b2a8b4bb2c8e66c2c4b1bb086337f393', '3486f32803bb953e7155dab3513dc68b', 'del_flag', '正常', '0', NULL, NULL, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('b6ee6622f7e99be8ee3bc28a382fa98f', '246cb0b513aaf40dd30d7167e6705e0c', 'politic', '九三学社社员', '10', '', 10, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('c5700a71ad08994d18ad1dacc37a71a9', 'a7adbcd86c37f7dbc9b66945c82ef9e6', 'yn', '否', '0', '', 1, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('c5a6b09242c3f791aa18e7bc10c3832d', '4148eea8b2ae080f123ae46492d04c91', 'nation', '朝鲜族', '40', '', 40, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('c6479c22019709849fc1ef3faecf7712', '4148eea8b2ae080f123ae46492d04c91', 'nation', '土族', '29', '', 29, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('cd77650505b35f541156f2951a39bcf1', '4148eea8b2ae080f123ae46492d04c91', 'nation', '德昂族', '46', '', 46, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('d02796e277079e2b6e9f7d011101dde2', '4148eea8b2ae080f123ae46492d04c91', 'nation', '哈尼族', '15', '', 15, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('d04296f35b36b58b7036b55df0c7c7e8', '4148eea8b2ae080f123ae46492d04c91', 'nation', '满族', '2', '', 2, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('d0544ecdd44bc1578dfc633005555c34', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '足球', '4', NULL, 4, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('d26cd6019598504ee87a5381c66774e1', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '舞蹈', '10', NULL, 10, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('d30940ae37c005e8e469c831f579e196', '4148eea8b2ae080f123ae46492d04c91', 'nation', '哈萨克族', '16', '', 16, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('d6025005d70a2e08651333f150455f07', '4148eea8b2ae080f123ae46492d04c91', 'nation', '门巴族', '54', '', 54, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('d675aa3bfeee73ef45f70e2d4bb2d680', '4148eea8b2ae080f123ae46492d04c91', 'nation', '侗族', '11', '', 11, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('da5c83acd7734cf7283559734630a745', '4148eea8b2ae080f123ae46492d04c91', 'nation', '傈僳族', '19', '(音：素)', 19, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('de220c46a012984bc9bd61734f21e0b5', '4148eea8b2ae080f123ae46492d04c91', 'nation', '维吾尔族', '6', '', 6, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('de6f0f895890f68d3e1a59f1b9faaa5e', '4148eea8b2ae080f123ae46492d04c91', 'nation', '蒙古族', '3', '', 3, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('dead6caaf70b9c4cc77b6ec1e85d4e86', '4984b6d72c5cfe761f48afa6eae626d8', 'edu_level', '中专', '35', '', 4, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('df168368dcef46cade2aadd80100d8aa', '3d9a351be3436fbefb1307d4cfb49bf2', 'sex', '男', '1', NULL, 1, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('e7925f092cdfd39b93ce55ddc32c77be', '4984b6d72c5cfe761f48afa6eae626d8', 'edu_level', '博士研究生', '70', '', 9, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('e95cb1fc6d96eb5fca8890ed8712362e', '4148eea8b2ae080f123ae46492d04c91', 'nation', '布朗族', '33', '', 33, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('ec883ccabcbcd381cdd272ff31f178b7', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '跳绳', '7', NULL, 7, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('f1801e0a6435d3c97e49d739e304e20b', '4148eea8b2ae080f123ae46492d04c91', 'nation', '达斡尔族', '30', '(音：握)', 30, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('f2f4e7bcafc3bb279421288da7957893', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '电影', '11', NULL, 11, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('f322ece040c93d0c4dd6559d4cf0d59d', '4984b6d72c5cfe761f48afa6eae626d8', 'edu_level', '博士', '75', '', 10, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('f5ff904342f22757cffe0ef0f930e9c5', 'c5ea96ffd9aa396a73b08593b80b0632', 'marital_status', '丧偶', '4', '', 4, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('f95eb364c819fc69052475606930b0ba', '4148eea8b2ae080f123ae46492d04c91', 'nation', '鄂伦春族', '52', '', 52, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_dict_item` VALUES ('fce067275fb1ac0d6091f531d24de26d', '496d935245ad8161df6aeaa80e750fcf', 'hobbies', '滑板', '5', NULL, 5, 1, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
 `id`           varchar(32)         NOT NULL        COMMENT '菜单编号',
 `parent_id`    varchar(32)         DEFAULT ''      COMMENT '上级菜单编号，一级菜单为\"\"',
 `title`        varchar(64)         DEFAULT NULL    COMMENT '菜单标题',
 `path`         varchar(255)        DEFAULT NULL    COMMENT '路由地址',
 `perms`        varchar(255)        DEFAULT NULL    COMMENT '权限标识',
 `component`    varchar(255)        DEFAULT NULL    COMMENT '组件',
 `type`         int                 DEFAULT NULL    COMMENT '菜单类型  (0:目录 1:菜单 2:按钮)',
 `icon`         varchar(32)         DEFAULT NULL    COMMENT '菜单图标',
 `order_num`    double              DEFAULT NULL    COMMENT '显示顺序',
 `is_cache`     int                 DEFAULT 0       COMMENT '是否缓存 (0:不缓存 1:缓存)',
 `status`       int                 DEFAULT 0       COMMENT '菜单状态 (0:停用 1:启用)',
 `created_at`   datetime            DEFAULT NULL,
 `created_by`   varchar(32)         DEFAULT NULL,
 `updated_at`   datetime            DEFAULT NULL,
 `updated_by`   varchar(32)         DEFAULT NULL,
 `del_flag`     int                 DEFAULT 0,
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0043e674aabd5fea8e3eac56d960d6fc', 'c2afd90e0e70961a26e279e081b96035', '部门管理', 'depart', 'sys:depart:list', 'sys/depart/list', 1, 'depart', 5, 1, 1, '2023-06-17 09:35:12', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('009e0710c9562a415287a38ecf0e603e', '3ac2f973fd6727a40940e0646a65679b', '编辑公告', '', 'sys:notice:edit', NULL, 2, '#', 3, 1, 1, '2023-06-17 09:54:45', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('01d67ca897a30141e93bc4f4d504540e', 'c2afd90e0e70961a26e279e081b96035', '字典管理', 'dict', 'sys:dict:list', 'sys/dict/list', 1, 'dict', 4, 1, 1, '2023-06-17 09:34:31', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('306bdb5c1633646b80cbd5f9be36d406', '901650c35b077958caf535e30327215e', '编辑任务', '', 'tool:job:edit', NULL, 2, '#', 3, 1, 1, '2023-06-17 09:55:59', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('358ead6686df99ff0859d7b46036633a', '5a79b612aa38a102acc0dda21fb5b854', '查询用户', '', 'sys:user:query', NULL, 2, '#', 2, 1, 1, '2023-06-17 09:43:04', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('3765cd66959ca2815f8a5931034c10d7', '0043e674aabd5fea8e3eac56d960d6fc', '新增部门', '', 'sys:depart:add', NULL, 2, '#', 1, 1, 1, '2023-06-17 09:53:21', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('3ac2f973fd6727a40940e0646a65679b', 'c2afd90e0e70961a26e279e081b96035', '通知公告', 'notice', 'sys:notice:list', 'sys/notice/list', 1, 'notice', 6, 1, 1, '2023-06-17 09:35:43', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('3eaf1e2dde83432c10ff28c09bbb195d', 'b09c8aa1df6080d496a42faa02def318', '编辑角色', '', 'sys:role:edit', NULL, 2, '#', 3, 1, 1, '2023-06-17 09:46:14', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('48654f6a65fd22894b832989c9243b63', '6648d2f6f4875d3a9c30cc1bf98e7a22', '菜单查询', '', 'sys:menu:query', NULL, 2, '#', 2, 1, 1, '2023-06-17 09:51:20', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('4e581af48e551fff3e1974452aec010f', '901650c35b077958caf535e30327215e', '删除任务', '', 'tool:job:delete', NULL, 2, '#', 4, 1, 1, '2023-06-17 09:56:10', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('55a965684cb387b2602694493a52c0a8', '01d67ca897a30141e93bc4f4d504540e', '删除字典', '', 'sys:dict:delete', NULL, 2, '#', 4, 1, 1, '2023-06-17 09:52:53', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('5a79b612aa38a102acc0dda21fb5b854', 'c2afd90e0e70961a26e279e081b96035', '用户管理', 'user', 'sys:user:list', 'sys/user/list', 1, 'user', 1, 1, 1, '2023-06-17 09:32:36', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('6648d2f6f4875d3a9c30cc1bf98e7a22', 'c2afd90e0e70961a26e279e081b96035', '菜单管理', 'menu', 'sys:menu:list', 'sys/menu/list', 1, 'menu', 3, 1, 1, '2023-06-17 09:33:35', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('710a63134de985bc214cd0ba72498ce9', '5a79b612aa38a102acc0dda21fb5b854', '新增用户', '', 'sys:user:add', NULL, 2, '#', 1, 1, 1, '2023-06-17 09:42:36', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('7e0506ea34d18de24d33bb95a3077d94', '901650c35b077958caf535e30327215e', '任务查询', '', 'tool:job:query', NULL, 2, '#', 2, 1, 1, '2023-06-17 09:55:46', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('8777c8f919c4d517bf45c5d4ea9b8591', '5a79b612aa38a102acc0dda21fb5b854', '重置密码', '', 'sys:user:resetPassword', NULL, 2, '#', 5, 1, 1, '2023-06-17 09:45:13', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('8ae455e4389e6c697074dd618a623a25', 'd3d2b0ad88745879e7f55f9a689daf39', '日志管理', 'log', 'tool:log:list', NULL, 0, 'log', 2, 1, 1, '2023-06-17 09:39:06', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('8c3f39d6d71871edbc30274fe2bd0e2a', '3ac2f973fd6727a40940e0646a65679b', '公告查询', '', 'sys:notice:query', NULL, 2, '#', 2, 1, 1, '2023-06-17 09:54:35', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('8c439cf1ae5c5a61ec92bcde8db3ff76', '5a79b612aa38a102acc0dda21fb5b854', '删除用户', '', 'sys:user:delete', NULL, 2, '#', 4, 1, 1, '2023-06-17 09:44:18', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('901650c35b077958caf535e30327215e', 'd3d2b0ad88745879e7f55f9a689daf39', '定时任务', 'job', 'tool:job:list', 'tool/job/list', 1, 'job', 1, 1, 1, '2023-06-17 09:38:05', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('9543747ea39edbac01134521ae54378d', 'b09c8aa1df6080d496a42faa02def318', '新增角色', '', 'sys:role:add', NULL, 2, '#', 1, 1, 1, '2023-06-17 09:45:46', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('979edffe57cb7b693d44c17934801fdb', '3ac2f973fd6727a40940e0646a65679b', '删除公告', '', 'sys:notice:delete', NULL, 2, '#', 4, 1, 1, '2023-06-17 09:54:55', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('98ba3afdedad20311c91d267cca1eb37', '01d67ca897a30141e93bc4f4d504540e', '新增字典', '', 'sys:dict:add', NULL, 2, '#', 1, 1, 1, '2023-06-17 09:52:12', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('98c2a36fa0d80f293a65c3ac5044b694', '8ae455e4389e6c697074dd618a623a25', '操作日志', 'operatelog', 'tool:operatelog:list', 'tool/operatelog/list', 1, 'operatelog', 1, 1, 1, '2023-06-17 10:01:02', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('9a32565be350e15d8ff35bf6c6c6280e', '6648d2f6f4875d3a9c30cc1bf98e7a22', '新增菜单', '', 'sys:menu:add', NULL, 2, '#', 1, 1, 1, '2023-06-17 09:50:53', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('abd41465c62118a8065c7da98b517404', '01d67ca897a30141e93bc4f4d504540e', '编辑字典', '', 'sys:dict:edit', NULL, 2, '#', 3, 1, 1, '2023-06-17 09:52:35', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('b09c8aa1df6080d496a42faa02def318', 'c2afd90e0e70961a26e279e081b96035', '角色管理', 'role', 'sys:role:list', 'sys/role/list', 1, 'role', 2, 1, 1, '2023-06-17 09:33:04', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('b452e23fc378067d5439dd9e28c0ea89', 'b09c8aa1df6080d496a42faa02def318', '角色查询', '', 'sys:role:query', NULL, 2, '#', 2, 1, 1, '2023-06-17 09:46:01', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('bd7f011ee05ee240e9ed987d631d7e6c', '01d67ca897a30141e93bc4f4d504540e', '查询字典', '', 'sys:dict:query', NULL, 2, '#', 2, 1, 1, '2023-06-17 09:52:24', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('c2afd90e0e70961a26e279e081b96035', '', '系统管理', 'system', '', NULL, 0, 'system', 1, 1, 1, '2023-06-17 09:29:24', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('c30c84165e01db617f2d240add9e34ff', 'b09c8aa1df6080d496a42faa02def318', '删除角色', '', 'sys:role:delete', NULL, 2, '#', 4, 1, 1, '2023-06-17 09:46:28', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('d305d74ca174e0f4bfdf7caf2941465a', '0043e674aabd5fea8e3eac56d960d6fc', '编辑部门', '', 'sys:depart:edit', NULL, 2, '#', 3, 1, 1, '2023-06-17 09:53:44', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('d3d2b0ad88745879e7f55f9a689daf39', '', '系统工具', 'tool', '', NULL, 0, 'tool', 2, 1, 1, '2023-06-17 09:31:08', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('d7d6b36ac55093b6e7302d888488c815', '8ae455e4389e6c697074dd618a623a25', '登录日志', 'loginlog', 'tool:loginlog:list', 'tool/loginlog/list', 1, 'loginlog', 2, 1, 1, '2023-06-17 10:01:23', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('dffaeda45be3115f1208864cf5f28d6c', '0043e674aabd5fea8e3eac56d960d6fc', '删除部门', '', 'sys:depart:delete', NULL, 2, '#', 4, 1, 1, '2023-06-17 09:53:55', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('e785bda907e5495292cf4a783a9cae37', '6648d2f6f4875d3a9c30cc1bf98e7a22', '删除菜单', '', 'sys:menu:delete', NULL, 2, '#', 4, 1, 1, '2023-06-17 09:51:45', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('ead03f458fc67e2e0b18b7be57b77f9a', '901650c35b077958caf535e30327215e', '新增任务', '', 'tool:job:add', NULL, 2, '#', 1, 1, 1, '2023-06-17 09:55:29', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('ec6a29278edf76c37f538aeb4595e12c', '0043e674aabd5fea8e3eac56d960d6fc', '部门查询', '', 'sys:depart:query', NULL, 2, '#', 2, 1, 1, '2023-06-17 09:53:32', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('f334fff8cfd4e9f8040b18677f24a0f3', '3ac2f973fd6727a40940e0646a65679b', '新增公告', '', 'sys:notice:add', NULL, 2, '#', 1, 1, 1, '2023-06-17 09:54:24', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('f4a4d222824c3e01f4f2380bf041f293', '5a79b612aa38a102acc0dda21fb5b854', '编辑用户', '', 'sys:user:edit', NULL, 2, '#', 3, 1, 1, '2023-06-17 09:43:49', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('f797009a420ba3bdc412f7fdbce2dc78', '6648d2f6f4875d3a9c30cc1bf98e7a22', '编辑菜单', '', 'sys:menu:edit', NULL, 2, '#', 3, 1, 1, '2023-06-17 09:51:34', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
 `id`           varchar(32)         NOT NULL,
 `name`         varchar(64)         DEFAULT NULL,
 `code`         varchar(64)         DEFAULT NULL,
 `remarks`      varchar(64)         DEFAULT NULL        COMMENT '备注',
 `created_at`   datetime            DEFAULT NULL,
 `created_by`   varchar(32)         DEFAULT NULL,
 `updated_at`   datetime            DEFAULT NULL,
 `updated_by`   varchar(32)         DEFAULT NULL,
 `del_flag`     int                 DEFAULT 0,
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('04012bfe83f9b7224069642e5a917094', '系统管理员', 'admin', '', '2023-06-02 14:03:01', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES ('733323e623a5a4879c2744380f43185a', '普通用户', 'normal', '', '2023-06-02 14:02:48', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES ('9f65261417e462f83b49c07dd6956ac3', '三国', 'the Three Kingdoms', '', '2023-06-02 14:03:11', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES ('a0181b37ee5e971969b82efffabb4d26', '孺家', 'Rujia', '', '2023-06-02 14:03:22', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES ('ab583f46bba1407138209288f9e72388', '道家', 'Taoists', '', '2023-06-02 14:03:31', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id`          varchar(32)         NOT NULL,
  `role_id`     varchar(32)         NOT NULL,
  `user_id`     varchar(32)         NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('0091f78956bbc40f298f723717816b06', 'ab583f46bba1407138209288f9e72388', 'd3f07a247471a7d7e7beb48bdc34126b');
INSERT INTO `sys_role_user` VALUES ('0bd23bbe4b4725bd259b1cc85948fb14', '733323e623a5a4879c2744380f43185a', '63e78131dbe70d0f360961326b1b467d');
INSERT INTO `sys_role_user` VALUES ('0edd4b1be19125aaea31e9972c49f4e3', '733323e623a5a4879c2744380f43185a', '229f048fea73473f761cb3682c0a711f');
INSERT INTO `sys_role_user` VALUES ('1824aa1cc52708dca57148b4fb63acf2', '733323e623a5a4879c2744380f43185a', 'a5f6799fff84251a585385d85ce00fc5');
INSERT INTO `sys_role_user` VALUES ('32bef5824513829c982ae2a1abe125ac', '733323e623a5a4879c2744380f43185a', '27234095f394f397efed563d105a8a80');
INSERT INTO `sys_role_user` VALUES ('33c15217364293790d8649063f26cc9c', '733323e623a5a4879c2744380f43185a', 'd3f07a247471a7d7e7beb48bdc34126b');
INSERT INTO `sys_role_user` VALUES ('407a01d8c194032ecb323b301f7cc181', '733323e623a5a4879c2744380f43185a', '1d6592a640d7a05ce044dde0b9815f09');
INSERT INTO `sys_role_user` VALUES ('5262c0f278c146d63a4aa57904c7dbd1', '733323e623a5a4879c2744380f43185a', 'c647f694c9df497727f9680253d07716');
INSERT INTO `sys_role_user` VALUES ('545d0c23ed37b5e28e9923483c6b6b2d', '9f65261417e462f83b49c07dd6956ac3', 'c647f694c9df497727f9680253d07716');
INSERT INTO `sys_role_user` VALUES ('65dfddb1bec1e72f02a353e2d268ee74', '733323e623a5a4879c2744380f43185a', '0c7918437d64e92886ecf0f8a9f7cfe5');
INSERT INTO `sys_role_user` VALUES ('7899d7c1657a2dd5519ec05c4adea7e0', '733323e623a5a4879c2744380f43185a', '2073e5c551d58b4b23d4dccd957605bc');
INSERT INTO `sys_role_user` VALUES ('79a1b63050bd7a872322ede3f2a66cf2', '9f65261417e462f83b49c07dd6956ac3', '2073e5c551d58b4b23d4dccd957605bc');
INSERT INTO `sys_role_user` VALUES ('7fa101d4047280278414d5789c957826', 'a0181b37ee5e971969b82efffabb4d26', '1ff9f3df31960430b4c154e2f6c25e15');
INSERT INTO `sys_role_user` VALUES ('826cfd5693b277ff5361a339a53b3502', '733323e623a5a4879c2744380f43185a', '1ff9f3df31960430b4c154e2f6c25e15');
INSERT INTO `sys_role_user` VALUES ('8ddcfd3c460eaf7a1610cec3c61f5b02', '04012bfe83f9b7224069642e5a917094', 'a5f6799fff84251a585385d85ce00fc5');
INSERT INTO `sys_role_user` VALUES ('934c7b403cbd7e87d7268b77d05c29fd', '733323e623a5a4879c2744380f43185a', 'e70709d34da7e49c29482a174fb046db');
INSERT INTO `sys_role_user` VALUES ('98e676ffc94deb2a73aec4e2d8307f85', '733323e623a5a4879c2744380f43185a', '831195135cd4d58b64fd3e3710b376d4');
INSERT INTO `sys_role_user` VALUES ('be3616d6800a8cdf3c977cddadd4a420', 'a0181b37ee5e971969b82efffabb4d26', '1d6592a640d7a05ce044dde0b9815f09');
INSERT INTO `sys_role_user` VALUES ('e96c8ef0a3941005fe539f51d919e264', '733323e623a5a4879c2744380f43185a', '7269d0bf11bf7921459896ab350c903e');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
 `id`           varchar(32)         NOT NULL,
 `username`     varchar(64)         DEFAULT NULL,
 `nickname`     varchar(64)         DEFAULT NULL,
 `realname`     varchar(64)         DEFAULT NULL,
 `sex`          int                 DEFAULT NULL,
 `password`     varchar(64)         DEFAULT NULL,
 `avatar`       varchar(255)        DEFAULT NULL,
 `email`        varchar(64)         DEFAULT NULL,
 `address`      varchar(64)         DEFAULT NULL,
 `hobbies`      varchar(255)        DEFAULT NULL,
 `created_at`   datetime            DEFAULT NULL,
 `created_by`   varchar(32)         DEFAULT NULL,
 `updated_at`   datetime            DEFAULT NULL,
 `updated_by`   varchar(32)         DEFAULT NULL,
 `del_flag`     int                 DEFAULT 0,
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0c7918437d64e92886ecf0f8a9f7cfe5', 'King Solomon', '所罗门王', NULL, 1, NULL, NULL, NULL, '以色列联合王国', '1,2,3', '2023-06-01 16:35:05', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES ('1d6592a640d7a05ce044dde0b9815f09', 'Mencius', '孟子', NULL, 1, NULL, NULL, NULL, '邹国', '2,5,8', '2023-06-01 16:32:31', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES ('1ff9f3df31960430b4c154e2f6c25e15', 'Confucius', '孔子', NULL, 1, NULL, NULL, NULL, '宋国栗邑', '3,5,10', '2023-06-01 16:32:03', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES ('2073e5c551d58b4b23d4dccd957605bc', 'guanyu', '关羽', NULL, 1, NULL, NULL, NULL, '河东解良', '1', '2023-06-01 16:30:25', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES ('229f048fea73473f761cb3682c0a711f', 'Hannibal Barca', '汉尼拨', NULL, 1, NULL, NULL, NULL, '迦太基', '6,11', '2023-06-01 16:34:36', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES ('27234095f394f397efed563d105a8a80', 'Vasco da Gama', '达迦马', NULL, 1, NULL, NULL, NULL, '葡萄牙锡尼什', '6,14', '2023-06-01 16:36:03', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES ('63e78131dbe70d0f360961326b1b467d', 'Plato', '柏拉图', NULL, 1, NULL, NULL, NULL, '古希腊雅典', '11,13', '2023-06-01 16:34:06', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES ('7269d0bf11bf7921459896ab350c903e', 'Socrates', '苏格拉底', NULL, 1, NULL, NULL, NULL, '古希腊雅典', '5,9', '2023-06-01 16:35:36', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES ('831195135cd4d58b64fd3e3710b376d4', 'Aristotle', '亚里士多德', NULL, 1, NULL, NULL, NULL, '古希腊色雷斯的斯塔基拉', '4,6,12', '2023-06-01 16:36:29', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES ('a5f6799fff84251a585385d85ce00fc5', 'admin', '管理员', NULL, 1, '$2a$10$N8wg4py2iS/BRGmYgPN23.ILMc4qeQScAItt9hUbQqS9A6UAlfQyS', NULL, NULL, '湖南常德', '9,11', '2021-01-12 22:13:53', NULL, '2022-09-06 14:48:57', NULL, 0);
INSERT INTO `sys_user` VALUES ('c647f694c9df497727f9680253d07716', 'caocao', '曹操', NULL, 1, NULL, NULL, NULL, '沛国谯县', '9,10', '2023-06-01 16:31:29', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES ('d3f07a247471a7d7e7beb48bdc34126b', 'Laocius', '老子', NULL, 1, NULL, NULL, NULL, '楚国(或陈国)', '1,3,9', '2023-06-01 16:33:01', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES ('e70709d34da7e49c29482a174fb046db', 'YueFei', '岳飞', NULL, 1, NULL, NULL, NULL, '宋相州汤阴县', '3,4,12', '2023-06-01 16:33:30', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group`  (
   `id`             varchar(32)         NOT NULL,
   `name`           varchar(255)        DEFAULT NULL,
   `code`           varchar(255)        DEFAULT NULL,
   `remarks`        varchar(255)        DEFAULT NULL,
   `members`        varchar(255)        DEFAULT NULL,
   `created_at`     datetime            DEFAULT NULL,
   `created_by`     varchar(32)         DEFAULT NULL,
   `updated_at`     datetime            DEFAULT NULL,
   `updated_by`     varchar(32)         DEFAULT NULL,
   `del_flag`       int                 DEFAULT 0,
   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('0be61660dc9d82e6229550c97440d572', '儒家', 'Rujia', NULL, '1ff9f3df31960430b4c154e2f6c25e15,1d6592a640d7a05ce044dde0b9815f09', '2023-06-02 16:17:11', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `user_group` VALUES ('89b74cb898058e10d924fedc81106cb3', '三国', 'the Three Kingdoms', NULL, 'c647f694c9df497727f9680253d07716,2073e5c551d58b4b23d4dccd957605bc', '2023-06-02 16:19:15', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);
INSERT INTO `user_group` VALUES ('d64109d105ba2de763013d9b9a95cda3', '道家', 'Taoists', NULL, 'd3f07a247471a7d7e7beb48bdc34126b', '2023-06-02 16:18:21', 'a5f6799fff84251a585385d85ce00fc5', NULL, NULL, 0);

