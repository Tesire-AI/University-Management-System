CREATE TABLE students (
                          id         VARCHAR(20) PRIMARY KEY,
                          first_name VARCHAR(50)  NOT NULL,
                          last_name  VARCHAR(50)  NOT NULL,
                          email      VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE courses (
                         Coursecode     VARCHAR(20) PRIMARY KEY,
                         title    VARCHAR(100) NOT NULL,
                         credits  INT NOT NULL CHECK (credits > 0),
                         capacity INT NOT NULL CHECK (capacity > 0)
);

CREATE TABLE enrollments (
                             student_id  VARCHAR(20) REFERENCES students(id) ON DELETE CASCADE,
                             course_code VARCHAR(20) REFERENCES courses(Coursecode) ON DELETE CASCADE,
                             grade       DOUBLE PRECISION CHECK (grade >= 0 AND grade <= 100),
                             PRIMARY KEY (student_id, course_code)
);

INSERT INTO students VALUES ('S001', 'Bana', 'Cephas', 'bana@gmail.com');
INSERT INTO students VALUES ('S002', 'Ryan', 'Frank', 'ryan@gmail.com');
INSERT INTO courses VALUES ('C101', 'Computer Science', 3, 3);
INSERT INTO courses VALUES ('C102', 'Computer Maintenance', 3, 3);
INSERT INTO enrollments VALUES ('S001', 'C101', 85);
INSERT INTO enrollments VALUES ('S002', 'C101', NULL);



SELECT s.first_name, c.Coursecode,c.title, e.grade
FROM students s
         JOIN enrollments e ON s.id = e.student_id
         JOIN courses c ON c.Coursecode = e.course_code;

