INSERT INTO groups (name) VALUES ('admin');
INSERT INTO groups (name) VALUES ('professor');
INSERT INTO groups (name) VALUES ('GR-1');
INSERT INTO groups (name) VALUES ('GR-2');
INSERT INTO groups (name) VALUES ('GR-3');
INSERT INTO groups (name) VALUES ('GR-4');
INSERT INTO groups (name) VALUES ('GR-5');
INSERT INTO groups (name) VALUES ('GR-6');
INSERT INTO groups (name) VALUES ('Unselected');


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

INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_STUDENT'), ('ROLE_PROFESSOR');

INSERT INTO lessons_time (lesson_time) VALUES ('8:00-9:30'), ('9:45-11:15'), ('11:30-13:00'), ('13:30-15:00'), ('15:15-16:45');

INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('admin', '', 'admin', '$2a$10$gqHrslMttQWSsDSVRTK1OehkkBiXsJ/a4z2OURU./dizwOQu5Lovu', 1);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Victoria', 'Nazarova', 'teach1', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 2);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Oksana', 'Boiko', 'teach2', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 2);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Diana', 'Belova', 'teach3', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 2);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Vitaliy', 'Rojkov', 'teach4', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 2);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Artur', 'Morozov', 'teach5', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 2);

INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Pavel', 'Ivanov', 'std1', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 3);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Gleb', 'Burov', 'std2', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 3);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Elena', 'Nosova', 'std3', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 3);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Boris', 'Pahomov', 'std4', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 3);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Maksim', 'Sidorov', 'std5', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 3);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Alisa', 'Mironova', 'std6', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 3);

INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Nikolay', 'Ponomarev', 'std7', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 4);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Inna', 'Lapina', 'std8', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 4);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Roman', 'Smirnov', 'std9', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 4);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Ivan', 'Lazarev', 'std10', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 4);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Anna', 'Bezpalova', 'std11', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 4);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Igor', 'Sokolov', 'std12', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 4);

INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Olga', 'Pavlova', 'std13', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 5);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Marina', 'Tihonova', 'std14', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 5);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Pavel', 'Bezpalov', 'std15', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 5);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Ivan', 'Lazarev', 'std16', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 5);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Igor', 'Pavlov', 'std17', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 5);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Boris', 'Sokolov', 'std18', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 5);

INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Olga', 'Pahomova', 'std19', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 6);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Marina', 'Lapina', 'std20', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 6);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Pavel', 'Tihonov', 'std21', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 6);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Ivan', 'Lazarev', 'std22', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 6);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Igor', 'Pavlov', 'std23', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 6);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Boris', 'Pahomov', 'std24', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 6);

INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Olga', 'Sokolova', 'std25', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 7);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Marina', 'Belova', 'std26', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 7);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Maksim', 'Fomin', 'std27', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 7);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Artur', 'Morozov', 'std28', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 7);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Eugenia', 'Rojkova', 'std29', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 7);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Elena', 'Mironova', 'std30', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 7);

INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Inna', 'Ivanova', 'std31', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 8);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Anna', 'Lapina', 'std32', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 8);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Alisa', 'Popova', 'std33', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 8);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Irina', 'Burova', 'std34', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 8);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Diana', 'Sidorova', 'std35', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 8);
INSERT INTO users (firstname, lastname, login, password, group_id) VALUES ('Olga', 'Popova', 'std36', '$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG', 8);

