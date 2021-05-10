create database euris;
use euris;

create table articles (
	code int primary key auto_increment,
    name varchar(50),
    cost varchar(50)
);

select * from articles;

insert into articles (name, cost) values
('Book', '4p 2s 3d'),
('Armchair', '9p 4s 5d'),
('Gloves', '3p 3s 8d'),
('Chandelier', '8p 3s 8d'),
('Phone', '14p 8s 3d'),
('Pencil', '3p 3s 2d'),
('Fork', '5p 14s 11d'),
('Peluche', '3p 2s 3d'),
('Desk', '12p 5s 10d'),
('Pen', '1p 3s 3d');