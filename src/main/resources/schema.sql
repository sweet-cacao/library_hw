create table genres(
    id bigint auto_increment,
    name varchar(255),
    primary key (id)
);

create table authors(
    id bigint auto_increment,
    name varchar(255),
    surname varchar(255),
    primary key (id)
);

create table books (
    id bigint auto_increment,
    name varchar(255),
    genre_id bigint references genres(id),
    author_id bigint references authors(id),
    primary key (id)
);