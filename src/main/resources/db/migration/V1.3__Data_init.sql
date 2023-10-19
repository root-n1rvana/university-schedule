INSERT INTO groups (name) VALUES ('admin');
INSERT INTO groups (name) VALUES ('professor');
INSERT INTO groups (name) VALUES ('GR-1');
INSERT INTO groups (name) VALUES ('GR-2');
INSERT INTO groups (name) VALUES ('GR-3');
INSERT INTO groups (name) VALUES ('GR-4');
INSERT INTO groups (name) VALUES ('GR-5');
INSERT INTO groups (name) VALUES ('GR-6');
INSERT INTO groups (name) VALUES ('');

INSERT INTO courses (name, description) VALUES ('History', 'the study of change over time');
INSERT INTO courses (name, description) VALUES ('Topography', 'the study of the land surface');
INSERT INTO courses (name, description) VALUES ('Chemistry', 'the science that deals with the properties, composition, and structure of substances');
INSERT INTO courses (name, description) VALUES ('Geography', 'the study of places and the relationships between people and their environments');
INSERT INTO courses (name, description) VALUES ('Astronomy', 'the study of everything in the universe beyond Earth''s atmosphere');
INSERT INTO courses (name, description) VALUES ('Physical_Ed', 'the teaching and leading of physical activity');
INSERT INTO courses (name, description) VALUES ('Math', 'the science that deals with the logic of shape, quantity and arrangement');
INSERT INTO courses (name, description) VALUES ('Biology', 'a branch of science that deals with living organisms and their vital processes');
INSERT INTO courses (name, description) VALUES ('English', 'introduction to english human language');
INSERT INTO courses (name, description) VALUES ('Physics', 'science that deals with the structure of matter');

INSERT INTO rooms (label, description) VALUES ('A1', 'Some description');
INSERT INTO rooms (label, description) VALUES ('A2', 'Some description');
INSERT INTO rooms (label, description) VALUES ('A3', 'Some description');
INSERT INTO rooms (label, description) VALUES ('A4', 'Some description');
INSERT INTO rooms (label, description) VALUES ('A5', 'Some description');
INSERT INTO rooms (label, description) VALUES ('A6', 'Some description');
INSERT INTO rooms (label, description) VALUES ('A7', 'Some description');
INSERT INTO rooms (label, description) VALUES ('A8', 'Some description');
INSERT INTO rooms (label, description) VALUES ('A9', 'Some description');
INSERT INTO rooms (label, description) VALUES ('A0', 'Some description');

INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_PROFESSOR'), ('ROLE_STUDENT');

INSERT INTO lessons_time (lesson_time) VALUES ('8:00-9:30'), ('9:45-11:15'), ('11:30-13:00'), ('13:30-15:00'), ('15:15-16:45');

INSERT INTO users (firstname, lastname, login, password)
VALUES ('admin', 'admin', 'admin', '$2a$10$gqHrslMttQWSsDSVRTK1OehkkBiXsJ/a4z2OURU./dizwOQu5Lovu');

