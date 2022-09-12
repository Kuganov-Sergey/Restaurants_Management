create table if not exists restaurants
(
    id          int primary key auto_increment,
    name        varchar(20) not null,
    description varchar(255)       not null
);