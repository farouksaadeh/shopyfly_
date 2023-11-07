# Shopyfly

create table products (id int auto_increment primary key, name varchar(255), price decimal (10, 2) not null, size varchar(255), brand varchar(255), stock int);

insert into products (name, price, size, brand, stock) values ('Nike Air Force 1', 115.00, 'SIZE_41', 'Nike', 5);