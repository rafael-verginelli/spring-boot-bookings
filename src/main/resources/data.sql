INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('Mercedes', 'S500', '5', '50000', 2004);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('Mercedes', 'GLA', '5', '50000', 2004);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('Mercedes', 'G-Class', '5', '50000', 2004);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('Mercedes', 'GLS', '5', '50000', 2004);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('Mercedes', 'S600', '5', '50000', 2004);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('BMW', 'E65', '5', '70000', 2002);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('BMW', 'E85', '2', '70000', 2002);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('BMW', 'G30', '5', '70000', 2002);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('BMW', 'F39', '2', '70000', 2002);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('Ford', 'Fiesta', '5', '35000', 2012);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('Ford', 'Focus', '5', '35000', 2012);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('Ford', 'Fusion', '5', '35000', 2012);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('Honda', 'Hornet', '2', '5700', 2008);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('Yamaha', 'Faizer', '2', '5700', 2008);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('Harley Davidson', 'Touring', '2', '5700', 2008);
INSERT INTO tb_vehicle (vehicle_type, vehicle_model, number_of_passengers, kilometers, production_year) VALUES ('Helicopter', 'XR-4', '12', '15700', 1942);

INSERT INTO tb_customer (first_name, last_name, email, address, country, state,	phone_number) VALUES ('John', 'Doe', 'john@doe.com', 'John Road 12', 'USA', 'California', '123123123');
INSERT INTO tb_customer (first_name, last_name, email, address, country, state,	phone_number) VALUES ('Audrey', 'Hepburn', 'audrey@hepburn.com', 'Audrey Hill 34', 'BR', 'Minas Gerais', '234234234');
INSERT INTO tb_customer (first_name, last_name, email, address, country, state,	phone_number) VALUES ('Mimi', 'Mimius', 'cosa@catita.com', 'Somewhere Street 56', 'CH', 'Fribourg', '567567567');

INSERT INTO tb_reservation (vehicle_id, customer_id, reservation_date) VALUES ((SELECT vehicle_id from tb_vehicle WHERE vehicle_model = 'S500'), (SELECT customer_id from tb_customer WHERE last_name = 'Hepburn'), '2024-03-14');
INSERT INTO tb_reservation (vehicle_id, customer_id, reservation_date) VALUES ((SELECT vehicle_id from tb_vehicle WHERE vehicle_model = 'Fiesta'), (SELECT customer_id from tb_customer WHERE last_name = 'Doe'), '2024-03-15');
INSERT INTO tb_reservation (vehicle_id, customer_id, reservation_date) VALUES ((SELECT vehicle_id from tb_vehicle WHERE vehicle_model = 'Hornet'), (SELECT customer_id from tb_customer WHERE last_name = 'Mimius'), '2024-03-16');