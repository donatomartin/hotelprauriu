-- RESERVATIONS
DROP TABLE IF EXISTS RESERVATIONS;

CREATE TABLE RESERVATIONS (
    ID BIGSERIAL PRIMARY KEY,
    guest_full_name VARCHAR(255),
    guest_email VARCHAR(255),
    guest_phone_number VARCHAR(255),
    number_of_dogs SMALLINT,
    number_of_guests SMALLINT,
    check_in DATE,
    check_out DATE,
    guest_message VARCHAR(511)
);

DROP TABLE IF EXISTS QUESTIONS;

-- QUESTIONS
CREATE TABLE QUESTIONS (
    ID BIGSERIAL PRIMARY KEY,
    guest_full_name VARCHAR(255),
    guest_email VARCHAR(255),
    guest_message VARCHAR(511)
);

-- INSERT DATA
INSERT INTO RESERVATIONS (
        guest_full_name,
        guest_email,
        guest_phone_number,
        number_of_dogs,
        number_of_guests,
        check_in,
        check_out,
        guest_message
    )
VALUES (
        'John Doe',
        'john@example.com',
        '123-456-7890',
        1,
        2,
        '2024-01-01',
        '2024-01-05',
        NULL
    ),
    (
        'Jane Smith',
        'jane@example.com',
        '098-765-4321',
        2,
        3,
        '2024-02-15',
        '2024-02-20',
        NULL
    ),
    (
        'Bob Wilson',
        'bob@example.com',
        '555-555-5555',
        0,
        4,
        '2024-03-10',
        '2024-03-15',
        NULL
    ),
    (
        'Donato Martín',
        'donato@example.com',
        '555-555-5555',
        0,
        4,
        '2024-03-10',
        '2024-03-15',
        'La cama sin chinches por favor'
    );

INSERT INTO questions (
        guest_full_name,
        guest_email,
        guest_message
    )
VALUES (
    'Alice Johnson',
    'alice@example.com',
    'Do you have parking available?'
),
(
    'Carlos Rodriguez',
    'carlos@example.com',
    'What time is check-in?'
),
(
    'Maria Garcia',
    'maria@example.com',
    'Are pets allowed in all rooms?'
),
(
    'David Lee',
    'david@example.com',
    'Is breakfast included in the rate?'
),
(
    'Emma Thompson',
    'emma@example.com',
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
);