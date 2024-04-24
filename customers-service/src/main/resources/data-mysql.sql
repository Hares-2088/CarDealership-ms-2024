-- Customers
INSERT INTO customers (customer_id, first_name, last_name, email_address, street_address, city, province, country, postal_code)
VALUES
    ('cus01', 'John', 'Doe', 'john.doe@example.com', '123 Main St', 'Anytown', 'Anyprovince', 'USA', '12345'),
    ('cus02', 'Jane', 'Smith', 'jane.smith@example.com', '456 Elm St', 'Othertown', 'Otherprovince', 'USA', '67890'),
    ('cus03', 'Alice', 'Johnson', 'alice.johnson@example.com', '789 Oak St', 'Smalltown', 'Smallprovince', 'USA', '54321'),
    ('cus04', 'Michael', 'Brown', 'michael.brown@example.com', '101 Elm St', 'Bigtown', 'Bigprovince', 'USA', '10101'),
    ('cus05', 'Emily', 'Wilson', 'emily.wilson@example.com', '222 Maple St', 'Largetown', 'Largeprovince', 'USA', '20202'),
    ('cus06', 'David', 'Miller', 'david.miller@example.com', '333 Oak St', 'Smallville', 'Smallprovince', 'USA', '30303'),
    ('cus07', 'Olivia', 'Martinez', 'olivia.martinez@example.com', '444 Cedar St', 'Mediumtown', 'Mediumprovince', 'USA', '40404'),
    ('cus08', 'Daniel', 'Garcia', 'daniel.garcia@example.com', '555 Pine St', 'Hometown', 'Homeprovince', 'USA', '50505'),
    ('cus09', 'Sophia', 'Lopez', 'sophia.lopez@example.com', '666 Birch St', 'Countrytown', 'Countryprovince', 'USA', '60606'),
    ('cus10', 'Matthew', 'Hernandez', 'matthew.hernandez@example.com', '777 Walnut St', 'Villagetown', 'Villageprovince', 'USA', '70707');
-- Customer Phone Numbers
insert into customer_phonenumbers (customer_id, type, number)
values
    ('cus01', 'HOME', '123-456-7890'),
    ('cus01', 'MOBILE', '234-567-8901'),
    ('cus02', 'WORK', '345-678-9012'),
    ('cus02', 'MOBILE', '456-789-0123'),
    ('cus03', 'HOME', '567-890-1234'),
    ('cus03', 'MOBILE', '678-901-2345'),
    ('cus04', 'WORK', '789-012-3456'),
    ('cus04', 'MOBILE', '890-123-4567'),
    ('cus05', 'HOME', '901-234-5678'),
    ('cus05', 'MOBILE', '012-345-6789');