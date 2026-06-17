create database show_multiple_tables;
use show_multiple_tables;

show tables;

INSERT INTO students(id,name,address)
VALUES
(1,'Rupa','Delhi'),
(2,'Rahul','Mumbai'),
(3,'Amit','Pune');

INSERT INTO courses(id,course_name,student_id)
VALUES
(1,'Java',1),
(2,'React',1),
(3,'React Native',1),
(4,'Spring Boot',2),
(5,'Angular',2),
(6,'Python',3),
(7,'Django',3);

INSERT INTO coursedetails(id,duration,price,course_id)
VALUES
(1,'3 Months',10000,1),
(2,'2 Months',12000,2),
(3,'4 Months',15000,3),
(4,'5 Months',18000,4),
(5,'2 Months',9000,5),
(6,'3 Months',11000,6),
(7,'4 Months',14000,7);