# Host: 192.168.1.215  (Version 5.6.26-log)
# Date: 2016-09-19 00:27:35

#
# Structure for table "shiziqiu_conf_node"
#
DROP TABLE IF EXISTS `shiziqiu_conf_node`;
CREATE TABLE `shiziqiu_conf_node` (
  `node_group` varchar(100) NOT NULL COMMENT '∑÷◊È',
  `node_key` varchar(100) NOT NULL COMMENT '≈‰÷√Key',
  `node_value` varchar(512) DEFAULT NULL COMMENT '≈‰÷√Value',
  `node_desc` varchar(100) DEFAULT NULL COMMENT '≈‰÷√ºÚΩÈ',
  UNIQUE KEY `u_group_key` (`node_group`,`node_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "shiziqui_conf_group"
#
DROP TABLE IF EXISTS `shiziqui_conf_group`;
CREATE TABLE `shiziqui_conf_group` (
  `group_name` varchar(100) NOT NULL,
  `group_title` varchar(100) NOT NULL COMMENT '√Ë ˆ',
  PRIMARY KEY (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;