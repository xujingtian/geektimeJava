## 1 选做

自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。

源码

```java
public class App {
    public static void main(String[] args) throws Exception { 
        int a = 30, b = 10;
        add(a, b);
        subtract(a, b);
        multiply(a, b);
        divide(a, b);
        testIf(a,b);
        for(int i=0;i<3;i++){
            a++;
        }
        System.out.println("Hello, World!");
    }

    private static int add(int a, int b) {
        return a + b;
    }

    private static int subtract(int a, int b) {
        return a - b;
    }


    private static int multiply(int a, int b) {
        return a * b;
    }

    private static int divide(int a, int b) {
        return a / b;
    }

    private static void testIf(int a, int b) {
        if(a>b){
            a++;
        }else {
            a--;
        }
    }
}
```

字节码

```java
Compiled from "app.java"
public class App {
  public App();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]) throws java.lang.Exception;
    Code:
       0: bipush        30                  // 将常量30压栈
       2: istore_1                          // 存到本地变量表1的位置上
       3: bipush        10                  // 将常量10压栈
       5: istore_2                          // 存到本地变量表2的位置上
       6: iload_1                           // 从本地变量表上加载1位置上的数据
       7: iload_2                           // 从本地变量表上加载2位置上的数据
       8: invokestatic  #2                  // Method add:(II)I  
      11: pop                               // 结果出栈
      12: iload_1                           // 从本地变量表上加载1位置上的数据
      13: iload_2                           // 从本地变量表上加载2位置上的数据
      14: invokestatic  #3                  // Method subtract:(II)I
      17: pop                               // 结果出栈
      18: iload_1                           // 从本地变量表上加载1位置上的数据
      19: iload_2                           // 从本地变量表上加载2位置上的数据
      20: invokestatic  #4                  // Method multiply:(II)I
      23: pop                               // 结果出栈
      24: iload_1                           // 从本地变量表上加载1位置上的数据
      25: iload_2                           // 从本地变量表上加载2位置上的数据
      26: invokestatic  #5                  // Method divide:(II)I
      29: pop                               // 结果出栈
      30: iload_1                           // 从本地变量表上加载1位置上的数据
      31: iload_2                           // 从本地变量表上加载2位置上的数据
      32: invokestatic  #6                  // Method testIf:(II)V
      35: iconst_0                          // 将常量0压栈
      36: istore_3                          // 将常量0存到本地变量表3的位置上
      37: iload_3                           // 从本地变量表上加载3位置上的数据
      38: iconst_3                          // 将常量3压栈
      39: if_icmpge     51                  
      42: iinc          1, 1                // 本地变量表1的数据加1
      45: iinc          3, 1                // 本地变量表3的数据加1
      48: goto          37                  // 跳出循环
      51: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
      54: ldc           #8                  // String Hello, World!
      56: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      59: return
}
```



## 2 必做

自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 Hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件在我的教室下载。

```java
package com.demo.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        Class<?> helloClass = new HelloClassLoader().findClass("Hello");
        Object o = helloClass.newInstance();
        Method helloMethod = helloClass.getMethod("hello");
        helloMethod.invoke(o);
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = new byte[0];
        try {
            bytes = toByteArray(System.getProperty("user.dir")+"\\src\\com\\demo\\jvm\\Hello.xlass");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, decode(bytes), 0, bytes.length);
    }

    public static byte[] toByteArray(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        }
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] decode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return bytes;
    }

}
```



## 3 必做

画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。

```
-Xmx：最大堆内存
-Xms：最小堆内存
-Xmn：堆的young区的内存
-XX:MetaspaceSize：元数据区的内存
-XX:MaxDirectMemorySize：最大堆外内存
-Xss：线程栈最大内存
```

![image-20220508105930784](D:\99.dean_pc\08.geektime\15.Java\03.code\week01\com.example.jvm\README.assets\image-20220508105930784.png)

![](https://github.com/xujingtian/geektimeJava/blob/master/week01/com.example.jvm/README.assets/image-20220508105930784.png)

