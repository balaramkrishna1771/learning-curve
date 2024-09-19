CREATE TABLE student_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    age TINYINT UNSIGNED NOT NULL,
    gender VARCHAR(10),
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);


select * from student_info;-- 

delete from student_info where id = 47;
commit;

-- Insert for Alice Johnson
INSERT INTO student_info (id, name, email, age, gender, username, password)
VALUES (12, 'Alice Johnson', 'alice.johnson@example.com', 22, 'Female', 'alicej', 'P@ssw0rd123');

-- Insert for Bob Smith
INSERT INTO student_info (id, name, email, age, gender, username, password)
VALUES (13, 'Bob Smith', 'bob.smith@example.com', 25, 'Male', 'bobsmith', 'B0b$3cr3t!');

-- r Charlie Brown
INSERT INTO student_info (id, name, email, age, gender, username, password)
VALUES (31, 'Charlie Brown', 'charlie.brown@example.com', 20, 'Male', 'charlieb', 'C!h@rli3#2024');

-- Insert for Diana Prince
INSERT INTO student_info (id, name, email, age, gender, username, password)
VALUES (42, 'Diana Prince', 'diana.prince@example.com', 23, 'Female', 'dianapr', 'D!@n@2024#');