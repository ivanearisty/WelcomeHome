-- Establish the connection and set the time zone
SET TIME_ZONE = '-04:00';

DROP SCHEMA IF EXISTS Project3;

-- Create the schema if it doesn't exist
CREATE SCHEMA IF NOT EXISTS Project3;

-- Select the database
USE Project3;

CREATE TABLE Category (
                          mainCategory VARCHAR(50) NOT NULL,
                          subCategory VARCHAR(50) NOT NULL,
                          catNotes TEXT,
                          PRIMARY KEY (mainCategory, subCategory)
);

CREATE TABLE Item (
                      ItemID INT NOT NULL AUTO_INCREMENT,
                      iDescription TEXT,
                      photo MEDIUMBLOB,
                      color VARCHAR(20),
                      isNew BOOLEAN DEFAULT TRUE,
                      hasPieces BOOLEAN,
                      material VARCHAR(50),
                      mainCategory VARCHAR(50) NOT NULL,
                      subCategory VARCHAR(50) NOT NULL,
                      PRIMARY KEY (ItemID),
                      FOREIGN KEY (mainCategory, subCategory) REFERENCES Category(mainCategory, subCategory)
);


CREATE TABLE Person (
                        userName VARCHAR(50) NOT NULL,
                        password VARCHAR(100) NOT NULL,
                        fname VARCHAR(50) NOT NULL,
                        lname VARCHAR(50) NOT NULL,
                        email VARCHAR(100) NOT NULL,
                        is_non_locked  BOOLEAN DEFAULT TRUE,
                        is_enabled     BOOLEAN DEFAULT TRUE,
                        PRIMARY KEY (userName)
);

CREATE TABLE PersonPhone (
                             userName VARCHAR(50) NOT NULL,
                             phone VARCHAR(20) NOT NULL,
                             PRIMARY KEY (userName, phone),
                             FOREIGN KEY (userName) REFERENCES Person(userName)
);

CREATE TABLE DonatedBy (
                           ItemID INT NOT NULL,
                           userName VARCHAR(50) NOT NULL,
                           donateDate DATE NOT NULL,
                           PRIMARY KEY (ItemID, userName),
                           FOREIGN KEY (ItemID) REFERENCES Item(ItemID),
                           FOREIGN KEY (userName) REFERENCES Person(userName)
);

CREATE TABLE Role (
                      roleID VARCHAR(20) NOT NULL,
                      roleDescription VARCHAR(100),
                      rolePermissions VARCHAR(255),
                      PRIMARY KEY (roleID)
);

INSERT INTO Role(roleID, roleDescription, rolePermissions)
VALUES
    ('ROLE_ADMIN', 'Administrator, all permissions', 'READ:PERSON, UPDATE:PERSON, DELETE:PERSON, CREATE:PERSON, READ:ITEM, UPDATE:ITEM, DELETE:ITEM, CREATE:ITEM, READ:ORDER' ),
    ('ROLE_CLIENT', 'read and order', 'READ:PERSON, READ:ITEM'),
    ('ROLE_DONOR', 'read and donate', 'READ:PERSON, READ:ITEM'),
    ('ROLE_STAFF', 'read edit orders and inventory', 'READ:PERSON, READ:ITEM, READ:ORDER, CREATE:ITEM'),
    ('ROLE_VOLUNTEER', 'act and stuff', 'READ:PERSON, READ:ITEM, READ:ORDER');

CREATE TABLE RolePerson (
                      roleID VARCHAR(20) NOT NULL,
                      userName VARCHAR(50) NOT NULL,
                      PRIMARY KEY (roleID, userName),
                      FOREIGN KEY (roleID) references Role(roleID),
                      FOREIGN KEY (userName) references Person(userName)
);

CREATE TABLE Act (
                     userName VARCHAR(50) NOT NULL,
                     roleID VARCHAR(20) NOT NULL,
                     PRIMARY KEY (userName, roleID),
                     FOREIGN KEY (userName) REFERENCES Person(userName),
                     FOREIGN KEY (roleID) REFERENCES Role(roleID)
);

CREATE TABLE Location (
                          roomNum INT NOT NULL,
                          shelfNum INT NOT NULL, -- not a point for deduction
                          shelf VARCHAR(20),
                          shelfDescription VARCHAR(200),
                          PRIMARY KEY (roomNum, shelfNum)
);



CREATE TABLE Piece (
                       ItemID INT NOT NULL,
                       pieceNum INT NOT NULL,
                       pDescription VARCHAR(200),
                       length INT NOT NULL, -- for simplicity
                       width INT NOT NULL,
                       height INT NOT NULL,
                       roomNum INT NOT NULL,
                       shelfNum INT NOT NULL,
                       pNotes TEXT,
                       PRIMARY KEY (ItemID, pieceNum),
                       FOREIGN KEY (ItemID) REFERENCES Item(ItemID),
                       FOREIGN KEY (roomNum, shelfNum) REFERENCES Location(roomNum, shelfNum)
);

