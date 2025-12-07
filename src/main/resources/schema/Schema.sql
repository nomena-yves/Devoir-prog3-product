create table product(
    id int primary key,
    name varchar (40),
    price int,
    creation_datime timestamp
);

create table product_category(
    id int primary key,
    name varchar(50),
    id_product int,
    constraint fk_product foreign key (id_product) references product(id)
);