ALTER TABLE car
ADD price_in_usd DECIMAL(15, 2),
ADD transmission_type VARCHAR(64),
ADD engine_type VARCHAR(64),
ADD drive_type VARCHAR(64),
ADD mileage_in_km BIGINT,
ADD color_rgb INTEGER,
ADD steering_location VARCHAR(32),
ADD owner_name VARCHAR(128),
ADD vin VARCHAR(17);
