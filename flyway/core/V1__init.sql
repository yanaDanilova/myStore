
CREATE TABLE categories (
    id              bigserial primary key,
    title           varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp

);

insert into categories(title) values ('Clothes'),('Others');

CREATE TABLE products (
 id bigserial       PRIMARY KEY,
 title              VARCHAR(255),
 price              numeric(8,2),
 categories_id      bigint references categories(id),
 created_at         timestamp default current_timestamp,
 updated_at         timestamp default current_timestamp
 );

INSERT INTO products (title, price, categories_id) VALUES
('T-Shirt', 25.50,1),
('Jeans', 70.25,1),
('Sweater',150.99,1),
('Skirt',50.45,1),
('Coat',150.88,1),
('Hat', 70.55,1),
('Scarf',68.99,1),
('Suit', 350.55,1),
('Jeans', 78.66,1),
('Sweater',100.00,1);




create table orders (
    id                              bigserial primary key,
    username                        varchar(255) not null,
    total_price                     numeric(8,2) not null,
    address                         varchar(255),
    created_at                      timestamp default current_timestamp,
    updated_at                      timestamp default current_timestamp

);

create table order_items (
    id                              bigserial primary key,
    product_id                      bigint references products (id),
    quantity                        int,
    price_per_product               numeric (8, 2),
    price                           numeric (8, 2),
    order_id                        bigint references orders(id),
    created_at                      timestamp default current_timestamp,
    updated_at                      timestamp default current_timestamp
);



