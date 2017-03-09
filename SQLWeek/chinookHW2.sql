-- 2.1 SELECT
-- select all records from the employee table
select * from employee;
-- select all records from the employee table where the last name is King
select * from employee where lastname = 'King';
-- select all records from the employee table where the first name is andrew
-- and reportsto is NULL
select * from employee where firstname = 'Andrew' and reportsto is null;

-- 2.2 ORDER BY
-- select all albums in album table and sort results set in descending order by title
select * from album order by title desc;
-- select first name from customer and sort result set in ascending order by city
select firstname from customer order by city;

-- 2.3 INSERT INTO
-- insert two new records into genre table
insert into genre (genreid, name) values (26, 'Space Pop');
insert into genre (genreid, name) values (27, 'Galaxy Baroque');
select * from genre;
-- insert two new records into employee table
insert into employee () values ();
insert into employee () values ();
-- insert two new records into customer table
insert into customer () values ();
insert into customer () values ();