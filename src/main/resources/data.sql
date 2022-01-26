--Permission Insert
INSERT INTO public.permission(id, permission)
VALUES (1, 'user:write');

INSERT INTO public.permission(id, permission)
VALUES (2, 'user:read');

INSERT INTO public.permission(id, permission)
VALUES (3, 'role:write');

INSERT INTO public.permission(id, permission)
VALUES (4, 'role:read');

--Sequence
SELECT setval('permission_seq', max(id)) FROM permission;


--User Insert
INSERT INTO public.custom_user(id, email, name, password)
VALUES (1, 'kadal@admin.com', 'kadal', '$2a$10$jo2Dzg9KXLtJZIoQawsdf.r3h8rJj8vOsi/OAwaMfPoqy2vP1O0HG');

--Sequence
SELECT setval('user_seq', max(id)) FROM custom_user;


--Role Insert
INSERT INTO public.role(
    id, role)
VALUES (1, 'ADMIN');

--Sequence
SELECT setval('role_seq', max(id)) FROM role;


--Role Permission Insert
INSERT INTO public.role_permissions(
    id, role_id, permission_id)
VALUES (1, 1, 1);

INSERT INTO public.role_permissions(
    id, role_id, permission_id)
VALUES (2, 1, 2);

INSERT INTO public.role_permissions(
    id, role_id, permission_id)
VALUES (3, 1, 3);

INSERT INTO public.role_permissions(
    id, role_id, permission_id)
VALUES (4, 1, 4);

--Sequence
SELECT setval('role_permissions_seq', max(id)) FROM role_permissions;


--User Role Insert
INSERT INTO public.user_roles(
    id, user_id, role_id)
VALUES (1, 1, 1);

--Sequence
SELECT setval('user_roles_seq', max(id)) FROM user_roles;


