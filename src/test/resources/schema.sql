drop all objects;

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


create table comments(
     id bigint auto_increment,
     comment varchar(255),
     book_id bigint references books(id) on delete cascade,
     primary key (id)
);