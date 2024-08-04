drop table if exists car;
drop table if exists category;

create table category (
   id serial not null,
   name varchar (31),
   primary key (id)
);

create table car (
   id serial not null,
   object_id varchar (31),
   model varchar (63),
   brand varchar (31),
   year integer,
   primary key (id)
);

create table car_category (
   id serial not null,
   car_id integer not null,
   category_id integer not null,
   primary key (id),
   foreign key (category_id) references category on delete cascade,
   foreign key (car_id) references car on delete cascade
);
