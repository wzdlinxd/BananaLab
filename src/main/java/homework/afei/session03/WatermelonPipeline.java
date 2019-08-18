package session003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ʵ�ֶ������ϵ���ˮ�ߴ������ղ����ϸ�����ϡ�
 * <p>
 * <p>
 * 1������������ʹ�� stream ������Ȼ�� Function ת��Ϊͬһ�����ϡ�
 * <p>
 * 2��ʹ�� Predicate ������������С0����������50�Ĺ���������������
 * <p>
 * 3��ʹ�� Consumer ������5�������Ա��ÿ�������Ա������ÿ�����ϣ�ʹ�� System.out.println("X �ż��Ա���� N �����ϣ�����Ϊ Y ���")���ù���ʹ�ö��߳���ɡ�
 * Ҳ����˵���ǻᴴ���� 50 ���̣߳������м����Ա�����ɺ�ʹ�� CountDownlatch ��ȷ�������̶߳�ִ������ˣ����۲����еļ��鱨�档
 * <p>
 * 4�������ڻ�ʣ�µ����ϰ�˳���ӡ��������ʽ [1��3��4��5��6]��
 * <p>
 * 5��ʹ�� reduce ����һ�£��������������ܼ��ж��ٸ�������ӡ����
 *
 * ------------------------------------------------------------
 * ��ʵ�־�����չ�� Lambda ��̵ĸ��ּ��ɣ�������ʵ������ʽ��̷�ʽ��
 * ------------------------------------------------------------
 *
 * @author logow.whl
 */
public class WatermelonPipeline {


    public static class BananaWatermelon {
        int bananaQuantity;

        public BananaWatermelon(int bananaQuantity) {
            this.bananaQuantity = bananaQuantity;
        }
    }


    public static class AppleWatermelon {
        int appleQuantity;

        public AppleWatermelon(int appleQuantity) {
            this.appleQuantity = appleQuantity;
        }
    }


    public static class CommonWatermelon {
        int quantity;

        public CommonWatermelon(int quantity) {
            this.quantity = quantity;
        }
    }


    public static void main(String[] args) {

        int[] bananaWatermelonArray = {8,5,7,5,8,4,-1, 0, 5, 60, 30};
        List<BananaWatermelon> bananaWatermelons = Arrays.stream(bananaWatermelonArray).mapToObj(BananaWatermelon::new).collect(Collectors.toList());

        int[] appleWatermelonArray = {7,5,8,4,5,8,6,8,5,8,4,-1, 0, 5, 60, 20};
        List<AppleWatermelon> appleWatermelons = Arrays.stream(appleWatermelonArray).mapToObj(AppleWatermelon::new).collect(Collectors.toList());

        List<CommonWatermelon> commonWatermelons = mergeWatermelons(bananaWatermelons, appleWatermelons);

        List<CommonWatermelon> filteredWatermenlon = filterWatermelons(commonWatermelons);

        writeWatermelonReport(filteredWatermenlon);

        sendoutWatermelons(filteredWatermenlon);

        countingWatermelons(filteredWatermenlon);
    }


    public static List<CommonWatermelon> mergeWatermelons(List<BananaWatermelon> bananaWatermelons, List<AppleWatermelon> appleWatermelons) {
        Objects.requireNonNull(bananaWatermelons);   //�ж϶����Ƿ�Ϊ�գ��յ�ʱ�򱨿�ָ���쳣��ʱ��Ϳ���ʹ���������
        Objects.requireNonNull(appleWatermelons);

        // 1������������ʹ�� stream ������Ȼ�� Function ת��Ϊͬһ�����ϡ�
        Stream<CommonWatermelon> bananaStream = bananaWatermelons.stream().map(w -> new CommonWatermelon(w.bananaQuantity));
        Stream<CommonWatermelon> appleStream = appleWatermelons.stream().map(w -> new CommonWatermelon(w.appleQuantity));

        return Stream.concat(bananaStream, appleStream).collect(Collectors.toList());
    }


    public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon> filterWatermelons) {
        Objects.requireNonNull(filterWatermelons);

        //2��ʹ�� Predicate ������������С0����������50�Ĺ���������������
        return filterWatermelons.stream().filter(w -> !(w.quantity < 0 || w.quantity > 50)).collect(Collectors.toList());
    }


    public static void writeWatermelonReport(List<CommonWatermelon> filterWatermelons) {
//        Objects.requireNonNull(filterWatermelons);

        Consumer<CommonWatermelon> consumer = watermelon -> {
            try {
                TimeUnit.MILLISECONDS.sleep(watermelon.quantity * 100L);//???
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        int checkerCount = 5;
        AtomicInteger seq = new AtomicInteger(0);//ԭ�Ӳ�����
        CountDownLatch latch = new CountDownLatch(checkerCount * filterWatermelons.size()); //����������

        // ���� 5 �����Ա��5 * N ���߳�ͬʱ�����������
        Stream.generate(seq::incrementAndGet)
                .limit(checkerCount)
                .flatMap(id -> mapToList(filterWatermelons,
                        (index, watermelon) -> new WatermelonCheck(id, index, watermelon, latch)).stream())
                .forEach(watermelonCheck -> new Thread(() -> watermelonCheck.execute(consumer)).start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sendoutWatermelons(List<CommonWatermelon> commonWatermelons) {
        Objects.requireNonNull(commonWatermelons);
        String string = commonWatermelons.stream().map(watermelon -> String.valueOf(watermelon.quantity))
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(string);
    }

    public static void countingWatermelons(List<CommonWatermelon> commonWatermelons) {
        Objects.requireNonNull(commonWatermelons);
        int count = commonWatermelons.stream().mapToInt(w -> w.quantity).reduce(0, (a, b) -> a + b);
        System.out.println("���������ܼ� " + count + "��");
    }

    //�������Ŀǰ������������Ҫ����ϸ�о��о���
    private static <T, R> List<R> mapToList(List<T> list, BiFunction<Integer, T, R> mapper) {
        List<R> results = new ArrayList<>(list.size());
        int index = 0;
        for (T t : list) {
            R r = mapper.apply(index++, t);
            results.add(r);
        }
        return results;
    }

    private static class WatermelonCheck {
        final int checkerId;
        final int index;
        final CommonWatermelon watermelon;
        final CountDownLatch latch;

        WatermelonCheck(int checkerId, int index, CommonWatermelon watermelon, CountDownLatch latch) {
            this.checkerId = checkerId;
            this.index = index;
            this.watermelon = watermelon;
            this.latch = latch;
        }
        //���������÷���Ҳ��Ū�����ΪʲôҪ���������ÿ������Լ�Ŀǰ�ܹ�ʵ�ֵķ���������ʵ��һ�顣 2019-08-01  21:55
        void execute(Consumer<? super CommonWatermelon> consumer) {
            try {
                consumer.accept(watermelon);
                System.out.printf("�� %d �ż��Ա���� %d �����ϣ�����Ϊ %d ���%n", checkerId, index + 1, watermelon.quantity);
            } catch (Exception e) {
                System.out.printf("�� %d �ż��Ա���� %d �����ϣ�����Ϊ %d ʧ��: %s%n", checkerId, index + 1, watermelon.quantity, e);
            }
            latch.countDown();
        }
    }
}