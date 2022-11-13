create table if not exists resume
(
    uuid      char(36) not null primary key,
    full_name text not null
);

alter table resume owner to postgres;

create table if not exists contact
(
    id          serial primary key,
    resume_uuid char(36) not null references resume(uuid) on update cascade on delete cascade,
    type        text     not null,
    value       text     not null
);
create unique index if not exists contact_uuid_name on contact (resume_uuid, type);


alter table contact owner to postgres;