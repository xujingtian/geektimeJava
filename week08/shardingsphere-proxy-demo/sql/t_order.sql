create table t_order(
    order_id bigint not null auto_increment,
	user_id int not null,
	status varchar(10),
	primary key(order_id)
	);