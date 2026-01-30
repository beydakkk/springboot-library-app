-- Copy&Paste this file content to your PostgreSQL initialization script
CREATE TABLE IF NOT EXISTS publisher (
    publisher_id BIGSERIAL PRIMARY KEY,
    publisher_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS book (
    book_id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    price NUMERIC(12,2),
    isbn13 VARCHAR(13) UNIQUE,
    published_year INT,
    publisher_id BIGINT NOT NULL REFERENCES publisher(publisher_id)
);

CREATE TABLE IF NOT EXISTS author (
    author_id BIGSERIAL PRIMARY KEY,
    author_name_surname VARCHAR(255) NOT NULL,
    book_id BIGINT REFERENCES book(book_id)
);

CREATE INDEX IF NOT EXISTS idx_book_title ON book(title);
CREATE INDEX IF NOT EXISTS idx_book_published_year ON book(published_year);
CREATE INDEX IF NOT EXISTS idx_author_book_id ON author(book_id);