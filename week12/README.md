

## **1.（必做）**

配置 redis 的主从复制，sentinel 高可用，Cluster 集群。

### 1.1 主从复制

#### 1.1.1 配置文件

```shell
port 6379
pidfile /var/run/redis_6379.pid
dir ./db/6379/
#masterauth 1234567890 
requirepass 1234567890
```

```
port 6380
pidfile /var/run/redis_6380.pid
dir ./db/6380/
replicaof 127.0.0.1 6379
masterauth 1234567890 
requirepass 1234567890
```

```
port 6381
pidfile /var/run/redis_6379.pid
dir ./db/6381/
replicaof 127.0.0.1 6380
masterauth 1234567890 
requirepass 1234567890
```

```
slaveof 127.0.0.1 6379 //也可以在某个master实例上，直接运行命令行
```



#### 1.1.2 启动服务

```shell
.\redis-server redis-windows-6379.conf
.\redis-server redis-windows-6380.conf
.\redis-server redis-windows-6381.conf
```



```shell
[023260] 23 Jul 16:08:03.088 # Server initialized
[023260] 23 Jul 16:08:03.089 * Ready to accept connections
[023260] 23 Jul 16:09:14.084 * Replica 127.0.0.1:6380 asks for synchronization
[023260] 23 Jul 16:09:14.084 * Full resync requested by replica 127.0.0.1:6380
[023260] 23 Jul 16:09:14.084 * Replication backlog created, my new replication IDs are '04422e72d0ccb13567e5d7ffb2abac08075e49f4' and '0000000000000000000000000000000000000000'
[023260] 23 Jul 16:09:14.084 * Delay next BGSAVE for diskless SYNC
[023260] 23 Jul 16:09:20.005 * Starting BGSAVE for SYNC with target: replicas sockets
[023260] 23 Jul 16:09:20.053 * Background RDB transfer started by pid 23716
[023260] 23 Jul 16:09:20.190 # fork operation complete
[023260] 23 Jul 16:09:20.206 * Background RDB transfer terminated with success
[023260] 23 Jul 16:09:20.206 * Streamed RDB transfer with replica 127.0.0.1:6380 succeeded (socket). Waiting for REPLCONF ACK from slave to enable streaming
[023260] 23 Jul 16:09:20.206 * Synchronization with replica 127.0.0.1:6380 succeeded
[023260] 23 Jul 17:08:04.019 * 1 changes in 3600 seconds. Saving...
[023260] 23 Jul 17:08:04.508 * Background saving started by pid 24524
[023260] 23 Jul 17:08:04.723 # fork operation complete
[023260] 23 Jul 17:08:04.739 * Background saving terminated with success
[023260] 23 Jul 18:58:15.128 # Disconnecting timedout replica (streaming sync): 127.0.0.1:6380
[023260] 23 Jul 18:58:15.206 # Connection with replica 127.0.0.1:6380 lost.
[023260] 23 Jul 18:58:15.341 * Replica 127.0.0.1:6380 asks for synchronization
[023260] 23 Jul 18:58:15.347 * Partial resynchronization request from 127.0.0.1:6380 accepted. Sending 0 bytes of backlog starting from offset 7461.
[023260] 23 Jul 18:59:16.196 # Connection with replica 127.0.0.1:6380 lost.
[023260] 23 Jul 18:59:16.196 * Replica 127.0.0.1:6380 asks for synchronization
[023260] 23 Jul 18:59:16.196 * Partial resynchronization request from 127.0.0.1:6380 accepted. Sending 84 bytes of backlog starting from offset 7461.
```