CREATE TABLE Ordered (
                         orderID INT NOT NULL AUTO_INCREMENT,
                         orderDate DATE NOT NULL,
                         orderNotes VARCHAR(200),
                         supervisor VARCHAR(50) NOT NULL,
                         client VARCHAR(50) NOT NULL,
                         PRIMARY KEY (orderID),
                         FOREIGN KEY (supervisor) REFERENCES Person(userName),
                         FOREIGN KEY (client) REFERENCES Person(userName)
);

CREATE TABLE ItemIn (
                        ItemID INT NOT NULL,
                        orderID INT NOT NULL,
                        found BOOLEAN DEFAULT FALSE,
                        PRIMARY KEY (ItemID, orderID),
                        FOREIGN KEY (ItemID) REFERENCES Item(ItemID),
                        FOREIGN KEY (orderID) REFERENCES Ordered(orderID)
);


CREATE TABLE Delivered (
                           userName VARCHAR(50) NOT NULL,
                           orderID INT NOT NULL,
                           status VARCHAR(20) NOT NULL,
                           date DATE NOT NULL,
                           PRIMARY KEY (userName, orderID),
                           FOREIGN KEY (userName) REFERENCES Person(userName),
                           FOREIGN KEY (orderID) REFERENCES Ordered(orderID)
);

-- Note, the following random data was partially generated with the help of AI for the random values:

-- Insert data into Category table
INSERT INTO Category (mainCategory, subCategory, catNotes) VALUES
        ('Furniture', 'Tables', 'Eating furniture'),
        ('Furniture', 'Chairs', 'Seating furniture'),
        ('Electronics', 'Phones', 'Mobile and landline phones'),
        ('Books', 'Fiction', 'Novels and stories'),
        ('Default', 'Default', 'Eating furniture');

-- Insert data into Item table
INSERT INTO Item (iDescription, color, isNew, hasPieces, material, mainCategory, subCategory) VALUES
        ('A comfortable wooden chair', 'Brown', TRUE, FALSE, 'Wood', 'Furniture', 'Chairs'),
        ('A high-tech smartphone', 'Black', TRUE, FALSE, 'Plastic', 'Electronics', 'Phones'),
        ('A bestselling novel', 'N/A', FALSE, FALSE, 'Paper', 'Books', 'Fiction');

-- Insert data into Person table
INSERT INTO Person (userName, password, fname, lname, email) VALUES
        ('admin', 'admin123', 'John', 'Doe', 'admin@example.com'),
        ('client1', 'client123', 'Jane', 'Smith', 'jane@example.com'),
        ('donor1', 'donor123', 'Jim', 'Beam', 'jim@example.com'),
        ('staff1', 'staff123', 'Sam', 'Adams', 'sam@example.com');

-- Insert data into DonatedBy table
INSERT INTO DonatedBy (ItemID, userName, donateDate) VALUES
        (1, 'donor1', '2023-04-12');

-- Insert data into Ordered table
INSERT INTO Ordered (orderDate, supervisor, client) VALUES
        ('2023-08-09', 'admin', 'client1'),
        ('2023-08-10', 'admin', 'client1');

INSERT INTO RolePerson (roleID, userName) VALUES
        ('ROLE_DONOR', 'donor1'),
        ('ROLE_CLIENT', 'client1'),
        ('ROLE_STAFF', 'staff1');

-- Insert data into Location table
INSERT INTO Location (roomNum, shelfNum, shelf, shelfDescription) VALUES
    (1, 1, 'Top Shelf', 'Shelf1'),
    (1, 2, 'Mid Shelf', 'Shelf2'),
    (1, 3, 'Bot Shelf', 'Shelf3'),
    (1, 4, 'Side Shelf', 'Shelf4'),
    (1, 5, 'Up Shelf', 'Shelf5');


-- Insert data into Piece table
INSERT INTO Piece (ItemID, pieceNum, pDescription, length, width, height, shelfNum, roomNum) VALUES
        (1, 1, 'Chair seat', 40, 40, 5, 5,1),
        (1, 2, 'Chair Leg', 40, 10, 5, 4,1),
        (1, 3, 'Chair Leg', 40, 10, 5, 3,1),
        (1, 4, 'Chair Leg', 40, 10, 5, 2,1),
        (1, 5, 'Chair Leg', 40, 10, 5, 1,1);

-- Insert Items
INSERT INTO ItemIn (ItemID, orderID, found)
VALUES  (1, 1, FALSE),
        (2, 2, FALSE);


