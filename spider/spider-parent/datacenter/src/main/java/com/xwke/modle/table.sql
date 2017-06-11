CREATE TABLE s_news (
  id int(11) NOT NULL auto_increment,
  title varchar(50)NOT NULL,
  summary varchar(100),
  content text,
  graspingTime varchar(20),
  pubStatus int(3),
  source varchar(20),
  thumimg varchar(50),	
  picArray varchar(50),
  columnId int(11), 	
  PRIMARY KEY  (`id`)
)

CREATE TABLE s_news_column(
  id int(11) NOT NULL auto_increment,
  c_name varchar(50) NOT NULL,
  c_describe varchar(100),
  PRIMARY KEY  (`id`)
)