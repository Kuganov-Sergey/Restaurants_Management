create table if not exists my_user
(
    id                int primary key auto_increment,
    name              varchar(20)  not null,
    surname           varchar(20)  not null,
    lastname          varchar(20)  not null,
    email             varchar(255) not null,
    registration_date timestamp not null default current_timestamp
);
