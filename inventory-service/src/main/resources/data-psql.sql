-- Inventories
insert into inventories (inventory_id, type)
values
    ('inv01', 'VEHICLE'),
    ('inv02', 'VEHICLE'),
    ('inv03', 'PART'),
    ('inv04', 'ACCESSORY'),
    ('inv05', 'PART'),
    ('inv06', 'VEHICLE'),
    ('inv07', 'ACCESSORY'),
    ('inv08', 'VEHICLE'),
    ('inv09', 'VEHICLE'),
    ('inv10', 'PART');

-- Vehicles
INSERT INTO vehicles (vehicle_id, make, model, "year", status, usage_type, currency, price, inventory_id)
VALUES
    ('veh01', 'Toyota', 'Camry', 2023, 'Available', 'NEW', 'USD', 45.5, 'inv01'),
    ('veh02', 'Honda', 'Civic', 2024, 'Available', 'NEW', 'USD', 50.0, 'inv02'),
    ('veh03', 'Ford', 'Fusion', 2023, 'Available', 'NEW', 'USD', 48.75, 'inv06'),
    ('veh04', 'Chevrolet', 'Malibu', 2022, 'Available', 'NEW', 'USD', 47.25, 'inv08'),
    ('veh05', 'Toyota', 'Corolla', 2024, 'Available', 'NEW', 'USD', 49.0, 'inv09'),
    ('veh06', 'Nissan', 'Altima', 2023, 'Available', 'NEW', 'USD', 46.0, 'inv01'),
    ('veh07', 'Hyundai', 'Elantra', 2024, 'Available', 'NEW', 'USD', 51.0, 'inv02'),
    ('veh08', 'Kia', 'Forte', 2022, 'Available', 'NEW', 'USD', 47.5, 'inv06'),
    ('veh09', 'Mazda', 'Mazda3', 2023, 'Available', 'NEW', 'USD', 46.5, 'inv08'),
    ('veh10', 'Subaru', 'Impreza', 2024, 'Available', 'NEW', 'USD', 48.0, 'inv09');

-- Vehicle Options
INSERT INTO vehicles_options (vehicle_id, option_name, description, option_price)
VALUES
    ('veh01', 'Option 1', 'Description of Option 1', 1000.00),
    ('veh02', 'Option 2', 'Description of Option 2', 1100.00),
    ('veh03', 'Option 3', 'Description of Option 3', 1200.00),
    ('veh04', 'Option 4', 'Description of Option 4', 1300.00),
    ('veh05', 'Option 5', 'Description of Option 5', 1400.00),
    ('veh06', 'Option 6', 'Description of Option 6', 1500.00),
    ('veh07', 'Option 7', 'Description of Option 7', 1600.00),
    ('veh08', 'Option 8', 'Description of Option 8', 1700.00),
    ('veh09', 'Option 9', 'Description of Option 9', 1800.00),
    ('veh10', 'Option 10', 'Description of Option 10', 1900.00);
