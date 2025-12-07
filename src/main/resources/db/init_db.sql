create database product_manager_db;
create user product_manager_user with password '123456'
       grant usage on schema public to product_manager_user;
       grant create on schema public to product_manager_user;