```shell
[023260] 23 Jul 16:08:03.088 # Server initialized
[023260] 23 Jul 16:08:03.089 * Ready to accept connections
[023260] 23 Jul 16:09:14.084 * Replica 127.0.0.1:6380 asks for synchronization
[023260] 23 Jul 16:09:14.084 * Full resync requested by replica 127.0.0.1:6380
[023260] 23 Jul 16:09:14.084 * Replication backlog created, my new replication IDs are '04422e72d0ccb13567e5d7ffb2abac08075e49f4' and '0000000000000000000000000000000000000000'
[023260] 23 Jul 16:09:14.084 * Delay next BGSAVE for diskless SYNC
[023260] 23 Jul 16:09:20.005 * Starting BGSAVE for SYNC with target: replicas sockets
[023260] 23 Jul 16:09:20.053 * Background RDB transfer started by pid 23716
[023260] 23 Jul 16:09:20.190 # fork operation complete
[023260] 23 Jul 16:09:20.206 * Background RDB transfer terminated with success
[023260] 23 Jul 16:09:20.206 * Streamed RDB transfer with replica 127.0.0.1:6380 succeeded (socket). Waiting for REPLCONF ACK from slave to enable streaming
[023260] 23 Jul 16:09:20.206 * Synchronization with replica 127.0.0.1:6380 succeeded
[023260] 23 Jul 17:08:04.019 * 1 changes in 3600 seconds. Saving...
[023260] 23 Jul 17:08:04.508 * Background saving started by pid 24524
[023260] 23 Jul 17:08:04.723 # fork operation complete
[023260] 23 Jul 17:08:04.739 * Background saving terminated with success
[023260] 23 Jul 18:58:15.128 # Disconnecting timedout replica (streaming sync): 127.0.0.1:6380
[023260] 23 Jul 18:58:15.206 # Connection with replica 127.0.0.1:6380 lost.
[023260] 23 Jul 18:58:15.341 * Replica 127.0.0.1:6380 asks for synchronization
[023260] 23 Jul 18:58:15.347 * Partial resynchronization request from 127.0.0.1:6380 accepted. Sending 0 bytes of backlog starting from offset 7461.
[023260] 23 Jul 18:59:16.196 # Connection with replica 127.0.0.1:6380 lost.
[023260] 23 Jul 18:59:16.196 * Replica 127.0.0.1:6380 asks for synchronization
[023260] 23 Jul 18:59:16.196 * Partial resynchronization request from 127.0.0.1:6380 accepted. Sending 84 bytes of backlog starting from offset 7461.
```

```shell
[023260] 23 Jul 16:08:03.088 # Server initialized
[023260] 23 Jul 16:08:03.089 * Ready to accept connections
[023260] 23 Jul 16:09:14.084 * Replica 127.0.0.1:6380 asks for synchronization
[023260] 23 Jul 16:09:14.084 * Full resync requested by replica 127.0.0.1:6380
[023260] 23 Jul 16:09:14.084 * Replication backlog created, my new replication IDs are '04422e72d0ccb13567e5d7ffb2abac08075e49f4' and '0000000000000000000000000000000000000000'
[023260] 23 Jul 16:09:14.084 * Delay next BGSAVE for diskless SYNC
[023260] 23 Jul 16:09:20.005 * Starting BGSAVE for SYNC with target: replicas sockets
[023260] 23 Jul 16:09:20.053 * Background RDB transfer started by pid 23716
[023260] 23 Jul 16:09:20.190 # fork operation complete
[023260] 23 Jul 16:09:20.206 * Background RDB transfer terminated with success
[023260] 23 Jul 16:09:20.206 * Streamed RDB transfer with replica 127.0.0.1:6380 succeeded (socket). Waiting for REPLCONF ACK from slave to enable streaming
[023260] 23 Jul 16:09:20.206 * Synchronization with replica 127.0.0.1:6380 succeeded
[023260] 23 Jul 17:08:04.019 * 1 changes in 3600 seconds. Saving...
[023260] 23 Jul 17:08:04.508 * Background saving started by pid 24524
[023260] 23 Jul 17:08:04.723 # fork operation complete
[023260] 23 Jul 17:08:04.739 * Background saving terminated with success
[023260] 23 Jul 18:58:15.128 # Disconnecting timedout replica (streaming sync): 127.0.0.1:6380
[023260] 23 Jul 18:58:15.206 # Connection with replica 127.0.0.1:6380 lost.
[023260] 23 Jul 18:58:15.341 * Replica 127.0.0.1:6380 asks for synchronization
[023260] 23 Jul 18:58:15.347 * Partial resynchronization request from 127.0.0.1:6380 accepted. Sending 0 bytes of backlog starting from offset 7461.
[023260] 23 Jul 18:59:16.196 # Connection with replica 127.0.0.1:6380 lost.
[023260] 23 Jul 18:59:16.196 * Replica 127.0.0.1:6380 asks for synchronization
[023260] 23 Jul 18:59:16.196 * Partial resynchronization request from 127.0.0.1:6380 accepted. Sending 84 bytes of backlog starting from offset 7461.
```



