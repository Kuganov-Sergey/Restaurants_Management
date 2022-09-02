create table if not exists user_roles (
    id int primary key auto_increment,
    user_id int not null,
    role_id int not null,
    foreign key (user_id) references my_user (id),
    foreign key (role_id) references roles (id)
)