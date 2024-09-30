CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32),
    password VARCHAR(256)
);

CREATE TABLE coordinates (
    id SERIAL PRIMARY KEY,
    x NUMERIC,
    y NUMERIC
);

CREATE TABLE location (
    id SERIAL PRIMARY KEY,
    x NUMERIC,
    y NUMERIC,
    z NUMERIC
);

CREATE TYPE country AS ENUM (
    'RUSSIA',
    'VATICAN',
    'SOUTH_KOREA',
    'JAPAN'
);

CREATE TYPE color AS ENUM (
    'GREEN',
    'RED',
    'BROWN'
);

CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(256),
    coordinates FOREIGN KEY,
    location FOREIGN KEY,
    creation_date TIMESTAMP,
    hairColor color,
    eyeColor color,
    nationality country,
    height NUMERIC
);