INSERT INTO users (firstname, lastname, login, password)
VALUES ('Victoria', 'Nazarova', 'teach1', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password)
VALUES ('Oksana', 'Boiko', 'teach2', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password)
VALUES ('Diana', 'Belova', 'teach3', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password)
VALUES ('Vitaliy', 'Rojkov', 'teach4', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password)
VALUES ('Artur', 'Morozov', 'teach5', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password)
VALUES ('Pavel', 'Ivanov', 'std1', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password)
VALUES ('Gleb', 'Burov', 'std2', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password)
VALUES ('Elena', 'Nosova', 'std3', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password)
VALUES ('Boris', 'Pahomov', 'std4', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password)
VALUES ('Maksim', 'Sidorov', 'std5', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password)
VALUES ('Alisa', 'Mironova', 'std6', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password) VALUES ('Nikolay', 'Ponomarev', 'std7', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Inna', 'Lapina', 'std8', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Roman', 'Smirnov', 'std9', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Ivan', 'Lazarev', 'std10', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Anna', 'Bezpalova', 'std11', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Igor', 'Sokolov', 'std12', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password) VALUES ('Olga', 'Pavlova', 'std13', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Marina', 'Tihonova', 'std14', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Pavel', 'Bezpalov', 'std15', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Ivan', 'Lazarev', 'std16', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Igor', 'Pavlov', 'std17', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Boris', 'Sokolov', 'std18', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password) VALUES ('Olga', 'Pahomova', 'std19', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Marina', 'Lapina', 'std20', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Pavel', 'Tihonov', 'std21', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Ivan', 'Lazarev', 'std22', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Igor', 'Pavlov', 'std23', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Boris', 'Pahomov', 'std24', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password) VALUES ('Olga', 'Sokolova', 'std25', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Marina', 'Belova', 'std26', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Maksim', 'Fomin', 'std27', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Artur', 'Morozov', 'std28', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Eugenia', 'Rojkova', 'std29', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Elena', 'Mironova', 'std30', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users (firstname, lastname, login, password) VALUES ('Inna', 'Ivanova', 'std31', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Anna', 'Lapina', 'std32', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Alisa', 'Popova', 'std33', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Irina', 'Burova', 'std34', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Diana', 'Sidorova', 'std35', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');
INSERT INTO users (firstname, lastname, login, password) VALUES ('Olga', 'Popova', 'std36', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG');

INSERT INTO users_roles VALUES (1, 1);
INSERT INTO users_roles VALUES (1, 2);
INSERT INTO users_roles VALUES (2, 2);
INSERT INTO users_roles VALUES (3, 2);
INSERT INTO users_roles VALUES (4, 2);
INSERT INTO users_roles VALUES (5, 2);
INSERT INTO users_roles VALUES (6, 2);
INSERT INTO users_roles VALUES (7, 3);
INSERT INTO users_roles VALUES (8, 3);
INSERT INTO users_roles VALUES (9, 3);
INSERT INTO users_roles VALUES (10, 3);
INSERT INTO users_roles VALUES (11, 3);
INSERT INTO users_roles VALUES (12, 3);
INSERT INTO users_roles VALUES (13, 3);
INSERT INTO users_roles VALUES (14, 3);
INSERT INTO users_roles VALUES (15, 3);
INSERT INTO users_roles VALUES (16, 3);
INSERT INTO users_roles VALUES (17, 3);
INSERT INTO users_roles VALUES (18, 3);
INSERT INTO users_roles VALUES (19, 3);
INSERT INTO users_roles VALUES (20, 3);
INSERT INTO users_roles VALUES (21, 3);
INSERT INTO users_roles VALUES (22, 3);
INSERT INTO users_roles VALUES (23, 3);
INSERT INTO users_roles VALUES (24, 3);
INSERT INTO users_roles VALUES (25, 3);
INSERT INTO users_roles VALUES (26, 3);
INSERT INTO users_roles VALUES (27, 3);
INSERT INTO users_roles VALUES (28, 3);
INSERT INTO users_roles VALUES (29, 3);
INSERT INTO users_roles VALUES (30, 3);
INSERT INTO users_roles VALUES (31, 3);
INSERT INTO users_roles VALUES (32, 3);
INSERT INTO users_roles VALUES (33, 3);
INSERT INTO users_roles VALUES (34, 3);
INSERT INTO users_roles VALUES (35, 3);
INSERT INTO users_roles VALUES (36, 3);
INSERT INTO users_roles VALUES (37, 3);
INSERT INTO users_roles VALUES (38, 3);
INSERT INTO users_roles VALUES (39, 3);
INSERT INTO users_roles VALUES (40, 3);
INSERT INTO users_roles VALUES (41, 3);
INSERT INTO users_roles VALUES (42, 3);

update users set group_id = 1 where login = 'admin';

update users set group_id = 2 where login = 'teach1';
update users set group_id = 2 where login = 'teach2';
update users set group_id = 2 where login = 'teach3';
update users set group_id = 2 where login = 'teach4';
update users set group_id = 2 where login = 'teach5';

update users set group_id = 3 where login = 'std1';
update users set group_id = 3 where login = 'std2';
update users set group_id = 3 where login = 'std3';
update users set group_id = 3 where login = 'std4';
update users set group_id = 3 where login = 'std5';
update users set group_id = 3 where login = 'std6';
update users set group_id = 4 where login = 'std7';
update users set group_id = 4 where login = 'std8';
update users set group_id = 4 where login = 'std9';
update users set group_id = 4 where login = 'std10';
update users set group_id = 4 where login = 'std11';
update users set group_id = 4 where login = 'std12';
update users set group_id = 5 where login = 'std13';
update users set group_id = 5 where login = 'std14';
update users set group_id = 5 where login = 'std15';
update users set group_id = 5 where login = 'std16';
update users set group_id = 5 where login = 'std17';
update users set group_id = 5 where login = 'std18';
update users set group_id = 6 where login = 'std19';
update users set group_id = 6 where login = 'std20';
update users set group_id = 6 where login = 'std21';
update users set group_id = 6 where login = 'std22';
update users set group_id = 6 where login = 'std23';
update users set group_id = 6 where login = 'std24';
update users set group_id = 7 where login = 'std25';
update users set group_id = 7 where login = 'std26';
update users set group_id = 7 where login = 'std27';
update users set group_id = 7 where login = 'std28';
update users set group_id = 7 where login = 'std29';
update users set group_id = 7 where login = 'std30';
update users set group_id = 8 where login = 'std31';
update users set group_id = 8 where login = 'std32';
update users set group_id = 8 where login = 'std33';
update users set group_id = 8 where login = 'std34';
update users set group_id = 8 where login = 'std35';
update users set group_id = 8 where login = 'std36';

INSERT INTO professors_courses VALUES (2, 1);
INSERT INTO professors_courses VALUES (3, 2);
INSERT INTO professors_courses VALUES (4, 3);
INSERT INTO professors_courses VALUES (5, 4);
INSERT INTO professors_courses VALUES (6, 5);

INSERT INTO student_groups_courses VALUES (3, 1);
INSERT INTO student_groups_courses VALUES (3, 2);
INSERT INTO student_groups_courses VALUES (3, 3);
INSERT INTO student_groups_courses VALUES (4, 4);
INSERT INTO student_groups_courses VALUES (5, 4);
INSERT INTO student_groups_courses VALUES (5, 5);
INSERT INTO student_groups_courses VALUES (6, 6);

INSERT INTO schedules (date) VALUES ('2023-09-04');
INSERT INTO schedules (date) VALUES ('2023-09-05');
INSERT INTO schedules (date) VALUES ('2023-09-06');
INSERT INTO schedules (date) VALUES ('2023-10-10');
INSERT INTO schedules (date) VALUES ('2023-10-11');
INSERT INTO schedules (date) VALUES ('2023-10-12');

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 1, 3, 1, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (1, 2, 3, 1, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (4, 3, 3, 1, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (10, 4, 3, 1, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (6, 5, 3, 1, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (8, 6, 4, 1, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (9, 7, 4, 1, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (5, 8, 4, 1, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (3, 9, 4, 1, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (2, 10, 4, 1, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (4, 3, 5, 2, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (8, 6, 5, 2, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 1, 5, 2, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (6, 5, 5, 2, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (1, 2, 5, 2, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (2, 10, 6, 2, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (3, 9, 6, 2, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (9, 7, 6, 2, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (5, 8, 6, 2, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (10, 4, 6, 2, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (3, 5, 7, 3, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 1, 7, 3, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (2, 2, 7, 3, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (8, 3, 7, 3, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (5, 7, 7, 3, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 2, 8, 3, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (4, 3, 8, 3, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (1, 4, 8, 3, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 6, 8, 3, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (3, 8, 8, 3, 5);

---

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 1, 3, 4, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (1, 2, 3, 4, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (4, 3, 3, 4, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (10, 4, 3, 4, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (6, 5, 3, 4, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (8, 6, 4, 4, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (9, 7, 4, 4, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (5, 8, 4, 4, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (3, 9, 4, 4, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (2, 10, 4, 4, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (4, 3, 5, 5, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (8, 6, 5, 5, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 1, 5, 5, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (6, 5, 5, 5, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (1, 2, 5, 5, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (2, 10, 6, 5, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (3, 9, 6, 5, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (9, 7, 6, 5, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (5, 8, 6, 5, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (10, 4, 6, 5, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (3, 5, 7, 6, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 1, 7, 6, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (2, 2, 7, 6, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (8, 3, 7, 6, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (5, 7, 7, 6, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 2, 8, 6, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (4, 3, 8, 6, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (1, 4, 8, 6, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 6, 8, 6, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (3, 8, 8, 6, 5);