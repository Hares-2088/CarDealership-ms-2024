-- Employees
INSERT INTO employees (employee_id, first_name, last_name, email_address, salary, currency, commission_rate, street_address, city, province, country, postal_code, department_id, position_title)
VALUES
    ('emp01', 'Adem', 'Doe', 'john.doe@example.com', 50000.00, 'USD', 0.05, '123 Main St', 'Anytown', 'Anyprovince', 'USA', '12345', 'dep01', 'Manager'),
    ('emp02', 'Jane', 'Smith', 'jane.smith@example.com', 60000.00, 'USD', 0.07, '456 Elm St', 'Othertown', 'Otherprovince', 'USA', '67890', 'dep02', 'Sales Associate'),
    ('emp03', 'Alice', 'Johnson', 'alice.johnson@example.com', 45000.00, 'USD', 0.03, '789 Oak St', 'Smalltown', 'Smallprovince', 'USA', '54321', 'dep01', 'Administrator'),
    ('emp04', 'Michael', 'Brown', 'michael.brown@example.com', 55000.00, 'USD', 0.06, '101 Elm St', 'Bigtown', 'Bigprovince', 'USA', '10101', 'dep02', 'Sales Associate'),
    ('emp05', 'Emily', 'Wilson', 'emily.wilson@example.com', 52000.00, 'USD', 0.04, '222 Maple St', 'Largetown', 'Largeprovince', 'USA', '20202', 'dep03', 'Analyst'),
    ('emp06', 'David', 'Miller', 'david.miller@example.com', 58000.00, 'USD', 0.08, '333 Oak St', 'Smallville', 'Smallprovince', 'USA', '30303', 'dep04', 'Supervisor'),
    ('emp07', 'Olivia', 'Martinez', 'olivia.martinez@example.com', 53000.00, 'USD', 0.05, '444 Cedar St', 'Mediumtown', 'Mediumprovince', 'USA', '40404', 'dep05', 'Executive'),
    ('emp08', 'Daniel', 'Garcia', 'daniel.garcia@example.com', 56000.00, 'USD', 0.07, '555 Pine St', 'Hometown', 'Homeprovince', 'USA', '50505', 'dep06', 'Engineer'),
    ('emp09', 'Sophia', 'Lopez', 'sophia.lopez@example.com', 54000.00, 'USD', 0.06, '666 Birch St', 'Countrytown', 'Countryprovince', 'USA', '60606', 'dep07', 'Representative'),
    ('emp10', 'Matthew', 'Hernandez', 'matthew.hernandez@example.com', 57000.00, 'USD', 0.05, '777 Walnut St', 'Villagetown', 'Villageprovince', 'USA', '70707', 'dep08', 'Researcher');

-- Employee Phone Numbers
insert into employee_phonenumbers (employee_id, type, number)
values
    ('emp01', 'WORK', '515-555-5555'),
    ('emp01', 'MOBILE', '514-555-4444'),
    ('emp02', 'WORK', '616-555-5555'),
    ('emp02', 'MOBILE', '616-555-4444'),
    ('emp03', 'WORK', '727-555-5555'),
    ('emp03', 'MOBILE', '727-555-4444'),
    ('emp04', 'WORK', '838-555-5555'),
    ('emp04', 'MOBILE', '838-555-4444'),
    ('emp05', 'WORK', '949-555-5555'),
    ('emp05', 'MOBILE', '949-555-4444');

--Departments
INSERT INTO departments (department_id, name, head_count)
VALUES
    ('dep01', 'Human Resources', 10),
    ('dep02', 'Marketing', 12),
    ('dep03', 'Finance', 8),
    ('dep04', 'Operations', 15),
    ('dep05', 'Sales', 20),
    ('dep06', 'Information Technology', 25),
    ('dep07', 'Customer Service', 18),
    ('dep08', 'Research and Development', 7),
    ('dep09', 'Legal', 5),
    ('dep10', 'Supply Chain', 11);
