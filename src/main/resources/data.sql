insert into genres(name) values ('Romantic');
insert into genres(name)values ('Detective');
insert into genres(name) values ('Drama');
insert into authors(name, surname) values ('Agata', 'Christy');
insert into authors(name, surname) values ('Charlotta', 'Bronte');
insert into authors(name, surname) values ('Lev', 'Tolstoy');
insert into books(name, genre_id, author_id) values ('Jane Air', (select genres.id from genres where genres.name = 'Romantic'), (select authors.id from authors where authors.name = 'Charlotta'));