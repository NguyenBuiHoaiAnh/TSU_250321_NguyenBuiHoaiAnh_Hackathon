create database db_quanlynhansu;
use db_quanlynhansu;

create table Department
(
    department_id   int primary key auto_increment,
    department_name varchar(50) not null unique,
    description     text,
    status          bit default 1
);

create table Employee
(
    employee_id   int primary key auto_increment,
    full_name     varchar(100) not null,
    email         varchar(100) not null unique,
    phone_number  varchar(20)  not null unique,
    avatar_url    varchar(255),
    status        bit      default 1,
    create_at     datetime default current_timestamp,
    department_id int          not null,
    foreign key (department_id)
        references Department (department_id)
        on delete cascade
);

-- ------------------------------------------------------

-- Department

DELIMITER &&
create procedure display_department()
begin
    select * from Department;
end &&
DELIMITER &&

DELIMITER &&
create procedure add_department(
    des_name_in varchar(50),
    des_description_in text,
    status_in bit
)
begin
    insert into Department(department_name, description, status)
    values (des_name_in,
            des_description_in,
            status_in);
end &&
DELIMITER &&

DELIMITER &&
create procedure find_department_by_id(
    id_in int
)
begin
    select *
    from Department
    where department_id = id_in;
end &&
DELIMITER &&

DELIMITER &&
create procedure update_department(
    id_in int,
    name_in varchar(50),
    description_in text,
    status_in bit
)
begin
    update Department
    set department_name = name_in,
        description     = description_in,
        status          = status_in
    where department_id = id_in;
end &&
DELIMITER &&

DELIMITER &&
create procedure delete_department(
    id_in int
)
begin
    delete
    from Department
    where department_id = id_in;
end &&
DELIMITER &&

