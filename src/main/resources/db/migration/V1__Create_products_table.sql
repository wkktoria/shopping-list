create table products(
    id int primary key auto_increment,
    name varchar(25) not null,
    quantity int,
    bought boolean
);