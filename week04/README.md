

## **2.（必做）**

思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到

这个方法的返回值后，退出主线程? 写出你的方法，越多越好，提交到 GitHub。



### 2.1 直接使用线程

```java
    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程
        final ThreadMethod method = new ThreadMethod();
        Thread thread = new Thread(() -> {
            method.sum(10);
        });
        thread.start();

        int result = method.getValue(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }
```

运行结果

```shell
PS D:\15.Java\03.code\week04\demo>  d:; cd 'd:\15.Java\03.code\week04\demo'; & 'C:\Program Files\Eclipse Adoptium\jdk-8.0.322.6-hotspot\bin\java.exe' '-cp' 'D:\15.Java\03.code\week04\demo\target\classes' 'com.example.ThreadMethod' 
异步计算结果为：89
使用时间：46 ms
```



### 2.2 异步方法

```java
 public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 异步执行 下面方法

        final SynchronizedMethod method = new SynchronizedMethod();
        Thread thread = new Thread(() -> {
            method.sum(10);
        });
        thread.start();

        int result = method.getValue(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

  private volatile Integer value = null;

    synchronized public void sum(int num) {
        value = fibo(num);
        notifyAll();
    }

    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    synchronized public int getValue() throws InterruptedException {
        while (value == null) {
            wait();
        }
        return value;
    }
```

运行结果

```shell
PS D:\99.dean_pc\08.geektime\15.Java\03.code\week04\demo> d:; cd 'd:\99.dean_pc\08.geektime\15.Java\03.code\week04\demo'; & 'C:\Program Files\Eclipse Adoptium\jdk-8.0.322.6-hotspot\bin\java.exe' '-cp' 'D:\99.dean_pc\08.geektime\15.Java\03.code\week04\demo\target\classes' 'com.example.SynchronizedMethod'
异步计算结果为：89
使用时间：41 ms
```



## **6.（必做）**

把多线程和并发相关知识梳理一遍，画一个脑图，截图上传到 GitHub 上。 可选工具:xmind，百度脑图，wps，MindManage，或其他。



![img](D:\99.dean_pc\08.geektime\15.Java\03.code\week04\README.assets\d17878708ccccc1761b28cb4aa4103cd.png)

