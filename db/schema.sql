
CREATE TABLE students (
                          id VARCHAR(50) PRIMARY KEY,
                          firstname VARCHAR(100) NOT NULL,
                          lastname VARCHAR(100) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          student_type VARCHAR(50) NOT NULL check(student_type in('Graduate student','Undergraduate student'))
);


CREATE TABLE courses (
                         course_code VARCHAR(50) PRIMARY KEY,
                         course_name VARCHAR(100) NOT NULL,
                         instructor_id VARCHAR(50)
);


CREATE TABLE enrollments (
                             enrollment_id SERIAL PRIMARY KEY,
                             student_id VARCHAR(50) REFERENCES students(id) ON DELETE CASCADE,
                             course_code VARCHAR(50) REFERENCES courses(course_code) ON DELETE CASCADE,
                             enrollment_date DATE,
                             grade DECIMAL(5, 2) DEFAULT 0.0
);