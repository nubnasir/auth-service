create table user_entity (
    id bigint auto_increment primary key,
    user_name varchar(200) unique not null,
    email varchar(200) unique not null,
    password varchar(2000) not null,
    user_role varchar(100) not null,
    status varchar(20) not null,
    created timestamp default current_timestamp,
    updated timestamp default current_timestamp on update current_timestamp
);
