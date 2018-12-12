
drop table lmzsystem;
CREATE TABLE `lmzsystem` (
  `id` varchar(32) NOT NULL,
  `code` varchar(96) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `mdomain` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `keyjson` text DEFAULT NULL,
  `optjson` text DEFAULT NULL,
  `conjson` text DEFAULT NULL,
  `active` int(11) DEFAULT '0',
  `extjson` text DEFAULT NULL,
  `remark` text DEFAULT NULL,
  `ctBy` varchar(32) DEFAULT 'sys',
  `ctDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `upBy` varchar(32) DEFAULT 'sys',
  `upDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table lmzmenu;
CREATE TABLE `lmzmenu` (
  `id` varchar(32) NOT NULL,
  `code` varchar(96) DEFAULT NULL,
  `name` varchar(96) DEFAULT NULL,
  `img` varchar(32) DEFAULT NULL,
  `pid` varchar(32) NOT NULL,
  `sysId` varchar(32) NOT NULL,
  `active` int(11) DEFAULT '0',
  `lev` int(11) NOT NULL,
  `ord` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `httpMethod` varchar(32) DEFAULT NULL,
  `isOp` int(11) DEFAULT '0',
  `isDev` int(11) DEFAULT '0',
	`extjson` text DEFAULT NULL,
  `ctBy` varchar(32) DEFAULT 'sys',
  `ctDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `upBy` varchar(32) DEFAULT 'sys',
  `upDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table lmzuser;
CREATE TABLE `lmzuser` (
  `id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `userpwd` varchar(32) NOT NULL,
  `email` varchar(32) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `lastip` varchar(32) DEFAULT NULL,
  `lastlogin` varchar(32) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `token` varchar(32) DEFAULT NULL,
  `userjson` text DEFAULT NULL,
  `extjson` text DEFAULT NULL,
  `active` int(11) DEFAULT '0',
  `ctBy` varchar(32) DEFAULT 'sys',
  `ctDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `upBy` varchar(32) DEFAULT 'sys',
  `upDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table lmzrole;
CREATE TABLE `lmzrole` (
  `id` varchar(32) NOT NULL,
  `code` varchar(64)  NULL,
  `name` varchar(64)  NULL,
  `extjson` text DEFAULT NULL,
  `active` int(11) DEFAULT '0',
  `ctBy` varchar(32) DEFAULT 'sys',
  `ctDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `upBy` varchar(32) DEFAULT 'sys',
  `upDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table lmzrolemenu;
CREATE TABLE `lmzrolemenu` (
  `id` varchar(32) NOT NULL,
  `rid` varchar(32) NOT NULL,
  `mid` varchar(32) NOT NULL,
  `ctBy` varchar(32) DEFAULT 'sys',
  `ctDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `upBy` varchar(32) DEFAULT 'sys',
  `upDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table lmzroleuser;
CREATE TABLE `lmzroleuser` (
  `id` varchar(32) NOT NULL,
  `rid` varchar(32) NOT NULL,
  `uid` varchar(32) NOT NULL,
  `ctBy` varchar(32) DEFAULT 'sys',
  `ctDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `upBy` varchar(32) DEFAULT 'sys',
  `upDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

