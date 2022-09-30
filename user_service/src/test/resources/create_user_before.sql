# delete from user_roles;
# delete from my_user;

insert into test_for_user_db.my_user (id, name, surname, lastname, email, registration_date, password, phone_number, status)
values (100, 'tyta', 'tyta', 'tyta', 'tyta@mail.ru', '2002-09-09 11:22:55', '$2y$12$vbJLURgbClXvhG2lQojGpeTOFGGBJ.Y1IzwwBb7V6VbIE9YIkWszm', '+79656140138', 'ACTIVE');

insert into test_for_user_db.roles(id, role)
values (1, 'ROLE_ADMIN');

insert into test_for_user_db.user_roles (id, user_id, role_id)
values (100, 100, 1);