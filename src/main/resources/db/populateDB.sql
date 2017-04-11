DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals(calories, description, user_id) VALUES ('1200', 'Завтрак', 100000);
INSERT INTO meals(calories, description, user_id) VALUES ('1500', 'Обед', 100000);
INSERT INTO meals(calories, description, user_id) VALUES ('2000', 'Ужин', 100000);
INSERT INTO meals(calories, description, user_id) VALUES ('500', 'Завтрак1', 100000);
INSERT INTO meals(calories, description, user_id) VALUES ('1000', 'Обед1', 100000);
INSERT INTO meals(calories, description, user_id) VALUES ('500', 'Ужин1', 100000);
