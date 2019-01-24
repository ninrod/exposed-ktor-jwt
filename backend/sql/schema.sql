CREATE TABLE "public"."usuario"
(
   login varchar(255) PRIMARY KEY NOT NULL,
   description varchar(255),
   firstname varchar(255),
   lastname varchar(255)
);

insert into usuario (login, description, firstname, lastname) values ('1', 'first', 'first', 'silva');
insert into usuario (login, description, firstname, lastname) values ('2', 'second', 'second', 'silva');
insert into usuario (login, description, firstname, lastname) values ('3', 'third', 'third', 'silva');
commit;
