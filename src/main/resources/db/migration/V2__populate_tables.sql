INSERT INTO category (id, name) VALUES
    (gen_random_uuid(), 'SUV'),
    (gen_random_uuid(), 'Sedan'),
    (gen_random_uuid(), 'Hatchback'),
    (gen_random_uuid(), 'Convertible'),
    (gen_random_uuid(), 'Coupe'),
    (gen_random_uuid(), 'Pickup Truck'),
    (gen_random_uuid(), 'Station Wagon'),
    (gen_random_uuid(), 'Minivan'),
    (gen_random_uuid(), 'Electric'),
    (gen_random_uuid(), 'Hybrid');

INSERT INTO brand (id, name) VALUES
    (gen_random_uuid(), 'Toyota'),
    (gen_random_uuid(), 'Honda'),
    (gen_random_uuid(), 'Ford'),
    (gen_random_uuid(), 'Chevrolet'),
    (gen_random_uuid(), 'Nissan'),
    (gen_random_uuid(), 'Hyundai'),
    (gen_random_uuid(), 'Kia'),
    (gen_random_uuid(), 'Volkswagen'),
    (gen_random_uuid(), 'BMW'),
    (gen_random_uuid(), 'Mercedes-Benz'),
    (gen_random_uuid(), 'Audi'),
    (gen_random_uuid(), 'Porsche'),
    (gen_random_uuid(), 'Tesla'),
    (gen_random_uuid(), 'Volvo'),
    (gen_random_uuid(), 'Mazda'),
    (gen_random_uuid(), 'Jeep'),
    (gen_random_uuid(), 'Subaru'),
    (gen_random_uuid(), 'GMC'),
    (gen_random_uuid(), 'Lexus'),
    (gen_random_uuid(), 'Acura'),
    (gen_random_uuid(), 'Infiniti'),
    (gen_random_uuid(), 'Land Rover'),
    (gen_random_uuid(), 'Jaguar'),
    (gen_random_uuid(), 'Mitsubishi'),
    (gen_random_uuid(), 'Peugeot'),
    (gen_random_uuid(), 'Fiat'),
    (gen_random_uuid(), 'Alfa Romeo'),
    (gen_random_uuid(), 'Chrysler'),
    (gen_random_uuid(), 'Dodge'),
    (gen_random_uuid(), 'Ram'),
    (gen_random_uuid(), 'Cadillac'),
    (gen_random_uuid(), 'Lincoln'),
    (gen_random_uuid(), 'Buick'),
    (gen_random_uuid(), 'Mini'),
    (gen_random_uuid(), 'Rolls-Royce'),
    (gen_random_uuid(), 'Bentley'),
    (gen_random_uuid(), 'Maserati'),
    (gen_random_uuid(), 'Ferrari'),
    (gen_random_uuid(), 'Lamborghini'),
    (gen_random_uuid(), 'McLaren'),
    (gen_random_uuid(), 'Bugatti'),
    (gen_random_uuid(), 'Aston Martin'),
    (gen_random_uuid(), 'Suzuki'),
    (gen_random_uuid(), 'Renault'),
    (gen_random_uuid(), 'Skoda'),
    (gen_random_uuid(), 'Seat'),
    (gen_random_uuid(), 'Saab');

DO $$
DECLARE
    car_brand_id UUID;
    car_category_id UUID;
    car_id UUID;
BEGIN
    FOR i IN 1..500 LOOP
        SELECT id INTO car_brand_id FROM brand ORDER BY RANDOM() LIMIT 1;

        INSERT INTO car (
            id, model, brand_id, year, price_in_usd, transmission_type,
            engine_type, drive_type, steering_location, mileage_in_km,
            color_rgb, owner_name, vin, created_at, updated_at
        ) VALUES (
            gen_random_uuid(),
            'Model_' || i,
            car_brand_id,
            1990 + (RANDOM() * 33)::INT,
            (RANDOM() * 50000 + 10000)::NUMERIC(15, 2),
            CASE WHEN RANDOM() > 0.5 THEN 'Automatic' ELSE 'Manual' END,
            CASE
                WHEN RANDOM() < 0.2 THEN 'Inline'
                WHEN RANDOM() < 0.35 THEN 'V type'
                WHEN RANDOM() < 0.45 THEN 'Flat'
                WHEN RANDOM() < 0.55 THEN 'Boxer'
                WHEN RANDOM() < 0.65 THEN 'W type'
                WHEN RANDOM() < 0.7 THEN 'Rotary'
                WHEN RANDOM() < 0.8 THEN 'Electric'
                WHEN RANDOM() < 0.9 THEN 'Hybrid'
                WHEN RANDOM() < 0.95 THEN 'Diesel'
                WHEN RANDOM() < 0.975 THEN 'Turbine'
                ELSE 'Hydrogen fuel cell'
            END,
            CASE
                WHEN RANDOM() < 0.3 THEN 'FWD'
                WHEN RANDOM() < 0.6 THEN 'RWD'
                WHEN RANDOM() < 0.8 THEN '4WD'
                ELSE 'AWD'
            END,
            CASE WHEN RANDOM() > 0.5 THEN 'Left' ELSE 'Right' END,
            (RANDOM() * 200000)::NUMERIC(10, 2),
            (RANDOM() * 16777215)::INT,
            'Owner_' || i,
            'VIN' || (1000000 + i)::TEXT,
            NOW(),
            NOW()
        ) RETURNING id INTO car_id;

        SELECT id INTO car_category_id FROM category ORDER BY RANDOM() LIMIT 1;

        INSERT INTO car_category (car_id, category_id) VALUES (car_id, car_category_id);
    END LOOP;
END $$;
