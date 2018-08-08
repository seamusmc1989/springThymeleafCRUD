# springThymeleafCRUD
Spring boot boilerplate code for Thymeleaf CRUD, MySQL, import.sql intilize data, server-side/client validation and spring security custom impl

To setup the MySQL db.
Run these commands:

create database 'springbootuserdemo';

CREATE USER 'smccarthy'@'localhost' IDENTIFIED BY 'newrootpassword';

GRANT ALL PRIVILEGES ON * . * TO 'smccarthy'@'localhost';

# The project has the following code features

Import.sql in the main resources folder to initialise some dummy data in the database

CRUD Thyme-leaf spring boot 

Custom RepositoryImpl in the RoleRepositoryCustomImpl class

Implementation of criterion and conjunctions in repository classes 

Server-side and client side validation for the /newCar getMapping

Custom mapped constraint bean for the UserPasswordValidator 

Spring Security using custom userDetailsService class

