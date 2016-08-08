CREATE DATABASE students
WITH OWNER = taptigin
ENCODING = 'UTF8'
TABLESPACE = pg_default
LC_COLLATE = 'Russian_Russia.1251'
LC_CTYPE = 'Russian_Russia.1251'
CONNECTION LIMIT = -1;

CREATE TABLE  users
(ID int primary key, FirstName varchar(80), 
LastName varchar(80), MiddleName varchar(80),
Sex varchar(1),Age int, GroupName varchar(80),
FacultyName varchar(80),EnrollmentDate date,ReleaseDate date);

CREATE SEQUENCE studentId 
START with 0
increment by 1
maxvalue 50000
minvalue 0;

DO $addStudents$
DECLARE i int;
BEGIN
i = nextval('studentId');
FOR i IN 1..1000
LOOP
	i = nextval('studentId');
INSERT INTO users VALUES (i,'Иван ' || i,'Иванов ' || i,'Иванович ' || i,'M',20,'С-1','Строительный','1994-11-27','2003-09-18');
	i = nextval('studentId');
INSERT INTO users VALUES (i,'Петр ' || i,'Петров ' || i,'Петрович ' || i,'M',22,'С-1','Строительный','1995-12-07','2005-08-12');
	i = nextval('studentId');
INSERT INTO users VALUES (i,'Станислав' || i,'Сидоров' || i,'Борисович' || i,'M',21,'С-1','Строительный','1992-02-01','2004-09-18');
	i = nextval('studentId');
INSERT INTO users VALUES (i,'Мария' || i,'Щаснарисую' || i,'Муштайдонтовна' || i,'F',20,'Р-1','Художественный','1994-11-27','2003-09-18');
	i = nextval('studentId');
INSERT INTO users VALUES (i,'Тарас' || i,'Бульба' || i,'Иванович' || i,'M',17,'Э-1','Электротех','1994-11-27','2003-09-18');	
	i = nextval('studentId');
INSERT INTO users VALUES (i,'Лариса' || i,'Хочулова' || i,'Ивановна' || i,'F',20,'С-1','Строительный','1994-11-27','2003-09-18');
	i = nextval('studentId');
INSERT INTO users VALUES (i,'Екатирина' || i,'Молчалова' || i,'Евгеньевна' || i,'F',19,'С-1','Строительный','2002-11-27','2007-09-18');
	i = nextval('studentId');
INSERT INTO users VALUES (i,'Евгения' || i,'Пупкина' || i,'Владимировна' || i,'F',19,'Р-1','Художественный','2003-01-12','2006-07-01');
	i = nextval('studentId');
INSERT INTO users VALUES (i,'Владимир' || i,'Городецкий' || i,'Владимирович' || i,'M',21,'С-1','Строительный','1998-02-01','2006-09-18');
	i = nextval('studentId');
INSERT INTO users VALUES (i,'Андрей' || i,'Андреев' || i,'Владимирович' || i,'M',22,'Э-1','Электротех','1995-02-01','2003-09-18');
END LOOP;
END $addStudents$;



