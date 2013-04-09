CREATE TABLE `WEIBO_USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCESS_TOKER` varchar(100) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(50) COLLATE utf8_bin NOT NULL,
  `UID` int(12) DEFAULT NULL,
  `ADD_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='微博用户';


CREATE TABLE `GULF_NEWS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `TITLE` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '文章标题',
  `SUMMARY` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '摘要',
  `CONTENT` text CHARACTER SET latin1 NOT NULL COMMENT '正文',
  `IMG_URL` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '首图URL',
  `ADD_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `STATUS` int(1) DEFAULT '1' COMMENT '状态 1-正常 0-删除',
  `TYPE` int(2) DEFAULT NULL COMMENT '1-日记 2-医生 3-用药 4-进食  5-各种玩意',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文章表';
