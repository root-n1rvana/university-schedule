INSERT INTO groups (group_name) VALUES ('admin');
INSERT INTO groups (group_name) VALUES ('professor');
INSERT INTO groups (group_name) VALUES ('GR-1');
INSERT INTO groups (group_name) VALUES ('GR-2');
INSERT INTO groups (group_name) VALUES ('GR-3');
INSERT INTO groups (group_name) VALUES ('GR-4');
INSERT INTO groups (group_name) VALUES ('GR-5');
INSERT INTO groups (group_name) VALUES ('GR-6');

INSERT INTO courses (course_name, course_description) VALUES ('History', 'the study of change over time');
INSERT INTO courses (course_name, course_description) VALUES ('Topography', 'the study of the land surface');
INSERT INTO courses (course_name, course_description) VALUES ('Chemistry', 'the science that deals with the properties, composition, and structure of substances');
INSERT INTO courses (course_name, course_description) VALUES ('Geography', 'the study of places and the relationships between people and their environments');
INSERT INTO courses (course_name, course_description) VALUES ('Astronomy', 'the study of everything in the universe beyond Earth''s atmosphere');
INSERT INTO courses (course_name, course_description) VALUES ('Physical_Ed', 'the teaching and leading of physical activity');
INSERT INTO courses (course_name, course_description) VALUES ('Math', 'the science that deals with the logic of shape, quantity and arrangement');
INSERT INTO courses (course_name, course_description) VALUES ('Biology', 'a branch of science that deals with living organisms and their vital processes');
INSERT INTO courses (course_name, course_description) VALUES ('English', 'introduction to english human language');
INSERT INTO courses (course_name, course_description) VALUES ('Physics', 'science that deals with the structure of matter');

INSERT INTO rooms (room_label, room_description) VALUES ('A1', 'Some description');
INSERT INTO rooms (room_label, room_description) VALUES ('A2', 'Some description');
INSERT INTO rooms (room_label, room_description) VALUES ('A3', 'Some description');
INSERT INTO rooms (room_label, room_description) VALUES ('A4', 'Some description');
INSERT INTO rooms (room_label, room_description) VALUES ('A5', 'Some description');
INSERT INTO rooms (room_label, room_description) VALUES ('A6', 'Some description');
INSERT INTO rooms (room_label, room_description) VALUES ('A7', 'Some description');
INSERT INTO rooms (room_label, room_description) VALUES ('A8', 'Some description');
INSERT INTO rooms (room_label, room_description) VALUES ('A9', 'Some description');
INSERT INTO rooms (room_label, room_description) VALUES ('A0', 'Some description');

INSERT INTO roles (role_name) VALUES ('admin'), ('student'), ('professor');

INSERT INTO lessons_time (lesson_time) VALUES ('8:00-9:30'), ('9:45-11:15'), ('11:30-13:00'), ('13:30-15:00'), ('15:15-16:45');

INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('admin', '', 1, 1);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Victoria', 'Nazarova', 3, 2);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Oksana', 'Boiko', 3, 2);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Diana', 'Belova', 3, 2);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Vitaliy', 'Rojkov', 3, 2);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Artur', 'Morozov', 3, 2);

INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Pavel', 'Ivanov', 2, 3);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Gleb', 'Burov', 2, 3);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Elena', 'Nosova', 2, 3);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Boris', 'Pahomov', 2, 3);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Maksim', 'Sidorov', 2, 3);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Alisa', 'Mironova', 2, 3);

INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Nikolay', 'Ponomarev', 2, 4);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Inna', 'Lapina', 2, 4);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Roman', 'Smirnov', 2, 4);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Ivan', 'Lazarev', 2, 4);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Anna', 'Bezpalova', 2, 4);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Igor', 'Sokolov', 2, 4);

INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Olga', 'Pavlova', 2, 5);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Marina', 'Tihonova', 2, 5);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Pavel', 'Bezpalov', 2, 5);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Ivan', 'Lazarev', 2, 5);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Igor', 'Pavlov', 2, 5);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Boris', 'Sokolov', 2, 5);

INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Olga', 'Pahomova', 2, 6);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Marina', 'Lapina', 2, 6);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Pavel', 'Tihonov', 2, 6);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Ivan', 'Lazarev', 2, 6);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Igor', 'Pavlov', 2, 6);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Boris', 'Pahomov', 2, 6);

INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Olga', 'Sokolova', 2, 7);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Marina', 'Belova', 2, 7);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Maksim', 'Fomin', 2, 7);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Artur', 'Morozov', 2, 7);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Eugenia', 'Rojkova', 2, 7);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Elena', 'Mironova', 2, 7);

INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Inna', 'Ivanova', 2, 8);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Anna', 'Lapina', 2, 8);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Alisa', 'Popova', 2, 8);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Irina', 'Burova', 2, 8);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Diana', 'Sidorova', 2, 8);
INSERT INTO users (user_name, user_lastname, role_id, group_id) VALUES ('Olga', 'Popova', 2, 8);

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

INSERT INTO schedules (schedule_date) VALUES ('2023-10-10');
INSERT INTO schedules (schedule_date) VALUES ('2023-10-10');
INSERT INTO schedules (schedule_date) VALUES ('2023-10-10');
INSERT INTO schedules (schedule_date) VALUES ('2023-10-10');
INSERT INTO schedules (schedule_date) VALUES ('2023-10-10');

INSERT INTO schedules (schedule_date) VALUES ('2023-10-11');
INSERT INTO schedules (schedule_date) VALUES ('2023-10-11');
INSERT INTO schedules (schedule_date) VALUES ('2023-10-11');
INSERT INTO schedules (schedule_date) VALUES ('2023-10-11');
INSERT INTO schedules (schedule_date) VALUES ('2023-10-11');

INSERT INTO schedules (schedule_date) VALUES ('2023-10-12');
INSERT INTO schedules (schedule_date) VALUES ('2023-10-12');
INSERT INTO schedules (schedule_date) VALUES ('2023-10-12');
INSERT INTO schedules (schedule_date) VALUES ('2023-10-12');
INSERT INTO schedules (schedule_date) VALUES ('2023-10-12');

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 1, 1, 1, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (1, 2, 1, 2, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (4, 3, 1, 3, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (10, 4, 1, 4, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (6, 5, 1, 5, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (8, 6, 2, 1, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (9, 7, 2, 2, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (5, 8, 2, 3, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (3, 9, 2, 4, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (2, 10, 2, 5, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (4, 3, 3, 6, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (8, 6, 3, 7, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 1, 3, 8, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (6, 5, 3, 9, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (1, 2, 3, 10, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (2, 10, 4, 6, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (3, 9, 4, 7, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (9, 7, 4, 8, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (5, 8, 4, 9, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (10, 4, 4, 10, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (3, 5, 5, 11, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 1, 5, 12, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (2, 2, 5, 13, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (8, 3, 5, 14, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (5, 7, 5, 15, 5);

INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 2, 6, 11, 1);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (4, 3, 6, 12, 2);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (1, 4, 6, 13, 3);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (7, 6, 6, 14, 4);
INSERT INTO lessons (course_id, room_id, group_id, schedule_id, lesson_time_id) VALUES (3, 8, 6, 15, 5);