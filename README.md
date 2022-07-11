

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT,
    email VARCHAR(125) NOT NULL UNIQUE,
    username VARCHAR(125) NOT NULL,
    password VARCHAR(125) NOT NULL,
    token VARCHAR(500) NOT NULL,
    register_date VARCHAR(125) NOT NULL,
    sex VARCHAR(125) NOT NULL,
    birthday VARCHAR(125) NOT NULL,
    about_me VARCHAR(225) NOT NULL,
    avatar VARCHAR(225) NOT NULL,
    active BIT(7) NOT NULL,
    wallpaper VARCHAR(125) NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(125) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);

CREATE TABLE user_has_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    FOREIGN KEY (role_id)
        REFERENCES roles(id)
        ON DELETE CASCADE

);

INSERT INTO roles (name) VALUES ('ROLE_ROOT');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');
