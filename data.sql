
DROP  TABLE IF EXISTS users;
drop table if exists bookings;
drop table if exists reviews;

create table users(
                      id serial primary key,
                      name varchar(100),
                      email varchar(100) unique ,
                      password varchar(100)
);
create table bookings (
                          id serial primary key,
                          user_id integer not null,
                          table_id integer not null,
                          booking_date date not null,
                          booking_time time not null,
                          status varchar(20) default 'pending',
                          foreign key (user_id) references users(id),
                          foreign key (table_id) references tables(id)
);



create table tables(
                       id serial primary key,
                       table_number integer unique not null,
                       seating_capacity integer,
                       location varchar(100)
);

create table reviews(
                        id serial primary key,
                        user_id integer not null,
                        booking_id integer not null,
                        rating integer not null check ( rating>=1 and rating<=5 ),
                        comment text,
                        created_at timestamp(0) default current_timestamp,
                        foreign key (user_id) references users(id),
                        foreign key (booking_id) references bookings(id)
);


create table menu(
                     id  serial primary key,
                     name varchar(50) not null,
                     description text,
                     price integer  not null
);