```shell
[023260] 23 Jul 16:08:03.088 # Server initialized
[023260] 23 Jul 16:08:03.089 * Ready to accept connections
[023260] 23 Jul 16:09:14.084 * Replica 127.0.0.1:6380 asks for synchronization
[023260] 23 Jul 16:09:14.084 * Full resync requested by replica 127.0.0.1:6380
[023260] 23 Jul 16:09:14.084 * Replication backlog created, my new replication IDs are '04422e72d0ccb13567e5d7ffb2abac08075e49f4' and '0000000000000000000000000000000000000000'
[023260] 23 Jul 16:09:14.084 * Delay next BGSAVE for diskless SYNC
[023260] 23 Jul 16:09:20.005 * Starting BGSAVE for SYNC with target: replicas sockets
[023260] 23 Jul 16:09:20.053 * Background RDB transfer started by pid 23716
[023260] 23 Jul 16:09:20.190 # fork operation complete
[023260] 23 Jul 16:09:20.206 * Background RDB transfer terminated with success
[023260] 23 Jul 16:09:20.206 * Streamed RDB transfer with replica 127.0.0.1:6380 succeeded (socket). Waiting for REPLCONF ACK from slave to enable streaming
[023260] 23 Jul 16:09:20.206 * Synchronization with replica 127.0.0.1:6380 succeeded
[023260] 23 Jul 17:08:04.019 * 1 changes in 3600 seconds. Saving...
[023260] 23 Jul 17:08:04.508 * Background saving started by pid 24524
[023260] 23 Jul 17:08:04.723 # fork operation complete
[023260] 23 Jul 17:08:04.739 * Background saving terminated with success
[023260] 23 Jul 18:58:15.128 # Disconnecting timedout replica (streaming sync): 127.0.0.1:6380
[023260] 23 Jul 18:58:15.206 # Connection with replica 127.0.0.1:6380 lost.
[023260] 23 Jul 18:58:15.341 * Replica 127.0.0.1:6380 asks for synchronization
[023260] 23 Jul 18:58:15.347 * Partial resynchronization request from 127.0.0.1:6380 accepted. Sending 0 bytes of backlog starting from offset 7461.
[023260] 23 Jul 18:59:16.196 # Connection with replica 127.0.0.1:6380 lost.
[023260] 23 Jul 18:59:16.196 * Replica 127.0.0.1:6380 asks for synchronization
[023260] 23 Jul 18:59:16.196 * Partial resynchronization request from 127.0.0.1:6380 accepted. Sending 84 bytes of backlog starting from offset 7461.
```

#### 1.1.3 测试命令

```shell
127.0.0.1:6379> set port 6379
OK
127.0.0.1:6379> keys *
1) "port"
127.0.0.1:6379> get port
"6379"
127.0.0.1:6379> set ip 127.0.0.3679
OK
127.0.0.1:6379> get ip
"127.0.0.3679"
127.0.0.1:6379> get port ip
```

```
127.0.0.1:6380> keys *
1) "port"
127.0.0.1:6380> get port
"6379"
127.0.0.1:6380> get ip
"127.0.0.3679"
```

```
127.0.0.1:6381> keys *
1) "port"
127.0.0.1:6381> get port
"6379"
127.0.0.1:6381> set ip 11111
(error) READONLY You can't write against a read only replica.
127.0.0.1:6381> get ip
"127.0.0.3679"
```



### 1.2  sentinel 高可用

#### 1.2.1 配置文件

