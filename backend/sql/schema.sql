CREATE TABLE "public"."usuario"
(
   login varchar(255) PRIMARY KEY NOT NULL,
   description varchar(255),
   firstname varchar(255),
   lastname varchar(255),
   password varchar(30)
);

insert into usuario (login, description, firstname, lastname, password)
  values ('john', 'likes pizza', 'John', 'Doe', 'abc');

insert into usuario (login, description, firstname, lastname, password)
  values ('bob', 'sleeps all day', 'Bob', 'Shutterland', 'pass');

insert into usuario (login, description, firstname, lastname, password)
  values ('alice', 'lives in the wonderland', 'Alice', 'Inchains', 'secret');

commit;
