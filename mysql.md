### mysql死锁
```txt
事务A已经开始修改一张表一行数据，事务B同时进来修改同一张表一行数据，
事务B会一直等待(死锁)，等待A事务提交后，事务B才会执行，但是等待时间mysql有一个限制
在规定时间内事务A依然没执行完，事务B就会报错推出
```
#### mysql等待时间变量
```txt
mysql> show session variables like "wait_timeout";
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| wait_timeout  | 28800 |
+---------------+-------+
1 row in set, 1 warning (0.00 sec)
```
#### 构造死锁现象
```shell
mysql> select * from goods;
+------+----------------------+--------+-------+
| id   | name                 | amount | price |
+------+----------------------+--------+-------+
| 1001 | 修改笔记本11111      |      2 |    15 |
| 1002 | 修改笔记本12345other |     20 |  3000 |
+------+----------------------+--------+-------+
2 rows in set (0.00 sec)

mysql>  select `trx_id`,`trx_state`,`trx_started`,`trx_requested_lock_id`,`trx_wait_started`,`trx_mysql_thread_id`,`trx_query`,`trx_lock_structs` from information_schema.INNODB_TRX;
Empty set (0.00 sec)

# A开启事务修改数据
mysql> begin;
Query OK, 0 rows affected (0.00 sec)

mysql> update goods set name='修改笔记本11' where id = 1001;
Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql>  select `trx_id`,`trx_state`,`trx_started`,`trx_requested_lock_id`,`trx_wait_started`,`trx_mysql_thread_id`,`trx_query`,`trx_lock_structs` from information_schema.INNODB_TRX;
+--------+-----------+---------------------+-----------------------+------------------+---------------------+-----------+------------------+
| trx_id | trx_state | trx_started         | trx_requested_lock_id | trx_wait_started | trx_mysql_thread_id | trx_query | trx_lock_structs |
+--------+-----------+---------------------+-----------------------+------------------+---------------------+-----------+------------------+
| 8478   | RUNNING   | 2023-06-18 14:55:37 | NULL                  | NULL             |                   5 | NULL      |                2 |
+--------+-----------+---------------------+-----------------------+------------------+---------------------+-----------+------------------+
1 row in set (0.00 sec)

# B开启事务修改同一条数据
mysql> begin;
Query OK, 0 rows affected (0.00 sec)

mysql> update goods set name='修改笔记本22222' where id = 1001;

### 超过超时时间就会断开并报错
ERROR 1205 (HY000): Lock wait timeout exceeded; try restarting transaction
mysql>


#### 死锁数据查询
mysql>  select `trx_id`,`trx_state`,`trx_started`,`trx_requested_lock_id`,`trx_wait_started`,`trx_mysql_thread_id`,`trx_query`,`trx_lock_structs` from information_schema.INNODB_TRX;
+--------+-----------+---------------------+-----------------------+---------------------+---------------------+---------------------------------------------------------+------------------+
| trx_id | trx_state | trx_started         | trx_requested_lock_id | trx_wait_started    | trx_mysql_thread_id | trx_query                                               | trx_lock_structs |
+--------+-----------+---------------------+-----------------------+---------------------+---------------------+---------------------------------------------------------+------------------+
| 8479   | LOCK WAIT | 2023-06-18 14:57:04 | 8479:34:3:9           | 2023-06-18 14:57:04 |                   8 | update goods set name='修改笔记本22222' where id = 1001 |                2 |
| 8478   | RUNNING   | 2023-06-18 14:55:37 | NULL                  | NULL                |                   5 | NULL                                                    |                2 |
+--------+-----------+---------------------+-----------------------+---------------------+---------------------+---------------------------------------------------------+------------------+
2 rows in set (0.00 sec)

mysql>
mysql>
mysql> select * from information_schema.INNODB_LOCKS;
+-------------+-------------+-----------+-----------+------------------+------------+------------+-----------+----------+-----------+
| lock_id     | lock_trx_id | lock_mode | lock_type | lock_table       | lock_index | lock_space | lock_page | lock_rec | lock_data |
+-------------+-------------+-----------+-----------+------------------+------------+------------+-----------+----------+-----------+
| 8479:34:3:9 | 8479        | X         | RECORD    | `lesson`.`goods` | PRIMARY    |         34 |         3 |        9 | 1001      |
| 8478:34:3:9 | 8478        | X         | RECORD    | `lesson`.`goods` | PRIMARY    |         34 |         3 |        9 | 1001      |
+-------------+-------------+-----------+-----------+------------------+------------+------------+-----------+----------+-----------+
2 rows in set (0.00 sec)

mysql> select * from information_schema.INNODB_LOCK_WAITS;
+-------------------+-------------------+-----------------+------------------+
| requesting_trx_id | requested_lock_id | blocking_trx_id | blocking_lock_id |
+-------------------+-------------------+-----------------+------------------+
| 8479              | 8479:34:3:9       | 8478            | 8478:34:3:9      |
+-------------------+-------------------+-----------------+------------------+
1 row in set (0.00 sec)
```
### mysql查看变量```https://blog.csdn.net/rod0320/article/details/109584570```
### mysql的表锁和行锁```https://zhuanlan.zhihu.com/p/471402921```
