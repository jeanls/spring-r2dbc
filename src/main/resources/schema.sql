DROP TABLE IF EXISTS products;
DROP SEQUENCE IF EXISTS products_seq;
CREATE SEQUENCE products_seq;

DROP TABLE IF EXISTS categories;
DROP SEQUENCE IF EXISTS categories_seq;
CREATE SEQUENCE categories_seq;

CREATE TABLE categories
(
    id         INTEGER UNIQUE NOT NULL DEFAULT NEXTVAL('categories_seq'),
    name       VARCHAR(50)    NOT NULL,
    created_at TIMESTAMP      NOT NULL,
    updated_at TIMESTAMP      NOT NULL
);

CREATE TABLE products
(
    id          INTEGER      NOT NULL DEFAULT NEXTVAL('products_seq'),
    name        VARCHAR(50)  NOT NULL,
    description VARCHAR(100) NULL,
    category_id INTEGER      NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    updated_at  TIMESTAMP    NOT NULL
);

ALTER TABLE products
    ADD CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories (id);

INSERT INTO categories (name, created_at, updated_at)
values ('Category 1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (name, description, category_id, created_at, updated_at)
values ('Product 1', 'Description product 1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (name, description, category_id, created_at, updated_at)
values ('Product 2', 'Description product 2', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (name, description, category_id, created_at, updated_at)
values ('Product 3', 'Description product 3', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (name, description, category_id, created_at, updated_at)
values ('Product 4', 'Description product 4', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (name, description, category_id, created_at, updated_at)
values ('Product 5', 'Description product 5', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (name, description, category_id, created_at, updated_at)
values ('Product 6', 'Description product 6', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (name, description, category_id, created_at, updated_at)
values ('Product 7', 'Description product 7', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (name, description, category_id, created_at, updated_at)
values ('Product 8', 'Description product 8', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (name, description, category_id, created_at, updated_at)
values ('Product 9', 'Description product 9', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (name, description, category_id, created_at, updated_at)
values ('Product 10', 'Description product 10', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (name, description, category_id, created_at, updated_at)
values ('Product 11', 'Description product 11', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (name, description, category_id, created_at, updated_at)
values ('Product 12', 'Description product 12', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);