```shell
sentinel myid 8d992c54df8f8677b0b345825f61fb733c73d14d
sentinel deny-scripts-reconfig yes
sentinel monitor redismaster 127.0.0.1 6379 2
sentinel auth-pass redismaster 1234567890
sentinel down-after-milliseconds redismaster 10000
# Generated by CONFIG REWRITE
protected-mode no
port 26379
#user default on nopass sanitize-payload ~* &* +@all
dir "./sentinel/db/"  #注意此处运行后会被修改
```



```shell
sentinel myid 8d992c54df8f8677b0b345825f61fb733c73d14d
sentinel deny-scripts-reconfig yes
sentinel monitor redismaster 127.0.0.1 6379 2
sentinel auth-pass redismaster 1234567890
sentinel down-after-milliseconds redismaster 10000
# Generated by CONFIG REWRITE
protected-mode no
port 26380
#user default on nopass sanitize-payload ~* &* +@all
dir "./sentinel/db/"  #注意此处运行后会被修改
```



```shell
sentinel myid 8d992c54df8f8677b0b345825f61fb733c73d14d
sentinel deny-scripts-reconfig yes
sentinel monitor redismaster 127.0.0.1 6379 2
sentinel auth-pass redismaster 1234567890
sentinel down-after-milliseconds redismaster 10000
# Generated by CONFIG REWRITE
protected-mode no
port 26381
#user default on nopass sanitize-payload ~* &* +@all
dir "./sentinel/db/"  #注意此处运行后会被修改
```



#### 1.2.2 启动服务

在原来的1主2从基础上，启动哨兵模式

```shell
.\redis-server sentinel\redis-sentinel-0.conf --sentinel
.\redis-server sentinel\redis-sentinel-1.conf --sentinel
.\redis-server sentinel\redis-sentinel-2.conf --sentinel
```



```shell
[028736] 23 Jul 23:46:25.655 # Sentinel ID is 8d992c54df8f8677b0b345825f61fb733c73d14c
[028736] 23 Jul 23:46:25.655 # +monitor master redismaster 127.0.0.1 6379 quorum 2
[028736] 23 Jul 23:46:25.702 * +slave slave 127.0.0.1:6380 127.0.0.1 6380 @ redismaster 127.0.0.1 6379
[028736] 23 Jul 23:46:25.702 * Sentinel new configuration saved on disk
[028736] 23 Jul 23:49:14.613 * +sentinel sentinel 8d992c54df8f8677b0b345825f61fb733c73d14d 127.0.0.1 26380 @ redismaster 127.0.0.1 6379
[028736] 23 Jul 23:49:14.613 * Sentinel new configuration saved on disk
[028736] 23 Jul 23:51:33.589 * +sentinel sentinel 8d992c54df8f8677b0b345825f61fb733c73d14e 127.0.0.1 26381 @ redismaster 127.0.0.1 6379
[028736] 23 Jul 23:51:33.620 * Sentinel new configuration saved on disk
```

```shell
[024488] 23 Jul 23:49:12.610 # Sentinel ID is 8d992c54df8f8677b0b345825f61fb733c73d14d
[024488] 23 Jul 23:49:12.610 # +monitor master redismaster 127.0.0.1 6379 quorum 2
[024488] 23 Jul 23:49:12.611 * +slave slave 127.0.0.1:6380 127.0.0.1 6380 @ redismaster 127.0.0.1 6379
[024488] 23 Jul 23:49:12.955 * Sentinel new configuration saved on disk
[024488] 23 Jul 23:49:12.955 * +sentinel sentinel 8d992c54df8f8677b0b345825f61fb733c73d14c 127.0.0.1 26379 @ redismaster 127.0.0.1 6379
[024488] 23 Jul 23:49:13.018 * Sentinel new configuration saved on disk
[024488] 23 Jul 23:51:33.589 * +sentinel sentinel 8d992c54df8f8677b0b345825f61fb733c73d14e 127.0.0.1 26381 @ redismaster 127.0.0.1 6379
[024488] 23 Jul 23:51:33.620 * Sentinel new configuration saved on disk
```

