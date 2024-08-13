CREATE TABLE movies (
    id SERIAL PRIMARY KEY,
    rating INTEGER,
    movie VARCHAR(255),
    year INTEGER,
    country VARCHAR(255),
    rating_ball FLOAT,
    overview TEXT,
    director VARCHAR(255),
    screenwriter TEXT[],
    actors TEXT[],
    url_logo VARCHAR(255)
);