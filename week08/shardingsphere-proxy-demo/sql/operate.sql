-- insert
insert into t_order(user_id, status) values (13, 'status'),(13,'false'),(13,'true'),(13,'true'),(13,'true'),(13,'true'),(13,'true'),(13,'true'),(13,'true'),(13,'true'),(13,'true');
-- select
select * from t_order where user_id = 13;
-- delete
delete from t_order where user_id = 13;