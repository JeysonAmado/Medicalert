CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       user_who_created_id BIGINT,
                       created_at TIMESTAMP,
                       user_who_updated_id BIGINT,
                       updated_at TIMESTAMP,
                       user_who_deleted_id BIGINT,
                       deleted_at TIMESTAMP
);

CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255)NOT null CONSTRAINT name_unique UNIQUE,
                       user_who_created_id BIGINT,
                       created_at TIMESTAMP,
                       user_who_updated_id BIGINT,
                       updated_at TIMESTAMP,
                       user_who_deleted_id BIGINT,
                       deleted_at TIMESTAMP
);

CREATE TABLE user_roles (
                            id SERIAL PRIMARY KEY,
                            user_id BIGINT NOT null,
                            role_id BIGINT NOT null,
                            user_who_created_id BIGINT,
                            created_at TIMESTAMP,
                            user_who_updated_id BIGINT,
                            updated_at TIMESTAMP,
                            user_who_deleted_id BIGINT,
                            deleted_at TIMESTAMP,
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE medication_types (
                                  id SERIAL PRIMARY KEY,
                                  name VARCHAR(255) NOT null,
                                  user_who_created_id BIGINT,
                                  created_at TIMESTAMP,
                                  user_who_updated_id BIGINT,
                                  updated_at TIMESTAMP,
                                  user_who_deleted_id BIGINT,
                                  deleted_at TIMESTAMP
);

CREATE TABLE medications(
                            id SERIAL PRIMARY KEY,
                            medication_type_id BIGINT,
                            name VARCHAR(255) NOT null,
                            user_who_created_id BIGINT,
                            created_at TIMESTAMP,
                            user_who_updated_id BIGINT,
                            updated_at TIMESTAMP,
                            user_who_deleted_id BIGINT,
                            deleted_at TIMESTAMP,
                            FOREIGN KEY (medication_type_id) REFERENCES medication_types(id)
);

CREATE TABLE medication_register(
                                    id SERIAL PRIMARY KEY,
                                    medication_id BIGINT NOT null,
                                    presentation VARCHAR(30),
                                    quantity DOUBLE PRECISION,
                                    additional_notes TEXT,
                                    user_who_created_id BIGINT,
                                    created_at TIMESTAMP,
                                    user_who_updated_id BIGINT,
                                    updated_at TIMESTAMP,
                                    user_who_deleted_id BIGINT,
                                    deleted_at TIMESTAMP,
                                    FOREIGN KEY (medication_id) REFERENCES medications(id)
);


CREATE TABLE alerts (
                        id SERIAL PRIMARY KEY,
                        medication_register_id BIGINT NOT null,
                        name VARCHAR(255),
                        hours_to_repeat DOUBLE precision NOT null,
                        doses_taken INTEGER,
                        start_at TIMESTAMP,
                        next_alert_at TIMESTAMP,
                        user_who_created_id BIGINT,
                        created_at TIMESTAMP,
                        user_who_updated_id BIGINT,
                        updated_at TIMESTAMP,
                        user_who_deleted_id BIGINT,
                        deleted_at TIMESTAMP,
                        FOREIGN KEY (medication_register_id) REFERENCES medication_register(id)
);

INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'CUSTOMER');

DELETE FROM users;

INSERT INTO users (id, name, password, email)
VALUES (80, 'admin', '$2a$10$/j.Djt8jRpESq8He7XwaluRQD343lNKX7J8No/vZN2LSSPT4x1sAK', 'jeyson@admin.net');

INSERT INTO users (id, name, password, email)
VALUES (52, 'Goku', '$2a$10$bYkNvKtpmIiDy9izWGozOOFlIgV2kopmJ3CA9n7poLwwEGoeYdsZ6', 'goku@example.com');

DELETE FROM user_roles;

INSERT INTO user_roles (id,user_id, role_id)
VALUES (125,80, 1);

INSERT INTO user_roles (id, user_id, role_id)
VALUES (126,52, 2);

INSERT INTO medication_types (name, user_who_created_id, created_at)
VALUES ('Tipo de medicamento 1', 80, current_timestamp);
INSERT INTO medication_types (name, user_who_created_id, created_at)
VALUES ('Tipo de medicamento 2', 80, current_timestamp);

INSERT INTO medications (medication_type_id, name, user_who_created_id, created_at)
VALUES (1, 'Medicamento 1', 80, current_timestamp);
INSERT INTO medications (medication_type_id, name, user_who_created_id, created_at)
VALUES (2, 'Medicamento 2', 80, current_timestamp);

INSERT INTO medication_register (medication_id, presentation, quantity, additional_notes, user_who_created_id, created_at)
VALUES (1, 'Presentación 1', 10.5, 'Notas adicionales 1', 80, current_timestamp);
INSERT INTO medication_register (medication_id, presentation, quantity, additional_notes, user_who_created_id, created_at)
VALUES (2, 'Presentación 2', 5.75, 'Notas adicionales 2', 80, current_timestamp);
