create table restaurant
(
    id        int auto_increment
        primary key,
    name      varchar(100) not null,
    longitude bigint       not null,
    latitude  bigint       not null,
    features  varchar(500) null,
    constraint restaurant_id_uindex
        unique (id)
);

create table user
(
    id          int auto_increment
        primary key,
    username    varchar(100) not null,
    email       varchar(100) not null,
    password    varchar(100) not null,
    user_vector varchar(255) null,
    user_rating varchar(255) null,
    friends     varchar(500) null,
    preferences varchar(500) null,
    constraint user_userId_uindex
        unique (id)
);

