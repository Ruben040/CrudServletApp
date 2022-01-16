create table developers
(
    id        integer default nextval('developer_id_seq'::regclass) not null
        constraint developer_pkey
            primary key,
    firstname varchar(30),
    lastname  varchar(30),
    age       integer
);

alter table developers
    owner to admin;

create table programminglanguage
(
    language_id   serial
        primary key,
    language_name varchar
);

alter table programminglanguage
    owner to admin;

create table dev_prog_lang
(
    developer_id integer not null
        references developers
            on update cascade on delete cascade,
    prog_lang_id integer not null
        references programminglanguage
            on update cascade,
    constraint dev_prog_lang_key
        primary key (developer_id, prog_lang_id)
);

alter table dev_prog_lang
    owner to admin;