CREATE TABLE users
(
  id           INT          NOT NULL PRIMARY KEY,
  bday         DATETIME     NULL,
  city         VARCHAR(255) NULL,
  country      VARCHAR(255) NULL,
  email        VARCHAR(255) NULL,
  name         TEXT         NULL,
  password     VARCHAR(255) NULL,
  phone_number VARCHAR(255) NULL,
  role         VARCHAR(255) NULL,
  surname      VARCHAR(255) NULL
)
  ENGINE = MyISAM
  CHARSET = utf8;

