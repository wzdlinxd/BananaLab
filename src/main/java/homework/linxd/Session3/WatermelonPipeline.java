package homework.linxd.Session3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class WatermelonPipeline {


    public static class BananaWatermelon {
        int bananaQuality;

        public BananaWatermelon(int bananaQuality) {
            this.bananaQuality = bananaQuality;
        }
    }


    public static class AppleWatermelon {
        int appleQualtity;

        public AppleWatermelon(int appleQualtity) {
            this.appleQualtity = appleQualtity;
        }
    }


    public static class CommonWatermelon {
        int quality;

        public CommonWatermelon(int quality) {
            this.quality = quality;
        }
    }


    public static void main(String[] args) {
        int[] bananaWatermelonArray = {-1, 0, 5, 60};

        List<BananaWatermelon> bananaWatermelons = new ArrayList<>();

        for (int i = 0; i < bananaWatermelonArray.length; i++) {
            bananaWatermelons.add(new BananaWatermelon(bananaWatermelonArray[i]));
        }

        int[] appleWatermelonArray = {-1, 0, 5, 60};

        List<AppleWatermelon> appleWatermelons = new ArrayList<>();

        for (int i = 0; i < appleWatermelonArray.length; i++) {
            appleWatermelons.add(new AppleWatermelon(appleWatermelonArray[i]));
        }

        List<CommonWatermelon> commonWatermelons = mergeWatermelons(bananaWatermelons, appleWatermelons);

        List<CommonWatermelon> filteredWatermenlon = filterWatermelons(commonWatermelons);

        writeWatermelonReport(filteredWatermenlon);

        sendoutWatermelons(filteredWatermenlon);

        countingWatermelons(filteredWatermenlon);
    }


    public static List<CommonWatermelon> mergeWatermelons(List<BananaWatermelon> bananaWatermelons, List<AppleWatermelon> appleWatermelons) {
        //TODO 这里是需要你自己实现的
        // 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。

        List<CommonWatermelon> result = new ArrayList<CommonWatermelon>();
        Function<BananaWatermelon, CommonWatermelon> bananaToCommon = banana -> new CommonWatermelon(banana.bananaQuality);
        Function<AppleWatermelon, CommonWatermelon> appleToCommon = apple -> new CommonWatermelon(apple.appleQualtity);
        //转换bananaWatermelons
        bananaWatermelons.forEach(banana -> result.add(bananaToCommon.apply(banana)));
        //转换appleWatermelons
        appleWatermelons.forEach(apple -> result.add(appleToCommon.apply(apple)));

        return result;
    }


    public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon> filterWatermelons) {
        //TODO 这里是需要你自己实现的
        //2、使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。
        List<CommonWatermelon> result = new ArrayList<CommonWatermelon>(filterWatermelons);
        Predicate<CommonWatermelon> check = common -> common.quality >= 0 && common.quality <= 50;

        filterWatermelons.forEach(common -> {
            if (!check.test(common)) {
                result.remove(common);
            }
        });



        return result;
    }


    public static void writeWatermelonReport(List<CommonWatermelon> filterWatermelons) {
        //TODO 这里是需要你自己实现的
        //2、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
        //  也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。

        CountDownLatch count = new CountDownLatch(5 * filterWatermelons.size());

        BiConsumer<CommonWatermelon, Integer> commonWatermelonConsumer1 = ((commonWatermelon, index) -> {
            new Thread(() -> {
                System.out.println(" 1号检察员检查第 " + index + " 个西瓜，质量为 " + commonWatermelon.quality + " 完毕");
                count.countDown();;
            }).start();
            new Thread(() -> {
                System.out.println(" 2号检察员检查第 " + index + " 个西瓜，质量为 " + commonWatermelon.quality + " 完毕");
                count.countDown();;
            }).start();
            new Thread(() -> {
                System.out.println(" 3号检察员检查第 " + index + " 个西瓜，质量为 " + commonWatermelon.quality + " 完毕");
                count.countDown();;
            }).start();
            new Thread(() -> {
                System.out.println(" 4号检察员检查第 " + index + " 个西瓜，质量为 " + commonWatermelon.quality + " 完毕");
                count.countDown();;
            }).start();
            new Thread(() -> {
                System.out.println(" 5号检察员检查第 " + index + " 个西瓜，质量为 " + commonWatermelon.quality + " 完毕");
                count.countDown();;
            }).start();
        });

        int index = 0;
        for (CommonWatermelon commonWatermelon : filterWatermelons) {
            commonWatermelonConsumer1.accept(commonWatermelon, ++index);
        }

        //如果没有全部报告都写完，阻塞在这里不允许返回。
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void sendoutWatermelons(List<CommonWatermelon> commonWatermelons) {
        System.out.println("质量为：");
        if (commonWatermelons == null || commonWatermelons.size() <= 0) {
            System.out.print("[]");
            return;
        }
        System.out.print("[");

        int size = commonWatermelons.size();

        for (int i = 0; i < size; i++) {
            //除了最后一个元素不用打印逗号，其他都要在后面加逗号
            if (i != size-1) {
                System.out.print(commonWatermelons.get(i).quality + ",");
            } else {
                System.out.print(commonWatermelons.get(i).quality);
            }
        }

        System.out.print("]");

    }


    public static void countingWatermelons(List<CommonWatermelon> commonWatermelons) {
        final Integer[] sum = {0};

        commonWatermelons.forEach(common -> sum[0] += common.quality );
        System.out.println("");
        System.out.println("总质量为：" + sum[0]);
    }
}
