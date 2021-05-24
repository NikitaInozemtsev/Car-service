INSERT INTO roles(id, name)
	values  (1, 'ROLE_USER'),
            (2, 'ROLE_ADMIN')
ON CONFLICT (id)
DO UPDATE SET name = excluded.name;