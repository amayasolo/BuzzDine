create table restaurant
(
    id        int auto_increment
        primary key,
    name      varchar(100) not null,
    longitude decimal(9,7)       not null,
    latitude  decimal(9,7)       not null,
    features  varchar(500) null,
    constraint restaurant_id_uindex
        unique (id)
);

insert into restaurant(name, longitude, latitude, features) values
("Cypress Street Pint & Plate",33.7773813,-84.3859444,"American"),
("Tindrum",33.7765639,-84.3900002,"Asian"),
("Atwoods",33.7774993,-84.387063,"Italian"),
("Moe's",33.7771568,-84.389949,"Mexican"),
("Gyro bros",33.7770831,-84.3901324,"Mediterranean"),
("Waffle house",33.7766569,-84.3894624,"American"),
("Umma's",33.7772587,-84.3892249,"Asian"),
("Starbucks",33.7766877,-84.3884513,"American"),
("Rising Roll",33.7750707,-84.4005375,"American"),
("Herban Fix",33.770087,-84.3849437,"Asian"),
("Poor Calvin's",33.7685491,-84.3821679,"Asian"),
("Ecco Midtown",33.7787552,-84.385827,"European"),
("Twisted Taco",33.774668,-84.4019619,"Mexican"),
("The Varsity",33.7715946,-84.3893032,"American"),
("Rowdy Tiger Whiskey Bar & Kitchen",33.7781729,-84.3875239,"American"),
("Dos Amigos",33.7817223,-84.407731,"Mexican"),
("Sublime Doughnut",33.7818784,-84.4049196,"American"),
("Lucky Buddha",33.7818802,-84.4048051,"Asian"),
("Anticos",33.8891199,-84.4688446,"Italian"),
("Cafe intermezzo",33.7834373,-84.383536,"European"),
("Nova Sushi",33.7794141,-84.3874886,"Asian"),
("Kaldi's Coffee",33.7742431,-84.3962455,"European"),
("Pho King",33.7802177,-84.3875353,"Asian"),
("La Fonda",33.7828809,-84.4114002,"Mexican"),
("Chick-fil-a",33.7848169,-84.4079716,"American"),
("McDonalds",33.7849256,-84.4070304,"American"),
("Cook Out",33.7853534,-84.4078129,"American"),
("Krystal",33.7859355,-84.4078075,"American")

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

