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
    ('ROLE_ADMIN', 'Administrator, all permissions', 'READ:PERSON, UPDATE:PERSON, DELETE:PERSON, CREATE:PERSON, READ:ITEM, UPDATE:ITEM, DELETE:ITEM, CREATE:ITEM'),
    ('ROLE_CLIENT', 'read and order', 'READ:PERSON, READ:ITEM'),
    ('ROLE_DONOR', 'read and donate', 'READ:PERSON, READ:ITEM'),
    ('ROLE_STAFF', 'read edit orders and inventory', 'READ:PERSON, READ:ITEM'),
    ('ROLE_VOLUNTEER', 'act and stuff', 'READ:PERSON, READ:ITEM');

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