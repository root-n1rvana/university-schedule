 -- isProfessor
 CREATE FUNCTION isProfessor (userId BIGINT)
 RETURNS BOOLEAN
 language plpgsql
 AS
 $$
 BEGIN
     if exists (SELECT * FROM users_roles ur join roles r on ur.role_id=r.id WHERE ur.user_id=userId and r.name='ROLE_PROFESSOR') THEN
         return true;
     else
         raise exception 'user % has not role ROLE_PROFESSOR', userId;
     END if;
 END;
 $$;

 -- isStudent
 CREATE FUNCTION isStudent (userId BIGINT)
 RETURNS BOOLEAN
 language plpgsql
 AS
 $$
 BEGIN
     if exists (SELECT * FROM users_roles ur join roles r on ur.role_id=r.id WHERE ur.user_id=userId and r.name='ROLE_STUDENT') THEN
         return true;
     else
         raise exception 'user with id=% has not role ROLE_STUDENT', userId;
     end if;
 END;
 $$;

 -- isUserCorrespondingToGroup
 CREATE FUNCTION isUserCorrespondingToGroup (userId BIGINT, groupId BIGINT)
 RETURNS BOOLEAN
 language plpgsql
 AS
 $$
 DECLARE
    groupName varchar(20);
 BEGIN
     select name into groupName from groups where id = groupId;

     if groupName = 'admin' and NOT EXISTS (SELECT * FROM users_roles ur join roles r on ur.role_id=r.id WHERE ur.user_id=userId and r.name='ROLE_ADMIN') THEN
         raise exception 'user with id=% does not belong to admin group', userId;
     elseif groupName = 'professor' and NOT EXISTS (SELECT * FROM users_roles ur join roles r on ur.role_id=r.id WHERE ur.user_id=userId and r.name='ROLE_PROFESSOR') THEN
         raise exception 'user with id=% does not belong to professor group', userId;
     END if;
     return true;
 END;
 $$;

 -- isNotAdminOrProfessorGroup
 CREATE FUNCTION isNotAdminOrProfessorGroup (groupId BIGINT)
 RETURNS BOOLEAN
 language plpgsql
 AS
 $$
 DECLARE
    groupName varchar(20);
 BEGIN
     select name into groupName from groups where id = groupId;

     if groupName = 'admin' or groupName = 'professor' THEN
         raise exception 'should not be admin or professor group';
     END if;
     return true;
 END;
 $$;

CREATE TYPE role_name AS ENUM ('ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_STUDENT');

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
    group_id  BIGINT      REFERENCES groups (id),
    CONSTRAINT user_group_ck CHECK(isUserCorrespondingToGroup(id, group_id) = 'true')
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

CREATE TABLE student_groups_courses
(
    group_id   BIGINT REFERENCES groups (id) ON UPDATE CASCADE ON DELETE CASCADE,
    course_id  BIGINT REFERENCES courses (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT group_course UNIQUE (group_id, course_id),
    CONSTRAINT students_courses_ck CHECK(isNotAdminOrProfessorGroup(group_id) = 'true')
);

CREATE TABLE professors_courses
(
    professor_id    BIGINT REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    course_id       BIGINT REFERENCES courses (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT professors_courses_un UNIQUE (professor_id, course_id),
    CONSTRAINT professors_courses_ck CHECK(isProfessor(professor_id) = true)
);

CREATE TABLE users_roles
(
    user_id BIGINT REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    role_id BIGINT REFERENCES roles (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT user_role UNIQUE (user_id, role_id)
);
