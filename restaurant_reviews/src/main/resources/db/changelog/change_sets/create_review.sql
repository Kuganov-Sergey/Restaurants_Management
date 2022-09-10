create table if not exists reviews
(
    id            int primary key auto_increment,
    restaurant_Entity_id int not null,
    reviewEntity        varchar(255),
    rating        int not null check (rating <= 5 and rating > 0),
    foreign key (restaurant_Entity_id) references restaurants (id) on delete cascade
);