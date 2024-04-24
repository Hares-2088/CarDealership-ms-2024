-- USE `inventory-db`;

DROP TABLE IF EXISTS inventories;
CREATE TABLE IF NOT EXISTS inventories (
    id SERIAL,
    inventory_id VARCHAR(36),
    type VARCHAR(100),
    PRIMARY KEY (id)
    );

DROP TABLE IF EXISTS vehicles_options;
create table if not exists vehicles_options (
    vehicle_id VARCHAR(50),
    option_name VARCHAR(100),
    description VARCHAR(200),
    option_price DECIMAL(19,2)
    );

DROP TABLE IF EXISTS vehicles_manufacturer;
CREATE TABLE IF NOT EXISTS vehicles_manufacturer (
    vehicle_id VARCHAR(36),
    name VARCHAR(255),
    country VARCHAR(255)
    );

Drop table if exists vehicles;
CREATE TABLE IF NOT EXISTS vehicles (
    id SERIAL,
    vehicle_id VARCHAR(36),
    make VARCHAR(255),
    model VARCHAR(255),
    year INTEGER,
    status VARCHAR(255),
    usage_type VARCHAR(255),
    currency VARCHAR(36),
    price DECIMAL(12,2),
    inventory_id VARCHAR(36),
    PRIMARY KEY (id)
    );