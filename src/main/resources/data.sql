LOAD DATA LOCAL INFILE 'C:\\Users\\vicky\\Desktop\\carrepair\\Users.csv '--'J:\\DevProjects\\IntelliJ Projects\\carrepair\\Users.csv'
INTO TABLE users
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 0 lines
(afm,email,password,type,firstname,lastname,address_street,address_number,address_zip_code);

LOAD DATA LOCAL INFILE   'C:\\Users\\vicky\\Desktop\\carrepair\\Vehicles.csv'--'J:\\DevProjects\\IntelliJ Projects\\carrepair\\Vehicles.csv'
INTO TABLE vehicles
FIELDS TERMINATED BY ';'
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE  1 lines
(vehicleid,brand,model,fuel_type,year,color,userid);

--LOAD DATA LOCAL INFILE 'C:\\Users\\vicky\\Desktop\\carrepair\\Repairs.csv'--'J:\\DevProjects\\IntelliJ Projects\\carrepair\\Users.csv'
--INTO TABLE repairs
--FIELDS TERMINATED BY ','
--OPTIONALLY ENCLOSED BY '"'
--LINES TERMINATED BY '\n'
--IGNORE 0 lines
--(repairid,email,password,type,firstname,lastname,address_street,address_number,address_zip_code);

INSERT INTO Repair_Types (repair_type, fixed_price)
VALUES ("Small Service",150);


INSERT INTO Repair_Types (repair_type, fixed_price)
VALUES ("Big Service",300);