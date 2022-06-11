## **6.（必做）**

基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。

### 6.1 用户表

```sql
drop table if exists `t_user`;
create table t_user(
    id bigint not null auto_increment comment '用户id',
    name varchar(50) not null comment '用户姓名',
    login_name varchar(20)  unique not null comment '登录名',
    phone_number varchar(15) not null comment '电话号码',
    create_time long not null comment '创建时间',
    update_time long not null comment '最后更新时间',
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户信息表';
```

### 6.2 商品表

```sql
drop table if exists `t_product`;
create table t_product(
    id bigint auto_increment comment '商品id',
    name varchar(100) not null comment '商品名称',
    price int not null comment '商品价格',
	create_time long not null comment '创建时间',
    update_time long not null comment '最后更新时间',
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '商品信息表';
```

### 6.3 订单表

```sql
drop table if exists `t_order`;
create table t_order(
    id bigint not null auto_increment comment '订单id',
    user_id int not null comment '用户id',
    price int not null comment '支付价格',
    status int not null comment '订单状态，10-未付款,20-已付款,30-已发货,40-交易完成,50-交易关闭 90-删除 99-取消',
    payment_time long comment '付款时间',
    send_time long comment '发货时间时间',
    end_time long comment '交易成功时间',
    close_time long comment '交易关闭时间',
    del_time long comment '删除时间',
    cancle_time long comment '取消时间',
	create_time long not null comment '创建时间',
    update_time long not null comment '最后更新时间',
    primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '订单信息表';

```

### 6.4 订单明细表

```sql
drop table if exists `t_order_detail`;
create table t_order_detail(
    id bigint not null auto_increment comment '订单明细id',
    order_id int not null comment '订单id',
    product_id int not null comment '商品id',
    product_name varchar(100) not null comment '商品名称',
    procuct_price int not null comment '商品单价',
    product_num int not null comment '商品数量',
    total_price int not null comment '商品总价',
	create_time long not null comment '创建时间',
    update_time long not null comment '最后更新时间',
    primary key (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '订单详情表';
```

