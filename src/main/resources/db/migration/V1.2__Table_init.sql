CREATE TYPE role_name AS ENUM ('ADMIN', 'STUDENT', 'PROFESSOR');

CREATE TABLE roles
(
    id   BIGSERIAL PRIMARY KEY,
    name role_name NOT NULL UNIQUE
);

CREATE TABLE groups
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(10) UNIQUE
);

CREATE TABLE users
(
    id        BIGSERIAL PRIMARY KEY,
    firstname VARCHAR(50),
    lastname  VARCHAR(50),
    login     VARCHAR(20) NOT NULL UNIQUE,
    password  VARCHAR     NOT NULL,
    group_id  BIGINT      NOT NULL REFERENCES groups (id)
);

CREATE TABLE courses
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(20) NOT NULL UNIQUE,
    description VARCHAR(100)
);

CREATE TABLE rooms
(
    id          BIGSERIAL PRIMARY KEY,
    label       VARCHAR(10) UNIQUE NOT NULL,
    description VARCHAR(100)
);

CREATE TABLE lessons_time
(
    id          BIGSERIAL PRIMARY KEY,
    lesson_time VARCHAR(15) NOT NULL UNIQUE
);

CREATE TABLE schedules
(
    id   BIGSERIAL PRIMARY KEY,
    date DATE
);

CREATE TABLE lessons
(
    id             BIGSERIAL PRIMARY KEY,
    course_id      BIGINT NOT NULL REFERENCES courses (id),
    room_id        BIGINT NOT NULL REFERENCES rooms (id),
    group_id       BIGINT NOT NULL REFERENCES groups (id),
    lesson_time_id BIGINT NOT NULL REFERENCES lessons_time (id),
    schedule_id    BIGINT NOT NULL REFERENCES schedules (id)
);

CREATE TABLE users_courses
(
    user_id   BIGINT REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    course_id BIGINT REFERENCES courses (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT user_course UNIQUE (user_id, course_id)
);

CREATE TABLE users_roles
(
    user_id BIGINT REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    role_id BIGINT REFERENCES courses (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT user_role UNIQUE (user_id, role_id)
);
