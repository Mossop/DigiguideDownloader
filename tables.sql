DROP TABLE IF EXISTS Channel;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS Programme;

CREATE TABLE Channel (
	id		INTEGER NOT NULL,
	name		VARCHAR(50),
	url		VARCHAR(50),
	PRIMARY KEY (id)
);

CREATE TABLE Category (
	id		INTEGER NOT NULL,
	name		VARCHAR(50),
	PRIMARY KEY (id)
);

CREATE TABLE Programme (
	id		INTEGER,
	date		DATETIME NOT NULL,
	channel		INTEGER NOT NULL,
	category	INTEGER,
	length		INTEGER,
	title		VARCHAR(50),
	description	TEXT,
	PRIMARY KEY (date,channel)
);
