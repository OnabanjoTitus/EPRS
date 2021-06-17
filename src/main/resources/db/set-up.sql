
CREATE user payroll_user with encrypted password 'Payroll@123' createdb;

create database payroll_db with owner = payroll_user;

grant all privileges on database payroll_db to payroll_user;