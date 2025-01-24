-- RESERVATIONS
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
CREATE TABLE USERS (
    ID BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    username VARCHAR(255),
    email VARCHAR(255),
    PASSWORD VARCHAR(255),
    role VARCHAR(255)
);

-- LOGS
CREATE TABLE LOGS (
    ID BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date TIMESTAMP,
    message VARCHAR(511),
    action VARCHAR(255)

);