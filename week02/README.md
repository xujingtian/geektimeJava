# 作业
***
## **1.（选做）**
&ensp;使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例



```java
package com.company;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
package com.geektime.gclogss;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class GCLogAnalysis {
    private static Random random = new Random();
    public static void main(String[] args){
        //当前毫秒时间戳
        long startMillis = System.currentTimeMillis();
        //持续运行毫秒数；可根据需要进行修改
        long timeoutMillis = TimeUnit.SECONDS.toMillis(1);
        //结束时间戳
        long endMillis =  startMillis+timeoutMillis;
        LongAdder counter = new LongAdder();
        //缓存一部分对象；进入老年代
        int cacheSize = 2000;
        Object[] cachedGarbage = new Object[cacheSize];
        // 在此时间范围内，持续循环
        while (System.currentTimeMillis() < endMillis) {
            //生成垃圾对象
            Object garbage = generateGarbage(100*1024);
            counter.increment();
            int randomIndex = random.nextInt(2*cacheSize);
            if(randomIndex <cacheSize) {
                cachedGarbage[randomIndex] = garbage;
            }
        }
        System.out.println("执行结束！共生成对象次数："+counter.longValue());

    }
    private static Object generateGarbage(int max) {
        int randomSize = random.nextInt(max);
        int type = randomSize%4;
        Object result=null;
        switch(type){
            case 0:
            result = new int[randomSize];
            case 1:
            result = new byte[randomSize];
            case 2:
            result = new byte[randomSize];
            default:
            StringBuilder builder = new StringBuilder();
            String randomString="randomString-Anything";
            while(builder.length()<randomSize){
                builder.append(randomString);
            }
            result =builder.toString();
            break;

        }
        return result;
    }
}

```



此次分别测试内存级别为：128m、512M、1G、2G、4G，对串行、并行、CMS、G1分别进行测试，测试大致命令如下：

```sh
java -XX:+UseSerialGC -Xms128m -Xmx128m -Xlog:gc F:\Code\Java\JAVA-000\Week_01\example\src\com\company\GCLogAnalysis.java
java -XX:+UseParallelGC -Xms128m -Xmx128m -Xlog:gc F:\Code\Java\JAVA-000\Week_01\example\src\com\company\GCLogAnalysis.java
java -XX:+UseConcMarkSweepGC -Xms128m -Xmx128m -Xlog:gc F:\Code\Java\JAVA-000\Week_01\example\src\com\company\GCLogAnalysis.java
java -XX:+UseG1GC -Xms128m -Xmx128m -Xlog:gc F:\Code\Java\JAVA-000\Week_01\example\src\com\company\GCLogAnalysis.java
```



| GC/MEM             | 128M | 512M    | 1G      | 2G      | 4G      |
| ------------------ | ---- | ------- | ------- | ------- | ------- |
| UseSerialGC        | OOM  | 13908.4 | 18831.2 | 17942.7 | 15903.8 |
| UseParallelGC      | OOM  | 10730.8 | 19241.3 | 21413.5 | 21765.2 |
| UseConcMarkSweepGC | OOM  | 13729.9 | 18409.2 | 17671.8 | 17331.6 |
| UseG1GC            | OOM  | 15307.3 | 23348.2 | 23697.0 | 21144.1 |

> 从内存不断增加来看，可以看到串行、并行、CMS都有一个先上升后下降的现象，我们从GC的原理来看，内存越大，需要进行标记和整理的对象就越多，而GC的处理能力有一个上限，在能力范围内，也就是内存较小的时候，它能在较短时间内标记和清理完全；但随着内存增大，超过能力范围，其标记和清除的负担加重，性能就表现出下降的趋势了
>

## **2.（选做）**

使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例

分别测试串行、并行、CMS、G1等GC，内存测试分别为512M、1G、2G、4G、8G

测试的数据如下：sb -u http://localhost:8081/api/hello -c 20 -N 60

| GC/内存 | 512M | 1G   | 2G   | 4G   |
| ------- | ---- | ---- | ---- | ---- |
| 串行    | 5120 | 4488 | 4696 | 4965 |
| 并行    | 5137 | 5289 | 5334 | 5228 |
| CMS     | 5083 | 5225 | 5526 | 5131 |
| G1      | 5400 | 4947 | 5163 | 5158 |

> 测试的结果发现大家的差距都不是太大，有点出乎意料。通过调节sb的参数，测试结果也没有太大的变化。
>

> 从上面简单的看出串行GC是不适合Web场景的，越到后面性能越低，但并行、CMS、G1在测试中性能差距不是太大。
>
> 猜测性能没有提升的原因是GC能应对当前的场景，性能的瓶颈现在是在网络请求处理上。



## **4.（必做）**
根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub

> # GC 算法
>
> - 标记-复制。优点：存活的对象越少，复制需要的空间就越小；而且复制后的对象们内存空间排布紧凑，避免空间碎片的问题。缺点：有一部分空间被浪费。如果存活对象大且多的话复制成本比较高。**适用每次 GC 存活对象小而美的情况。年轻代**
> - 标记-清除。直接清除可回收对象，不进行内存整理。，优点：单次 STW 的时间可能要短一些。缺点：但是产生的内存碎片，可能导致内存总空间足够，但是没一块连续的空间存放对象的问题，内存利用率降低；对象放不下，可能触发额外的 GC。**适用对象存活率高的情况。老年代**
> - 标记-清除-整理。STW 时间可能会稍长一些，内存碎片问题得到解决。适用对象存活率高的情况。
>
> # Serial GC
>
> -XX:+UseSerialGC
>
> 串行 GC 单线程执行，在 GC 期间其他业务线程均暂停，暂停的时间长。
>
> 串行 GC 对年轻代采用标记复制算法。对老年代使用标记-清除-整理算法。
>
> 串行 GC 简单直接，在单核 CPU 环境下比较适用。
>
> ## -XX:+UseParNewGC
>
> ParNew 收集器，多线程版本的 Serial。配合 CMS 使用。
>
> # Parallel GC
>
> -XX:UseParallelGC -XX:UseParallelOldGC
>
> 使用的 GC 算法和串行的一样。
>
> 默认的 GC 线程数是 CPU core 数，该收集器的目标更倾向于**提高系统吞吐量**，有时候单次的 GC 暂停时间较长。
>
> # CMS GC
>
> -XX:UseConcMarkSweepGC
>
> 对老年代没有整理操作，使用 free-list 进行内存空间的管理。默认的核心线程数 CPU 核数 / 4。
>
> 可以和业务线程并发执行，GC 暂停时间少。
>
> # G1 GC
>
> 打破整个分区的理论，把内存划分成多个小块进行管理。对每个小块的垃圾数量进行预估，优先回收垃圾多的 Region。可预期的垃圾停顿时间



## **6.（必做）**
&ensp;写一段代码，使用 HttpClient 或 OkHttp 访问 [ http://localhost:8801 ](http://localhost:8801/)，代码提交到 GitHub。



```java
package com.geektime.gclogss;


import com.sun.deploy.net.HttpResponse;
import sun.net.www.http.HttpClient;


import java.io.IOException;
import java.io.InputStream;

public class HttpClientUtil {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801/";
        System.out.println(HttpClientUtil.httpGet(url));

    }

    /**
     * get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static String httpGet(String url) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream in = entity.getContent();
            byte[] buffer = new byte[in.available()];
            in.read(buffer, 0, in.available());
            return new String(buffer);
        }
        return null;
    }
}

```

```bash
hello,kimmking

Process finished with exit code 0
```