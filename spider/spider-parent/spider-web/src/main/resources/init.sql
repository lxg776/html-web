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
)

CREATE TABLE s_news_column(
  id int(11) NOT NULL auto_increment,
  c_name varchar(50) NOT NULL,
  c_describe varchar(100),
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
  `job_executor` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gmt_create` timestamp NULL DEFAULT NULL,
  `gmt_modify` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`schedule_job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

create table s_content_tag(
	 id int(11) NOT NULL auto_increment,
	 `tag_name` varchar(255) DEFAULT NULL,
	 `remarks` varchar(255),
	 PRIMARY KEY  (`id`)
)

create table s_tag_news_relation(
	 id int(11) NOT NULL auto_increment,
	 tag_id int(11) DEFAULT NULL,
	 news_id bigint(20) DEFAULT NULL,
	 pub_status varchar(10),
	  PRIMARY KEY  (id),
	  UNIQUE KEY `u_key` (`tag_id`,`news_id`) 
)

create table s_news_node(
	id int(11) NOT NULL auto_increment,
	tag_name varchar(255) DEFAULT NULL,
	sort int(3) ,
	PRIMARY KEY  (`id`)
)

create table s_node_relation(
	id int(11) NOT NULL auto_increment,
	`f_id` int(11) ,
	`c_id` int(11) ,
	`node_level` int(3) ,
     UNIQUE KEY `u_key` (`f_id`,`c_id`) ,
	 PRIMARY KEY  (`id`)
)

create table s_node_tag_relation(
	id int(11) NOT NULL auto_increment,
	`tag_id` int(11) ,
	`node_id` int(11) ,
	`status` varchar(10),
	 UNIQUE KEY `u_key` (`tag_id`,`node_id`),
	PRIMARY KEY  (`id`)
)

create table s_image_record (
  		id bigint(20) NOT NULL AUTO_INCREMENT,
		`news_id` bigint(20) ,	
		`image_url` varchar(255) DEFAULT NULL,
		`save_path` varchar(255) DEFAULT NULL,
		`load_count` int(3),
		`status` varchar(10), 
		PRIMARY KEY  (`id`)
)

create table s_executor (
  		id bigint(20) NOT NULL AUTO_INCREMENT,
		`c_name` varchar(255) ,	
		`c_describe` varchar(255),
		`c_type` varchar(255),
		`link_url`  varchar(255),
		PRIMARY KEY  (`id`)
)
