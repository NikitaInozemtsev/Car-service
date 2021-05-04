
INSERT INTO db.roles(id, name)
values (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN')
ON DUPLICATE KEY UPDATE name = values(name);