```shell
[019116] 23 Jul 23:51:31.525 # Sentinel ID is 8d992c54df8f8677b0b345825f61fb733c73d14e
[019116] 23 Jul 23:51:31.525 # +monitor master redismaster 127.0.0.1 6379 quorum 2
[019116] 23 Jul 23:51:31.548 * +slave slave 127.0.0.1:6380 127.0.0.1 6380 @ redismaster 127.0.0.1 6379
[019116] 23 Jul 23:51:31.885 * Sentinel new configuration saved on disk
[019116] 23 Jul 23:51:31.885 * +sentinel sentinel 8d992c54df8f8677b0b345825f61fb733c73d14d 127.0.0.1 26380 @ redismaster 127.0.0.1 6379
[019116] 23 Jul 23:51:31.900 * Sentinel new configuration saved on disk
[019116] 23 Jul 23:51:33.230 * +sentinel sentinel 8d992c54df8f8677b0b345825f61fb733c73d14c 127.0.0.1 26379 @ redismaster 127.0.0.1 6379
[019116] 23 Jul 23:51:33.230 * Sentinel new configuration saved on disk
```

#### 1.2.3 测试命令

停用6379，使用config直接配置 1主2从没有办法启动，还是因为2从不全是监听的6379

```shell
[020308] 24 Jul 00:12:20.234 # Sending command to master in replication handshake: -Writing to master: No error
[020308] 24 Jul 00:12:20.375 * Connecting to MASTER 127.0.0.1:6379
[020308] 24 Jul 00:12:20.375 * MASTER <-> REPLICA sync started
[020308] 24 Jul 00:12:22.408 * Non blocking connect for SYNC fired the event.
[020308] 24 Jul 00:12:22.408 # Sending command to master in replication handshake: -Writing to master: No error
[020308] 24 Jul 00:12:22.564 * Connecting to MASTER 127.0.0.1:6379
```

改为全监听6379后成功

```shell
[027160] 24 Jul 00:17:20.563 * Non blocking connect for SYNC fired the event.
[027160] 24 Jul 00:17:20.563 # Sending command to master in replication handshake: -Writing to master: No error
[027160] 24 Jul 00:17:20.735 * Connecting to MASTER 127.0.0.1:6379
[027160] 24 Jul 00:17:20.735 * MASTER <-> REPLICA sync started
[027160] 24 Jul 00:17:22.753 * Non blocking connect for SYNC fired the event.
[027160] 24 Jul 00:17:22.753 # Sending command to master in replication handshake: -Writing to master: No error
[027160] 24 Jul 00:17:22.925 * Connecting to MASTER 127.0.0.1:6379
[027160] 24 Jul 00:17:22.925 * MASTER <-> REPLICA sync started
[027160] 24 Jul 00:17:23.768 * Discarding previously cached master state.
[027160] 24 Jul 00:17:23.768 # Setting secondary replication ID to 311c2836e9277ee28edaab27d790ac9c16440fce, valid up to offset: 32606. New replication ID is f248272b4741de72fb548a0a8d1cfecd05dcfa65
[027160] 24 Jul 00:17:23.768 * MASTER MODE enabled (user request from 'id=15 addr=127.0.0.1:64983 laddr=127.0.0.1:6380 fd=13 name=sentinel-8d992c54-cmd age=26 idle=0 flags=x db=0 sub=0 psub=0 ssub=0 multi=4 qbuf=188 qbuf-free=20286 argv-mem=4 multi-mem=169 rbs=1024 rbp=45 obl=45 oll=0 omem=0 tot-mem=22693 events=r cmd=exec user=default redir=-1 resp=2')
[027160] 24 Jul 00:17:23.783 # CONFIG REWRITE executed with success.
[027160] 24 Jul 00:17:24.221 * Replica 127.0.0.1:6381 asks for synchronization
[027160] 24 Jul 00:17:24.221 * Partial resynchronization request from 127.0.0.1:6381 accepted. Sending 159 bytes of backlog starting from offset 32606.
```

查看6380端口，role:master

