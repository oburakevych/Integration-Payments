use `polling`;

CREATE TABLE dropbox_account (
  id VARCHAR(40) NOT NULL PRIMARY KEY,
  created TIMESTAMP NOT NULL,
  deactivated TIMESTAMP,
  name VARCHAR(255),
  email VARCHAR(255),
  country VARCHAR(20),
  lang VARCHAR(20),
  referral_link VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE dropbox_access_token (
  account_id VARCHAR(40) PRIMARY KEY,
  value VARCHAR(40) NOT NULL,
  secret VARCHAR(40) NOT NULL,
  consumerkey VARCHAR(255) NOT NULL,
  created TIMESTAMP NOT NULL,
  CONSTRAINT FOREIGN KEY 
    dropbox_access_token_accountid_fk (account_id) 
    REFERENCES dropbox_account (id)
) ENGINE=InnoDB;

CREATE TABLE dropbox_directory (
  id VARCHAR(40) PRIMARY KEY,
  account_id VARCHAR(40) NOT NULL,
  directory VARCHAR(255),
  hash VARCHAR(255),
  modified TIMESTAMP NOT NULL,
  last_check TIMESTAMP,
  is_updated INT,
  CONSTRAINT FOREIGN KEY 
    dropbox_hash_accountid_fk (account_id) 
    REFERENCES dropbox_account (id)
) ENGINE=InnoDB;