ALTER TABLE Product
DROP
CONSTRAINT WinningBidFK;

-- SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS Bid;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS User;
-- SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE User
(
    id        BIGINT                         NOT NULL AUTO_INCREMENT,
    userName  VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password  VARCHAR(60)                    NOT NULL,
    firstName VARCHAR(60)                    NOT NULL,
    lastName  VARCHAR(60)                    NOT NULL,
    email     VARCHAR(60)                    NOT NULL,
    role      TINYINT                        NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE TABLE Category
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    CONSTRAINT CategoryPK PRIMARY KEY (id),
    CONSTRAINT CategoryNameUniqueKey UNIQUE (name)
) ENGINE = InnoDB;

CREATE TABLE Product
(
    id              BIGINT         NOT NULL AUTO_INCREMENT,
    userId          BIGINT         NOT NULL,
    name            VARCHAR(60)    NOT NULL,
    description     VARCHAR(2000)  NOT NULL,
    publicationDate DATETIME       NOT NULL,
    expirationDate  DATETIME       NOT NULL,
    startPrice      DECIMAL(11, 2) NOT NULL,
    currentPrice    DECIMAL(11, 2) NOT NULL,
    categoryId      BIGINT         NOT NULL,
    shipInfo        VARCHAR(2000)  NOT NULL,
    winningBid      BIGINT,
    hasBidder       TINYINT,
    version         BIGINT,
    CONSTRAINT ProductPK PRIMARY KEY (id),
    FOREIGN KEY (categoryId) REFERENCES Category (id),
    FOREIGN KEY (userId) REFERENCES User (id)
    --FOREIGN KEY(winningBidId) REFERENCES ProductBids(id)
) ENGINE = InnoDB;

CREATE TABLE Bid
(
    id        BIGINT         NOT NULL AUTO_INCREMENT,
    userId    BIGINT         NOT NULL,
    price     DECIMAL(11, 2) NOT NULL,
    productId BIGINT         NOT NULL,
    bidDate   DATETIME       NOT NULL,
    bidStatus   TINYINT       NOT NULL,
    CONSTRAINT BidPK PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES User (id),
    FOREIGN KEY (productId) REFERENCES Product (id)
) ENGINE = InnoDB;

ALTER TABLE Product
    ADD CONSTRAINT WinningBidFK FOREIGN KEY (winningBid) REFERENCES Bid (id);

