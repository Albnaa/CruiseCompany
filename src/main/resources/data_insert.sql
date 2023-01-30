--
INSERT INTO role (id, name)
VALUES (1, 'ADMIN'),
       (2, 'USER');

--
INSERT INTO status (id, name)
VALUES (1, 'UNCHECKED'),
       (2, 'UNPAID'),
       (3, 'PAID'),
       (4, 'COMPLETED');

--
INSERT INTO Port (id, name)
VALUES (1, 'New York'),
       (2, 'Los Angeles'),
       (3, 'Chicago'),
       (4, 'Houston'),
       (5, 'Phoenix'),
       (6, 'Philadelphia'),
       (7, 'San Antonio'),
       (8, 'San Diego'),
       (9, 'Dallas'),
       (10, 'San Jose'),
       (11, 'Austin'),
       (12, 'Jacksonville'),
       (13, 'San Francisco'),
       (14, 'Columbus'),
       (15, 'Charlotte'),
       (16, 'Fort Worth'),
       (17, 'Indianapolis'),
       (18, 'Seattle'),
       (19, 'Denver'),
       (20, 'Washington'),
       (21, 'Boston'),
       (22, 'El Paso'),
       (23, 'Detroit'),
       (24, 'Nashville'),
       (25, 'Portland'),
       (26, 'Memphis'),
       (27, 'Oklahoma City'),
       (28, 'Las Vegas'),
       (29, 'Louisville'),
       (30, 'Baltimore'),
       (31, 'Milwaukee'),
       (32, 'Albuquerque'),
       (33, 'Tucson'),
       (34, 'Fresno'),
       (35, 'Sacramento'),
       (36, 'Mesa'),
       (37, 'Kansas City'),
       (38, 'Atlanta'),
       (39, 'Long Beach'),
       (40, 'Omaha'),
       (41, 'Raleigh'),
       (42, 'Miami'),
       (43, 'Minneapolis'),
       (44, 'Cleveland'),
       (45, 'New Orleans'),
       (46, 'St. Louis'),
       (47, 'Tampa'),
       (48, 'Pittsburgh'),
       (49, 'Cincinnati'),
       (50, 'Anchorage');

--
INSERT INTO route (id, name, start_of_cruise, end_of_cruise, price)
VALUES (1, 'Mediterranean Cruise', '2022-05-01', '2022-05-10', 1000),
       (2, 'Caribbean Cruise', '2022-06-01', '2022-06-10', 1200),
       (3, 'Alaska Cruise', '2022-07-01', '2022-07-10', 1500),
       (4, 'Europe River Cruise', '2022-08-01', '2022-08-10', 800),
       (5, 'Asia Cruise', '2022-09-01', '2022-09-10', 1700),
       (6, 'Australia Cruise', '2022-10-01', '2022-10-10', 2000),
       (7, 'Africa Cruise', '2022-11-01', '2022-11-10', 1800),
       (8, 'Antarctica Cruise', '2022-12-01', '2022-12-10', 2500),
       (9, 'South America Cruise', '2023-01-01', '2023-01-10', 2100),
       (10, 'North America Cruise', '2023-02-01', '2023-02-10', 2400),
       (11, 'Mediterranean Cruise', '2023-03-01', '2023-03-10', 1000),
       (12, 'Caribbean Cruise', '2023-04-01', '2023-04-10', 1200),
       (13, 'Alaska Cruise', '2023-05-01', '2023-05-10', 1500),
       (14, 'Europe River Cruise', '2023-06-01', '2023-06-10', 800),
       (15, 'Asia Cruise', '2023-07-01', '2023-07-10', 1700),
       (16, 'Australia Cruise', '2023-08-01', '2023-08-10', 2000),
       (17, 'Africa Cruise', '2023-09-01', '2023-09-10', 1800),
       (18, 'Antarctica Cruise', '2023-10-01', '2023-10-10', 2500),
       (19, 'South America Cruise', '2023-11-01', '2023-11-10', 2100),
       (20, 'North America Cruise', '2023-12-01', '2023-12-10', 2400),
       (21, 'Mediterranean Cruise', '2024-01-01', '2024-01-10', 1000),
       (22, 'Caribbean Cruise', '2024-02-01', '2024-02-10', 1200),
       (23, 'Alaska Cruise', '2024-03-01', '2024-03-10', 1500),
       (24, 'Europe River Cruise', '2024-04-01', '2024-04-10', 800),
       (25, 'Asia Cruise', '2024-05-01', '2024-05-10', 1700),
       (26, 'Australia Cruise', '2024-06-01', '2024-06-10', 2000),
       (27, 'Africa Cruise', '2024-07-01', '2024-07-10', 1800);

--
INSERT
INTO ship (name, capacity, visited_ports, staff, route_id)
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

