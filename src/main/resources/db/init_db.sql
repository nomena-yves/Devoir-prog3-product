create database product_manager_db;
create user product_manager_user with password '123456'
       grant usage on schema public to product_manager_user;
       grant create on schema public to product_manager_user;
                    grant select,insert,update,delete on all tables
                          in schema public to product_manager_db;
     grant insert privilages in schema public select,insert,update,delete
    on schema public to product_manager_db;