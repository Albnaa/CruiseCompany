
--
INSERT INTO role (id, name)
VALUES (1, 'ADMIN'),
       (2, 'USER');

--
INSERT INTO status (id, name)
VALUES (1, 'PAID'),
       (2, 'UNPAID');

--
INSERT INTO port (id, name)
VALUES (1, 'Miami'),
       (2, 'San Francisco'),
       (3, 'New York'),
       (4, 'Toronto'),
       (5, 'Rio de Janeiro'),
       (6, 'Barcelona'),
       (7, 'Sydney'),
       (8, 'Cape Town'),
       (9, 'Shanghai'),
       (10, 'Dubai');

--
INSERT INTO route (name, start_of_cruise, end_of_cruise)
VALUES ('Mediterranean Sea Cruise', '2022-06-01', '2022-06-10'),
       ('Caribbean Cruise', '2022-07-01', '2022-07-10'),
       ('Alaskan Cruise', '2022-08-01', '2022-08-10'),
       ('Hawaii Cruise', '2022-09-01', '2022-09-10'),
       ('Asia Cruise', '2022-10-01', '2022-10-10'),
       ('Europe Cruise', '2022-11-01', '2022-11-10'),
       ('South America Cruise', '2022-12-01', '2022-12-10'),
       ('Australia Cruise', '2023-01-01', '2023-01-10'),
       ('Antarctica Cruise', '2023-02-01', '2023-02-10'),
       ('Arctic Cruise', '2023-03-01', '2023-03-10');

--
INSERT INTO ship (name, capacity, visited_ports, staff, route_id)
VALUES ('Titanic', 3000, 10, 1000, 1),
       ('Queen Mary', 2000, 15, 500, 2),
       ('Carnival Breeze', 4000, 20, 1500, 3),
       ('Norwegian Dawn', 3500, 25, 1000, 4),
       ('Disney Magic', 2500, 10, 500, 5),
       ('Celebrity Eclipse', 3000, 20, 750, 6),
       ('Royal Caribbean Oasis of the Seas', 6000, 30, 2000, 7),
       ('MSC Fantasia', 4500, 25, 1500, 8),
       ('Princess Cruises Sapphire Princess', 3500, 20, 1000, 9),
       ('Holland America Line Maasdam', 2500, 15, 750, 10),
       ('AIDA Cruises AIDAvita', 4000, 20, 1000, 1),
       ('P&O Cruises Oriana', 3500, 15, 750, 2),
       ('Cunard Queen Victoria', 2500, 10, 500, 3);

--
INSERT INTO user (login, email, password, first_name, last_name, Role_id)
VALUES ('admin', 'admin@cruise.com', 'password', 'John', 'Doe', 1),
       ('manager', 'manager@cruise.com', 'password', 'Jane', 'Doe', 2),
       ('customer1', 'customer1@cruise.com', 'password', 'Bob', 'Bib', 2),
       ('customer2', 'customer2@cruise.com', 'password', 'Alice', 'Smith', 2),
       ('staff1', 'staff1@cruise.com', 'password', 'Tom', 'Johnson', 2),
       ('staff2', 'staff2@cruise.com', 'password', 'Sara', 'Williams', 2),
       ('captain', 'captain@cruise.com', 'password', 'Jason', 'Miller', 2),
       ('chef', 'chef@cruise.com', 'password', 'Ashley', 'Davis', 2),
       ('entertainment', 'entertainment@cruise.com', 'password', 'Michael', 'Brown', 2),
       ('housekeeping', 'housekeeping@cruise.com', 'password', 'Emma', 'Jones', 2),
       ('security', 'security@cruise.com', 'password', 'David', 'Garcia', 2),
       ('medical', 'medical@cruise.com', 'password', 'Jessica', 'Martin', 2);

INSERT INTO route_has_port (route_id, port_id, arrive_time, departure_time)
VALUES (1, 1, '2022-08-01', '2022-03-04'),
       (1, 2, '2022-08-01', '2022-03-04'),
       (1, 3, '2022-08-01', '2022-03-04'),
       (1, 4, '2022-08-01', '2022-03-04'),
       (1, 5, '2022-08-01', '2022-03-04'),
       (1, 6, '2022-08-01', '2022-03-04'),
       (1, 7, '2022-08-01', '2022-03-04'),
       (1, 8, '2022-08-01', '2022-03-04'),
       (1, 9, '2022-08-01', '2022-03-04'),
       (1, 10, '2022-08-01', '2022-03-04'),
       (2, 1, '2022-08-01', '2022-03-04'),
       (3, 1, '2022-08-01', '2022-03-04'),
       (4, 1, '2022-08-01', '2022-03-04'),
       (5, 1, '2022-08-01', '2022-03-04'),
       (6, 1, '2022-08-01', '2022-03-04');

--
INSERT INTO ticket (passengers_count, price, user_id, status_id, ship_id)
VALUES (1, 100, 1, 1, 1),
       (2, 200, 2, 2, 2),
       (3, 300, 3, 1, 3),
       (4, 400, 4, 2, 4),
       (5, 500, 5, 1, 5),
       (6, 600, 6, 1, 6),
       (7, 700, 7, 2, 7),
       (8, 800, 8, 2, 8),
       (9, 900, 9, 1, 9),
       (10, 1000, 10, 1, 10);

