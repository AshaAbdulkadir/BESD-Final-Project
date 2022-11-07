DROP TABLE IF EXISTS order_options;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS options;
DROP TABLE IF EXISTS furnitures;
DROP TABLE IF EXISTS colors;
DROP TABLE IF EXISTS customers;


CREATE TABLE customers (
  customer_pk int unsigned NOT NULL AUTO_INCREMENT,
  customer_id varchar(35) NOT NULL,
  first_name varchar(30) NOT NULL, 
  last_name varchar(30) NOT NULL,
  phone varchar(15),
  PRIMARY KEY (customer_pk)
);


CREATE TABLE colors (
  color_pk int unsigned NOT NULL AUTO_INCREMENT,
  color_id varchar(20) NOT NULL,
  color varchar(60) NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (color_pk),
  UNIQUE KEY (color_id)
);


CREATE TABLE furnitures (
  furniture_pk int unsigned NOT NULL AUTO_INCREMENT,
  furniture_id enum('BED_ROOM', 'LIVING_ROOM', 'ACCENT_FURNITURE') NOT NULL, 
  material varchar(50) NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (furniture_pk),
  UNIQUE KEY (furniture_id)
);


CREATE TABLE options (
  option_pk int unsigned NOT NULL AUTO_INCREMENT,
  option_id varchar(30) NOT NULL,
  category enum('BED', 'SOFA', 'CHAIR') NOT NULL,
  material varchar(50) NOT NULL,
  name varchar(40) NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (option_pk),
  UNIQUE KEY (option_id)
);


CREATE TABLE orders (
  order_pk int unsigned NOT NULL AUTO_INCREMENT,
  customer_fk int unsigned NOT NULL,
  furniture_fk int unsigned NOT NULL,
  color_fk int unsigned NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (order_pk),
  FOREIGN KEY (customer_fk) REFERENCES customers (customer_pk) ON DELETE CASCADE,
  FOREIGN KEY (furniture_fk) REFERENCES furnitures (furniture_pk) ON DELETE CASCADE,
  FOREIGN KEY (color_fk) REFERENCES colors (color_pk) ON DELETE CASCADE
);


CREATE TABLE order_options (
  option_fk int unsigned NOT NULL,
  order_fk int unsigned NOT NULL,
  FOREIGN KEY (option_fk) REFERENCES options (option_pk) ON DELETE CASCADE,
  FOREIGN KEY (order_fk) REFERENCES orders (order_pk) ON DELETE CASCADE
);