INSERT INTO users_courses VALUES (8, 6);
INSERT INTO users_courses VALUES (9, 5);
INSERT INTO users_courses VALUES (10, 9);
INSERT INTO users_courses VALUES (11, 3);
INSERT INTO users_courses VALUES (12, 5);
INSERT INTO users_courses VALUES (13, 2);
INSERT INTO users_courses VALUES (14, 1);
INSERT INTO users_courses VALUES (15, 7);
INSERT INTO users_courses VALUES (16, 3);
INSERT INTO users_courses VALUES (17, 7);
INSERT INTO users_courses VALUES (18, 10);
INSERT INTO users_courses VALUES (18, 9);
INSERT INTO users_courses VALUES (19, 4);
INSERT INTO users_courses VALUES (20, 9);
INSERT INTO users_courses VALUES (21, 5);
INSERT INTO users_courses VALUES (21, 10);
INSERT INTO users_courses VALUES (22, 6);
INSERT INTO users_courses VALUES (22, 8);
INSERT INTO users_courses VALUES (23, 6);
INSERT INTO users_courses VALUES (24, 4);
INSERT INTO users_courses VALUES (25, 10);
INSERT INTO users_courses VALUES (25, 2);
INSERT INTO users_courses VALUES (26, 2);
INSERT INTO users_courses VALUES (27, 1);
INSERT INTO users_courses VALUES (26, 6);
INSERT INTO users_courses VALUES (26, 8);
INSERT INTO users_courses VALUES (29, 1);
INSERT INTO users_courses VALUES (30, 9);
INSERT INTO users_courses VALUES (31, 1);
INSERT INTO users_courses VALUES (32, 5);
INSERT INTO users_courses VALUES (33, 6);
INSERT INTO users_courses VALUES (33, 7);
INSERT INTO users_courses VALUES (34, 6);
INSERT INTO users_courses VALUES (35, 4);
INSERT INTO users_courses VALUES (36, 1);
INSERT INTO users_courses VALUES (37, 2);
INSERT INTO users_courses VALUES (37, 10);
INSERT INTO users_courses VALUES (38, 6);
INSERT INTO users_courses VALUES (39, 6);
INSERT INTO users_courses VALUES (40, 5);
INSERT INTO users_courses VALUES (41, 1);
INSERT INTO users_courses VALUES (42, 2);
INSERT INTO users_courses VALUES (42, 10);

INSERT INTO users_roles VALUES (1, 1);
INSERT INTO users_roles VALUES (1, 3);
INSERT INTO users_roles VALUES (1, 2);
INSERT INTO users_roles VALUES (2, 3);
INSERT INTO users_roles VALUES (3, 3);
INSERT INTO users_roles VALUES (4, 3);
INSERT INTO users_roles VALUES (5, 3);
INSERT INTO users_roles VALUES (6, 3);
INSERT INTO users_roles VALUES (7, 2);
INSERT INTO users_roles VALUES (8, 2);
INSERT INTO users_roles VALUES (9, 2);
INSERT INTO users_roles VALUES (10, 2);
INSERT INTO users_roles VALUES (11, 2);
INSERT INTO users_roles VALUES (12, 2);
INSERT INTO users_roles VALUES (13, 2);
INSERT INTO users_roles VALUES (14, 2);
INSERT INTO users_roles VALUES (15, 2);
INSERT INTO users_roles VALUES (16, 2);
INSERT INTO users_roles VALUES (17, 2);
INSERT INTO users_roles VALUES (18, 2);
INSERT INTO users_roles VALUES (19, 2);
INSERT INTO users_roles VALUES (20, 2);
INSERT INTO users_roles VALUES (21, 2);
INSERT INTO users_roles VALUES (22, 2);
INSERT INTO users_roles VALUES (23, 2);
INSERT INTO users_roles VALUES (24, 2);
INSERT INTO users_roles VALUES (25, 2);
INSERT INTO users_roles VALUES (26, 2);
INSERT INTO users_roles VALUES (27, 2);
INSERT INTO users_roles VALUES (28, 2);
INSERT INTO users_roles VALUES (29, 2);
INSERT INTO users_roles VALUES (30, 2);
INSERT INTO users_roles VALUES (31, 2);
INSERT INTO users_roles VALUES (32, 2);
INSERT INTO users_roles VALUES (33, 2);
INSERT INTO users_roles VALUES (34, 2);
INSERT INTO users_roles VALUES (35, 2);
INSERT INTO users_roles VALUES (36, 2);
INSERT INTO users_roles VALUES (37, 2);
INSERT INTO users_roles VALUES (38, 2);
INSERT INTO users_roles VALUES (39, 2);
INSERT INTO users_roles VALUES (40, 2);
INSERT INTO users_roles VALUES (41, 2);
INSERT INTO users_roles VALUES (42, 2);

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