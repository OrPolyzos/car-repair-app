INSERT INTO repair_types VALUES (1, 150, 'Small Service');
INSERT INTO repair_types VALUES (2, 300, 'Great Service');

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

LOAD DATA LOCAL INFILE 'J:\\DevProjects\\IntelliJ Projects\\carrepair\\Repairs.csv'
INTO TABLE repairs
FIELDS TERMINATED BY ';'
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE  0 lines
(repair_date_time,repair_status,repair_tasks,repair_total_cost,repair_typeid,vehicleid);

LOAD DATA LOCAL INFILE 'J:\\DevProjects\\IntelliJ Projects\\carrepair\\Parts.csv'
INTO TABLE parts
FIELDS TERMINATED BY ';'
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 0 lines
(part_name,part_price);
