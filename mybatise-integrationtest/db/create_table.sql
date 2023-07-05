create table vip_user
(
    user_id   serial primary key,
    user_name varchar(50) unique not null,
    password  varchar(50)        not null,
    vip_num   varchar(50)        not null
)