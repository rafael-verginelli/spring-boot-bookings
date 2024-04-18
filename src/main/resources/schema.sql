CREATE TABLE tb_vehicle (
	vehicle_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	vehicle_type VARCHAR(16) NOT NULL,
	vehicle_model VARCHAR(16) NOT NULL,
	number_of_passengers VARCHAR(16) NOT NULL,
	kilometers VARCHAR(16) NOT NULL,
	production_year VARCHAR(16) NOT NULL
);

CREATE TABLE tb_customer (
	customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(32),
	last_name VARCHAR(32),
	email VARCHAR(32),
	address VARCHAR(64),
	country VARCHAR(16),
	state VARCHAR(16),
	phone_number VARCHAR(16)
);

CREATE TABLE tb_reservation (
	reservation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	vehicle_id BIGINT NOT NULL,
	customer_id BIGINT NOT NULL,
	reservation_date DATE
);

ALTER TABLE tb_reservation ADD FOREIGN KEY (vehicle_id) REFERENCES tb_vehicle(vehicle_id);
ALTER TABLE tb_reservation ADD FOREIGN KEY (customer_id) REFERENCES tb_customer(customer_id);
CREATE INDEX idx_reservation_date ON tb_reservation(reservation_date);