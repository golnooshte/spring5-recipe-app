CREATE DATABASE sfg_dev;
CREATE DATABASE sfg_prod;

CREATE USER 'sfg_dev'@'Localhost' IDENTIFIED  BY 'goli';
CREATE USER 'sfg_prod'@'Localhost' IDENTIFIED BY 'goli';



GRANT SELECT  ON sfg_dev.* TO 'sfg_dev'@'Localhost'
GRANT UPDATE  ON sfg_dev.* TO 'sfg_dev'@'Localhost'
GRANT DELETE  ON sfg_dev.* TO 'sfg_dev'@'Localhost'
GRANT INSERT  ON sfg_dev.* TO 'sfg_dev'@'Localhost'
GRANT SELECT  ON sfg_prod.* TO 'sfg_prod'@'Localhost'
GRANT UPDATE  ON sfg_prod.* TO 'sfg_prod'@'Localhost'
GRANT DELETE  ON sfg_prod.* TO 'sfg_prod'@'Localhost'
GRANT INSERT  ON sfg_prod.* TO 'sfg_prod'@'Localhost'
