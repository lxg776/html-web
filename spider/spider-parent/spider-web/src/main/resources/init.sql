CREATE TABLE s_news (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  title varchar(50)NOT NULL,
  summary varchar(100),
  content text,
  grasping_time varchar(200),
  pub_status int(3),
  source varchar(255),
  source_url varchar(255),
  thum_img varchar(255),	
  pic_array text,
  thum_img_array text,
  pub_time varchar(100),
  author varchar(100),
  column_id int(11), 	
  PRIMARY KEY  (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE s_news_column(
  id int(11) NOT NULL auto_increment,
  c_name varchar(50) NOT NULL,
  c_describe varchar(100),
  PRIMARY KEY  (`id`)
)

CREATE TABLE s_site_config(
  id int(11) NOT NULL auto_increment,
  c_jsontext text,
  c_alias varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
)



CREATE TABLE `SCHEDULE_JOB` (
  `schedule_job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(255) DEFAULT NULL,
  `alias_name` varchar(255) DEFAULT NULL,
  `job_group` varchar(255) DEFAULT NULL,
  `job_trigger` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `executor` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gmt_create` timestamp NULL DEFAULT NULL,
  `gmt_modify` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`schedule_job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8