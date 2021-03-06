drop table if exists car CASCADE;
drop table if exists configuration CASCADE;
drop table if exists customer CASCADE;
create table car
(
    id               bigint generated by default as identity,
    model_type       varchar(255),
    serial_number    bigint,
    configuration_id bigint,
    customer_id      bigint,
    primary key (id)
);
create table configuration
(
    id        bigint generated by default as identity,
    engine    bigint,
    equipment bigint,
    exterior  bigint,
    interior  bigint,
    trim      bigint,
    primary key (id)
);
create table customer
(
    id             bigint generated by default as identity,
    date_of_birth  date,
    name           varchar(255),
    nationality    varchar(255),
    place_of_birth varchar(255),
    primary key (id)
);
alter table car
    add constraint FKp5foxcmfjuq5gcu0egj0fxupp foreign key (configuration_id) references configuration;
alter table car
    add constraint FK8kbvg77sdvbvsgm7p3988o35q foreign key (customer_id) references customer;