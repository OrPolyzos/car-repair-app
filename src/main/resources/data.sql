LOAD DATA LOCAL INFILE 'J:\\DevProjects\\IntelliJ Projects\\carrepair\\Users.csv'
INTO TABLE users
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 0 lines
(afm,email,password,type,firstname,lastname,address_street,address_number,address_zip_code);

LOAD DATA LOCAL INFILE 'J:\\DevProjects\\IntelliJ Projects\\carrepair\\Vehicles.csv'
INTO TABLE vehicles
FIELDS TERMINATED BY ';'
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE  1 lines
(vehicleid,brand,model,fuel_type,year,color,userid);
