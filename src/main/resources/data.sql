LOAD DATA LOCAL INFILE 'C:\\Users\\Orestes\\Desktop\\carrepair\\Users.csv'
INTO TABLE users
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 0 lines
(afm,email,password,type,firstname,lastname,addressid);