package com.example;

/**
 * Hello world!
 */
public final class ThreadMethod {
    private ThreadMethod() {
    }

    private volatile Integer value = null;

    public void sum(int num) {
        value = fibo(num);
    }

    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    public int getValue() {
        while (value == null) {
        }
        return value;
    }

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
}
