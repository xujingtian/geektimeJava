package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.properties.DBProperties;

/**
 * 数据库连接加上rewriteBatchedStatements=true参数，使用preparement批量执行插入语句
 */
public class UsePreparedStatement {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(DBProperties.driver);
        long start = 0;
        String sql = "insert t_user(name,login_name,phone_number,create_time,update_time) values(?,?,?,?,?)";
        int[] resultSet = null;
        try(Connection connection = DriverManager.getConnection(DBProperties.url, DBProperties.username, DBProperties.password);
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            String truncate = "truncate table t_user";
            Statement statement1 = connection.createStatement();
            statement1.execute(truncate);
            statement1.close();
            for(int i = 0; i < 1000000; i++) {
                statement.setString(1, "iam"+Integer.toString(i));
                statement.setString(2, "dean"+Integer.toString(i));
                statement.setString(3, "1732538479"+Integer.toString(i));
                Long tLong= System.currentTimeMillis();
                statement.setLong(4, tLong);
                statement.setLong(5, tLong);
                statement.addBatch();
            }
            start = System.currentTimeMillis();
            resultSet = statement.executeBatch();
            if(resultSet.length > 0) {
                System.out.println("success");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("用时: " + (end - start) + "毫秒");
    }
}