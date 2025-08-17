-- Добавление ролей
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

-- Добавление администратора
INSERT INTO users(username, password, email)
VALUES('admin', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'admin@library.com');

-- Связь пользователя с ролью администратора
INSERT INTO user_roles(user_id, role_id)
VALUES(
          (SELECT id FROM users WHERE username = 'admin'),
          (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
      );