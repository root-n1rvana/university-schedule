INSERT INTO groups (name) VALUES ('admin');
INSERT INTO groups (name) VALUES ('professor');
INSERT INTO groups (name) VALUES ('GR-1');
INSERT INTO groups (name) VALUES ('GR-2');
INSERT INTO groups (name) VALUES ('GR-3');
INSERT INTO groups (name) VALUES ('GR-4');
INSERT INTO groups (name) VALUES ('GR-5');
INSERT INTO groups (name) VALUES ('GR-6');

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

INSERT INTO roles (name) VALUES ('ADMIN'), ('STUDENT'), ('PROFESSOR');

INSERT INTO lessons_time (lesson_time) VALUES ('8:00-9:30'), ('9:45-11:15'), ('11:30-13:00'), ('13:30-15:00'), ('15:15-16:45');

INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('admin', '', 'pass', 1, 1);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Victoria', 'Nazarova', 'pass', 3, 2);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Oksana', 'Boiko', 'pass', 3, 2);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Diana', 'Belova', 'pass', 3, 2);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Vitaliy', 'Rojkov', 'pass', 3, 2);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Artur', 'Morozov', 'pass', 3, 2);

INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Pavel', 'Ivanov', 'pass', 2, 3);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Gleb', 'Burov', 'pass', 2, 3);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Elena', 'Nosova', 'pass', 2, 3);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Boris', 'Pahomov', 'pass', 2, 3);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Maksim', 'Sidorov', 'pass', 2, 3);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Alisa', 'Mironova', 'pass', 2, 3);

INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Nikolay', 'Ponomarev', 'pass', 2, 4);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Inna', 'Lapina', 'pass', 2, 4);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Roman', 'Smirnov', 'pass', 2, 4);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Ivan', 'Lazarev', 'pass', 2, 4);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Anna', 'Bezpalova', 'pass', 2, 4);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Igor', 'Sokolov', 'pass', 2, 4);

INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Olga', 'Pavlova', 'pass', 2, 5);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Marina', 'Tihonova', 'pass', 2, 5);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Pavel', 'Bezpalov', 'pass', 2, 5);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Ivan', 'Lazarev', 'pass', 2, 5);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Igor', 'Pavlov', 'pass', 2, 5);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Boris', 'Sokolov', 'pass', 2, 5);

INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Olga', 'Pahomova', 'pass', 2, 6);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Marina', 'Lapina', 'pass', 2, 6);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Pavel', 'Tihonov', 'pass', 2, 6);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Ivan', 'Lazarev', 'pass', 2, 6);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Igor', 'Pavlov', 'pass', 2, 6);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Boris', 'Pahomov', 'pass', 2, 6);

INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Olga', 'Sokolova', 'pass', 2, 7);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Marina', 'Belova', 'pass', 2, 7);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Maksim', 'Fomin', 'pass', 2, 7);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Artur', 'Morozov', 'pass', 2, 7);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Eugenia', 'Rojkova', 'pass', 2, 7);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Elena', 'Mironova', 'pass', 2, 7);

INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Inna', 'Ivanova', 'pass', 2, 8);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Anna', 'Lapina', 'pass', 2, 8);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Alisa', 'Popova', 'pass', 2, 8);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Irina', 'Burova', 'pass', 2, 8);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Diana', 'Sidorova', 'pass', 2, 8);
INSERT INTO users (firstname, lastname, password, role_id, group_id) VALUES ('Olga', 'Popova', 'pass', 2, 8);

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