```shell
# Replication
role:master
connected_slaves:1
slave0:ip=127.0.0.1,port=6381,state=online,offset=60540,lag=1
master_failover_state:no-failover
master_replid:f248272b4741de72fb548a0a8d1cfecd05dcfa65
master_replid2:311c2836e9277ee28edaab27d790ac9c16440fce
master_repl_offset:60676
second_repl_offset:32606
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:18032
repl_backlog_histlen:42645

# CPU
used_cpu_sys:0.109375
used_cpu_user:0.109375
used_cpu_sys_children:0.000000
used_cpu_user_children:0.000000

# Modules

# Errorstats
errorstat_NOAUTH:count=3

# Cluster
cluster_enabled:0
```

在6380上新增KEY

```shell
# Keyspace
db0:keys=2,expires=0,avg_ttl=0
127.0.0.1:6380> set aa 11
OK
127.0.0.1:6380>
```

再启动6379，首次报错

```shell
[025912] 24 Jul 00:29:34.457 * (Non critical) Master does not understand REPLCONF listening-port: -NOAUTH Authentication required.
```

修改config

```shell
masterauth 1234567890 
```

再次启动

```shell

[023860] 24 Jul 00:32:44.050 # Server initialized
[023860] 24 Jul 00:32:44.050 * Loading RDB produced by version 7.0.4
[023860] 24 Jul 00:32:44.050 * RDB age 190 seconds
[023860] 24 Jul 00:32:44.050 * RDB memory usage when created 0.89 Mb
[023860] 24 Jul 00:32:44.050 * Done loading RDB, keys loaded: 2, keys expired: 0.
[023860] 24 Jul 00:32:44.050 * DB loaded from disk: 0.000 seconds
[023860] 24 Jul 00:32:44.050 * Before turning into a replica, using my own master parameters to synthesize a cached master: I may be able to synchronize with the new master with just a partial transfer.
[023860] 24 Jul 00:32:44.050 * Ready to accept connections
[023860] 24 Jul 00:32:44.066 * Connecting to MASTER 127.0.0.1:6380
[023860] 24 Jul 00:32:44.066 * MASTER <-> REPLICA sync started
[023860] 24 Jul 00:32:44.066 * Non blocking connect for SYNC fired the event.
[023860] 24 Jul 00:32:44.066 * Master replied to PING, replication can continue...
[023860] 24 Jul 00:32:44.066 * Trying a partial resynchronization (request ae1a082ccb257abc0b8cc0c1c8db992bed64ced9:45249).
[023860] 24 Jul 00:32:49.558 * Full resync from master: f248272b4741de72fb548a0a8d1cfecd05dcfa65:219215
[023860] 24 Jul 00:32:49.746 * MASTER <-> REPLICA sync: receiving streamed RDB from master with EOF to disk
[023860] 24 Jul 00:32:49.746 * Discarding previously cached master state.
[023860] 24 Jul 00:32:49.746 * MASTER <-> REPLICA sync: Flushing old data
[023860] 24 Jul 00:32:49.746 * MASTER <-> REPLICA sync: Loading DB in memory
[023860] 24 Jul 00:32:49.761 * Loading RDB produced by version 7.0.4
[023860] 24 Jul 00:32:49.761 * RDB age 0 seconds
[023860] 24 Jul 00:32:49.761 * RDB memory usage when created 0.00 Mb
[023860] 24 Jul 00:32:49.761 # RDB file was saved with checksum disabled: no check performed.
[023860] 24 Jul 00:32:49.761 * Done loading RDB, keys loaded: 3, keys expired: 0.
[023860] 24 Jul 00:32:49.761 * MASTER <-> REPLICA sync: Finished with success
```

连6379客户端，查看KEY,新增的aa顺利同步

```shell
127.0.0.1:6379> keys *
1) "ip"
2) "port"
3) "aa"
127.0.0.1:6379>
```



### 1.4 Cluster 集群

1.4.1 配置文件

cluster-enabled yes



## **6.（必做）**

搭建 ActiveMQ 服务，基于 JMS，写代码分别实现对于 queue 和 topic 的消息生产和消费，代码提交到 github。

详见：ActivemqApplication.java