CREATE TABLE s_news (
  id int(11) NOT NULL auto_increment,
  title varchar(50)NOT NULL,
  summary varchar(100),
  content text,
  grasping_time varchar(20),
  pub_status int(3),
  source varchar(20),
  source_url varchar(20),
  thum_img varchar(50),	
  pic_array varchar(50),
  author varchar(50),
  column_id int(11), 	
  PRIMARY KEY  (`id`)
)

CREATE TABLE s_news_column(
  id int(11) NOT NULL auto_increment,
  c_name varchar(50) NOT NULL,
  c_describe varchar(100),
  PRIMARY KEY  (`id`)
)