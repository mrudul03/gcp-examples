create table customerdb.address
(
    id      varchar(36) not null,
    city    varchar(255),
    state   varchar(255),
    street  varchar(255),
    zipcode varchar(255),
    primary key (id)
);

create table customerdb.customer
(
    id      varchar(36) not null,
    firstname    varchar(255),
    lastname   varchar(255),
    mobilenumber   varchar(15),
    address_id varchar(36),
    primary key (id),
    constraint fk_customer_address_id foreign key (address_id) references address (id)
);