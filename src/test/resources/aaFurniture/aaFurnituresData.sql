-- Customers
INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES('JAMES_PAUL', 'Paul', 'James', '615.223.5969');
INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES('MATHEW_MARK', 'Mark', 'Mathew', '931.399.4665');
INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES('ADEN_ZURI', 'Zuri', 'Aden', NULL);
INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES('SAGO_DUBE', 'Dube', 'Sago', '901.993.3774');
INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES('JACOB_COX', 'Cox', 'Jacob', '731.377.3854');
INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES('MATA_LULU', 'Lulu', 'Mata', NULL);

-- Colors
INSERT INTO colors (color_id, color, price) VALUES('Dark_Finish', 'Modern graphite', 300.00);
INSERT INTO colors (color_id, color, price) VALUES('Light_Finish', 'Mate Silver', 255.00);



-- Bed Room
INSERT INTO rooms (room_id, material, price) VALUES('BED_ROOM', 'Wood', 1485.00);
INSERT INTO rooms (room_id, material, price) VALUES('BED_ROOM', 'Fabric', 1225.00);
INSERT INTO rooms (room_id, material, price) VALUES('BED_ROOM', 'Metal', 1115.00);


-- Living Room
INSERT INTO rooms (room_id, material, price) VALUES('LIVING_ROOM', 'Wood', 3475.00);
INSERT INTO rooms (room_id, material, price) VALUES('LIVING_ROOM', 'Fabric', 1400.00);
INSERT INTO rooms (room_id, material, price) VALUES('LIVING_ROOM', 'Leather', 5455.00);


-- Accent Furniture
INSERT INTO rooms (room_id, material, price) VALUES('ACCENT_FURNITURE', 'Wood', 495.00);
INSERT INTO rooms (room_id, material, price) VALUES('ACCENT_FURNITURE', 'Metal', 255.00);

-- Options
INSERT INTO options(option_id, category, material, name, price) VALUES('AA_Collections', 'BED', 'Wood', 'AA Bed', 600.00);
INSERT INTO options(option_id, category, material, name, price) VALUES('NISH_Collections', 'BED', 'Metal', 'Nish Bed', 470.00);
INSERT INTO options(option_id, category, material, name, price) VALUES('ZURI_Collections', 'BED', 'Fabric', 'AA Bed Room', 525.00);


INSERT INTO options(option_id, category, material, name, price) VALUES('AA_Collections', 'SOFA', 'Wood', 'AA Sofa', 975.00);
INSERT INTO options(option_id, category, material, name, price) VALUES('NISH_Collections', 'BED', 'Metal', 'Nish Sofa', 700.00);
INSERT INTO options(option_id, category, material, name, price) VALUES('ZURI_Collections', 'BED', 'Fabric', 'Zuri Sofa', 450.00);



INSERT INTO options(option_id, category, material, name, price) VALUES('AA_Collections', 'CHAIR', 'Wood', 'AA Chair', 600.00);
INSERT INTO options(option_id, category, material, name, price) VALUES('NISH_Collections', 'CHAIR', 'Metal', 'Nish Chair', 399.00);
