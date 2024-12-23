CREATE TABLE IF NOT EXISTS category (
    id UUID PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS brand (
    id UUID PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS car (
    id UUID PRIMARY KEY,
    model VARCHAR(64),
    brand_id UUID,
    year INT,
    price_in_usd DECIMAL(15, 2),
    transmission_type VARCHAR(50),
    engine_type VARCHAR(50),
    drive_type VARCHAR(50),
    steering_location VARCHAR(50),
    mileage_in_km DECIMAL(10, 2),
    color_rgb INT,
    owner_name VARCHAR(128),
    vin VARCHAR(17) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (brand_id) REFERENCES brand(id)
);

CREATE TABLE IF NOT EXISTS car_category (
    car_id UUID NOT NULL,
    category_id UUID NOT NULL,
    PRIMARY KEY (car_id, category_id),
    FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);
