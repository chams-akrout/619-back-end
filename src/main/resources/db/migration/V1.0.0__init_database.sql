create table category
(
    id int auto_increment primary key,
    name                   varchar(255) null

);
create table product
(
    id int auto_increment primary key,
    name                   varchar(255) null,
    factory                 varchar(255) null,
    image                 varchar(255) null,
    points                 int null,
    category_id            int null,
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category (id)

);


