INSERT INTO users (firstname, lastname, login, password) VALUES ('alex', 'Collier', 'test1', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('John', 'Collier', 'test2', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users_roles VALUES (43, 1);
INSERT INTO users_roles VALUES (44, 1);

update users set group_id = 1 where login = 'test1';
update users set group_id = 1 where login = 'test2';