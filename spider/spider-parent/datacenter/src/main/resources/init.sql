CREATE TABLE s_news (
  id int(11) NOT NULL auto_increment,
  title varchar(50)NOT NULL,
  summary varchar(100),
  content text,
  grasping_time varchar(20),
  pub_status int(3),
  source varchar(200),
  source_url varchar(200),
  thum_img varchar(100),	
  pic_array varchar(200),
  thum_img_array varchar(200),
  pub_time varchar(100),
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