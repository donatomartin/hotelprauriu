-- RESERVATIONS
DROP TABLE IF EXISTS RESERVATIONS;
CREATE TABLE RESERVATIONS (
    ID BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    guest_full_name VARCHAR(255),
    guest_email VARCHAR(255),
    guest_phone_number VARCHAR(255),
    number_of_dogs SMALLINT,
    number_of_guests SMALLINT,
    check_in DATE,
    check_out DATE,
    guest_message VARCHAR(511),
    reply_message VARCHAR(511),
    reservation_status VARCHAR(255) DEFAULT 'PENDING'
);

-- MESSAGES
DROP TABLE IF EXISTS MESSAGES;
CREATE TABLE MESSAGES (
    ID BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    guest_full_name VARCHAR(255),
    guest_email VARCHAR(255),
    guest_message VARCHAR(511),
    reply_message VARCHAR(511),
    message_status VARCHAR(255) DEFAULT 'UNREAD'
);

-- USERS
DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
    ID BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    username VARCHAR(255),
    email VARCHAR(255),
    PASSWORD VARCHAR(255),
    role VARCHAR(255)
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
    ),
    (
        'Sarah Brown',
        'sarah@example.com',
        '444-555-6666',
        1,
        2,
        '2024-04-20',
        '2024-04-25',
        'Would like a room with a view'
    ),
    (
        'Michael Chen',
        'michael@example.com',
        '777-888-9999',
        2,
        3,
        '2024-05-01',
        '2024-05-07',
        'Early check-in requested'
    ),
    (
        'Emily Davis',
        'emily@example.com',
        '111-222-3333',
        0,
        1,
        '2024-06-15',
        '2024-06-18',
        'Business trip'
    ),
    (
        'Luis Rodriguez',
        'luis@example.com',
        '666-777-8888',
        1,
        4,
        '2024-07-01',
        '2024-07-10',
        'Family vacation'
    ),
    (
        'Anna Kim',
        'anna@example.com',
        '222-333-4444',
        0,
        2,
        '2024-08-05',
        '2024-08-12',
        'Honeymoon stay'
    );
INSERT INTO messages (guest_full_name, guest_email, guest_message)
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
    ),
    (
        'Sophie Wilson',
        'sophie@example.com',
        'Do you have any spa services?'
    ),
    (
        'James Brown',
        'james@example.com',
        'Is there a restaurant on site?'
    ),
    (
        'Laura Martinez',
        'laura@example.com',
        'What is your cancellation policy?'
    ),
    (
        'Thomas Anderson',
        'thomas@example.com',
        'Do you offer airport shuttle service?'
    ),
    (
        'Julia Chang',
        'julia@example.com',
        'Are there any nearby attractions?'
    ),
    (
        'Robert Miller',
        'robert@example.com',
        'Do you have connecting rooms available?'
    ),
    (
        'Elena Popov',
        'elena@example.com',
        'What amenities are included?'
    ),
    (
        'Ahmed Hassan',
        'ahmed@example.com',
        'Is there WiFi in all rooms?'
    ),
    (
        'Isabella Silva',
        'isabella@example.com',
        'Do you have room service?'
    ),
    (
        'Pierre Dubois',
        'pierre@example.com',
        'Are there any special rates for extended stays?'
    ),
    (
        'Yuki Tanaka',
        'yuki@example.com',
        'Is there a gym facility?'
    ),
    (
        'Hans Schmidt',
        'hans@example.com',
        'Do you offer late check-out?'
    ),
    (
        'Sophia Lee',
        'sophia@example.com',
        'Are there meeting rooms available?'
    ),
    (
        'Mohammed Al-Sayed',
        'mohammed@example.com',
        'What are your COVID-19 policies?'
    ),
    (
        'Natalie Wong',
        'natalie@example.com',
        'Do you have cribs available for infants?'
    );
INSERT INTO USERS (username, email, PASSWORD, role)
VALUES ('admin', 'admin@admin.com', 'admin', 'ADMIN');