CREATE DATABASE IF NOT EXISTS cas;
USE cas;

CREATE TABLE IF NOT EXISTS student (
     id          int            NOT NULL
    ,first_name  varchar(255)   NOT NULL
    ,last_name   varchar(255)   NOT NULL

    ,PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS semester (
     id         varchar(255)    NOT NULL
    ,name       varchar(255)    NOT NULL

    ,PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS module (
     id         varchar(255)    NOT NULL
    ,name       varchar(255)    NOT NULL

    ,PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS result (
     student_id int             NOT NULL
    ,module_id  varchar(255)    NOT NULL
    ,date       timestamp       NOT NULL
    ,grade      numeric(4,2)

    ,PRIMARY KEY (student_id, module_id, date)
    ,CONSTRAINT fk_result_student_id FOREIGN KEY (student_id) REFERENCES student(id)
    ,CONSTRAINT fk_result_module_id FOREIGN KEY (module_id) REFERENCES module(id)
);

CREATE TABLE IF NOT EXISTS student_semester (
     student_id  int            NOT NULL
    ,semester_id varchar(255)   NOT NULL

    ,PRIMARY KEY (student_id, semester_id)
    ,CONSTRAINT fk_student_semester_student_id FOREIGN KEY (student_id) REFERENCES student(id)
    ,CONSTRAINT fk_student_semester_semester_id FOREIGN KEY (semester_id) REFERENCES semester(id)
);

CREATE TABLE IF NOT EXISTS semester_module (
     semester_id varchar(255)   NOT NULL
    ,module_id   varchar(255)   NOT NULL

    ,PRIMARY KEY (semester_id, module_id)
    ,CONSTRAINT fk_semester_module_semester_id FOREIGN KEY (semester_id) REFERENCES semester(id)
    ,CONSTRAINT fk_semester_module_module_id FOREIGN KEY (module_id) REFERENCES module(id)
);