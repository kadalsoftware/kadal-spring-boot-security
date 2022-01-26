--Sequence Create
create sequence permission_seq;
create sequence user_seq;
create sequence role_seq;
create sequence role_permissions_seq;
create sequence user_roles_seq;

--Permission Table Create
CREATE TABLE IF NOT EXISTS public.permission
(
    id bigint NOT NULL default nextval('permission_seq'::regclass) ,
    permission character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT permission_pkey PRIMARY KEY (id)
    );

--User Table Create
CREATE TABLE IF NOT EXISTS public.custom_user
(
    id bigint NOT NULL default nextval('user_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT custom_user_pkey PRIMARY KEY (id)
);

--Role Table Create
CREATE TABLE IF NOT EXISTS public.role
(
    id bigint NOT NULL default nextval('role_seq'::regclass),
    role character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT role_pkey PRIMARY KEY (id)
);

--Role Permission Table Create
CREATE TABLE IF NOT EXISTS public.role_permissions
(
    id bigint NOT NULL default nextval('role_permissions_seq'::regclass),
    role_id bigint NOT NULL,
    permission_id bigint NOT NULL,
    CONSTRAINT role_permissions_pkey PRIMARY KEY (id)
);

--User Role Table Create
CREATE TABLE IF NOT EXISTS public.user_roles
(
    id bigint NOT NULL default nextval('user_roles_seq'::regclass),
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (id)
);
