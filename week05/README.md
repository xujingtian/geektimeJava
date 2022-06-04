## 作业2

### 作业要求

&ensp;&ensp;&ensp;&ensp;写代码实现Spring Bean的装配，方式越多越好（XML、Annotation都可以）,提交到Github

### 代码实现

实现代码：week05\bean\src\main\java\com\example\bean

测试代码：week05\bean\src\test\java\com\example\bean\auto

- xml配置：

> ![xml](https://github.com/xujingtian/geektimeJava/blob/master/week05/README.assets/image-20220604161200770.png)
>
> ![image-20220604161200770](D:\99.dean_pc\08.geektime\15.Java\03.code\week05\README.assets\image-20220604161200770.png)

- java代码：

> ![javacode](https://github.com/xujingtian/geektimeJava/blob/master/week05/README.assets/image-20220604160549432.png)
>
> ![image-20220604160549432](D:\99.dean_pc\08.geektime\15.Java\03.code\week05\README.assets\image-20220604160549432.png)



- 自动注解：

> ![autowiring](https://github.com/xujingtian/geektimeJava/blob/master/week05/README.assets/image-20220604160214060.png)
>
> ![image-20220604160214060](D:\99.dean_pc\08.geektime\15.Java\03.code\week05\README.assets\image-20220604160214060.png)
>
> 

## 作业5

### 作业要求

&ensp;&ensp;&ensp;&ensp;研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：

- 1）使用 JDBC 原生接口，实现数据库的增删改查操作。
- 2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
- 3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。



### 代码实现

- 实现代码：week05\bean\src\main\java\com\example\database
  - jdbc：放置原生jdbc操作和事务操作
  - Hikari：放置Hikari简单示例
- 测试代码：
  - jdbc:运行main函数即可
  - Hikari 运行其中的：HikariApplication，设置为启动以后自动连接查询