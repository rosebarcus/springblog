CREATE DATABASE IF NOT EXISTS springblog_db;

CREATE USER springblog_user@localhost IDENTIFIED BY '$2a$10$gvGkJ4OFS8nRehVjIWJ4l.GmVse3riES2H2qMhh0ex7ML01d5aaKy';
GRANT ALL ON springblog_db.* TO springblog_user@localhost;

SELECT user, host FROM mysql.user;