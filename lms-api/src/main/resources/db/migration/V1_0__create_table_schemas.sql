create sequence course_seq start with 1 increment by 50;
create sequence enrolled_course_seq start with 1 increment by 50;
create sequence task_category_seq start with 1 increment by 50;
create sequence task_log_seq start with 1 increment by 50;
create sequence users_seq start with 1 increment by 50;


create table course
(
    id         bigint       not null,
    end_date   date         not null,
    name       varchar(255) not null,
    start_date date         not null,
    primary key (id)
);


create table enrolled_course
(
    id         bigint       not null,
    status     varchar(255) not null,
    course_id  bigint       not null,
    student_id bigint       not null,
    primary key (id)
);


create table student
(
    address       varchar(255) not null,
    date_of_birth date         not null,
    email         varchar(255) not null,
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    phone_number  varchar(255) not null,
    user_id       bigint       not null,
    primary key (user_id)
);


create table task_category
(
    id   bigint       not null,
    name varchar(255) not null,
    primary key (id)
);


create table task_log
(
    id                 bigint       not null,
    date               date         not null,
    description        varchar(255) not null,
    time_spent         float(53)    not null,
    category_id        bigint       not null,
    enrolled_course_id bigint       not null,
    primary key (id)
);


create table users
(
    id       bigint not null,
    password varchar(255),
    role     varchar(255),
    username varchar(255),
    primary key (id)
);


alter table if exists course
    add constraint UK_4xqvdpkafb91tt3hsb67ga3fj unique (name);


alter table if exists student
    add constraint UK_fe0i52si7ybu0wjedj6motiim unique (email);


alter table if exists task_category
    add constraint UK_7k53oycuympinf3ewmh1rpfaf unique (name);


alter table if exists enrolled_course
    add constraint FK7ymxj31qf504o3u6sxmrx8mw1
        foreign key (course_id)
            references course;


alter table if exists enrolled_course
    add constraint FKnehajistor2cwf29gwl1efc4p
        foreign key (student_id)
            references student;


alter table if exists student
    add constraint FKk0thg920a3xk3v59yjbsatw1l
        foreign key (user_id)
            references users;


alter table if exists task_log
    add constraint FKhs0g0eaxs4m3hhgqwaemgom60
        foreign key (category_id)
            references task_category;


alter table if exists task_log
    add constraint FK66hprtoll2ix0k6v3l2u9c08d
        foreign key (enrolled_course_id)
            references enrolled_course;

insert into task_category
values (1, 'RESEARCHING'),
       (2, 'PRACTICING'),
       (3, 'WATCHING_VIDEOS');

insert into users(id,password, role, username) values (nextval('users_seq'),'$2a$10$SGlY2JhCQgYanQBrvUKAa.F6KIqCvyHdt2wsfM4oZqxRgGz/Eh3qO','ADMIN','admin@admin.com')