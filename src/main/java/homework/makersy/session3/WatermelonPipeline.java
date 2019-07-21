package homework.makersy.session3;


import java.util.*;
import java.util.concurrent.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WatermelonPipeline {

	public static class BananaWatermelon{
		int bananaQuantity;
		public BananaWatermelon(int bananaQuantity){
			this.bananaQuantity = bananaQuantity;
		}
	}


	public static class AppleWatermelon{
		int appleQuantity;
		public AppleWatermelon(int appleQuantity){
			this.appleQuantity = appleQuantity;
		}
	}


	public static class CommonWatermelon{
		int quantity;
		public CommonWatermelon(int quantity){
			this.quantity = quantity;
		}
	}


	public static void main(String[] args) {

		int[] bananaWatermelonArray = {-1,0,5,60};

		List<BananaWatermelon> bananaWatermelons = new ArrayList<>();

		for(int i = 0 ; i < bananaWatermelonArray.length ; i++){
			bananaWatermelons.add(new BananaWatermelon(bananaWatermelonArray[i]));
		}

		int[] appleWatermelonArray = {-1,0,5,60};

		List<AppleWatermelon> appleWatermelons = new ArrayList<>();

		for(int i = 0 ; i < appleWatermelonArray.length ; i++){
			appleWatermelons.add(new AppleWatermelon(appleWatermelonArray[i]));
		}


		List<CommonWatermelon> commonWatermelons = mergeWatermelons(bananaWatermelons, appleWatermelons);

		List<CommonWatermelon> filteredWatermenlon = filterWatermelons(commonWatermelons);

		writeWatermelonReport(filteredWatermenlon);

		sendoutWatermelons(filteredWatermenlon);

		countingWatermelons(filteredWatermenlon);

	}


	private static List<CommonWatermelon> mergeWatermelons(List<BananaWatermelon> bananaWatermelons, List<AppleWatermelon> appleWatermelons) {
		//这里是需要你自己实现的
		// 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。
		List<CommonWatermelon> res = new ArrayList<>();

		if (bananaWatermelons == null && appleWatermelons == null) {
			return null;
		}

		//转换方法
		Function<BananaWatermelon, CommonWatermelon> banana2Common = a -> new CommonWatermelon(a.bananaQuantity);
		Function<AppleWatermelon, CommonWatermelon> apple2Common = a -> new CommonWatermelon(a.appleQuantity);

		//stream遍历
		res.addAll(bananaWatermelons.stream().map(banana2Common).collect(Collectors.toList()));
		res.addAll(appleWatermelons.stream().map(apple2Common).collect(Collectors.toList()));

		return res;
	}




	private static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon> filterWatermelons){
		//这里是需要你自己实现的
		//2、使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。

		//predicate 作为filter传入参数，判断用
		Predicate<CommonWatermelon> predicate = a -> a.quantity>=0;
		predicate = predicate.and(a -> a.quantity <= 50);
		/*
		 * filter:
		 * Returns a stream consisting of the elements of this stream that match
		 * the given predicate.
		 * 返回符合条件的
		 */

		return filterWatermelons.stream().filter(predicate).collect(Collectors.toList());

	}




	private static void writeWatermelonReport(List<CommonWatermelon> filterWatermelons){
		//这里是需要你自己实现的
		//2、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
		//  也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。

		//5个任务的倒计时
		CountDownLatch countDownLatch = new CountDownLatch(5);

		//5个consumer
		Consumer<CommonWatermelon> commonWatermelonConsumer1 = (commonWatermelon -> {
			int index = filterWatermelons.indexOf(commonWatermelon);
			System.out.println("1号检察员检查第 " + (index + 1) + " 个西瓜，质量为 " + commonWatermelon.quantity + "。 完毕。");
			if (index == filterWatermelons.size() - 1) {
				countDownLatch.countDown();
			}
		});

		Consumer<CommonWatermelon> commonWatermelonConsumer2 = (commonWatermelon -> {
			int index = filterWatermelons.indexOf(commonWatermelon);
			System.out.println("2号检察员检查第 " + (index + 1) + " 个西瓜，质量为 " + commonWatermelon.quantity + "。 完毕。");
			if (index == filterWatermelons.size() - 1) {
				countDownLatch.countDown();
			}
		});

		Consumer<CommonWatermelon> commonWatermelonConsumer3 = (commonWatermelon -> {
			int index = filterWatermelons.indexOf(commonWatermelon);
			System.out.println("3号检察员检查第 " + (index + 1) + " 个西瓜，质量为 " + commonWatermelon.quantity + "。 完毕。");
			if (index == filterWatermelons.size() - 1) {
				countDownLatch.countDown();
			}
		});

		Consumer<CommonWatermelon> commonWatermelonConsumer4 = (commonWatermelon -> {
			int index = filterWatermelons.indexOf(commonWatermelon);
			System.out.println("4号检察员检查第 " + (index + 1) + " 个西瓜，质量为 " + commonWatermelon.quantity + "。 完毕。");
			if (index == filterWatermelons.size() - 1) {
				countDownLatch.countDown();
			}
		});

		Consumer<CommonWatermelon> commonWatermelonConsumer5 = (commonWatermelon -> {
			int index = filterWatermelons.indexOf(commonWatermelon);
			System.out.println("5号检察员检查第 " + (index + 1) + " 个西瓜，质量为 " + commonWatermelon.quantity + "。 完毕。");
			if (index == filterWatermelons.size() - 1) {
				countDownLatch.countDown();
			}
		});

//		//线程池 最大线程数5000
//		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 5000, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

		for(CommonWatermelon commonWatermelon : filterWatermelons){
			new Thread(()->{
				commonWatermelonConsumer1.accept(commonWatermelon);
			}).start();
			new Thread(()->{
				commonWatermelonConsumer2.accept(commonWatermelon);
			}).start();
			new Thread(()->{
				commonWatermelonConsumer3.accept(commonWatermelon);
			}).start();
			new Thread(()->{
				commonWatermelonConsumer4.accept(commonWatermelon);
			}).start();
			new Thread(()->{
				commonWatermelonConsumer5.accept(commonWatermelon);
			}).start();

//			threadPoolExecutor.execute(() -> commonWatermelonConsumer1.accept(commonWatermelon));
//			threadPoolExecutor.execute(() -> commonWatermelonConsumer2.accept(commonWatermelon));
//			threadPoolExecutor.execute(() -> commonWatermelonConsumer3.accept(commonWatermelon));
//			threadPoolExecutor.execute(() -> commonWatermelonConsumer4.accept(commonWatermelon));
//			threadPoolExecutor.execute(() -> commonWatermelonConsumer5.accept(commonWatermelon));
		}

		//如果没有全部报告都写完，阻塞在这里不允许返回。
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println("检查完成！");
		}
	}


	//打印每个西瓜
	public static void sendoutWatermelons(List<CommonWatermelon>  commonWatermelons){
		System.out.print("[");
		int size = commonWatermelons.size();
		if (size > 0) {
			for (int i = 0; i < commonWatermelons.size(); i++) {
				if (i != 0) {
					System.out.print(",");
				}
				System.out.print(commonWatermelons.get(i).quantity);
			}
		}
		System.out.println("]");
	}

	/**
	 * 返回西瓜数量
	 * @param commonWatermelons
	 */
	public static void countingWatermelons(List<CommonWatermelon> commonWatermelons) {
//		System.out.println(commonWatermelons.size());
		long res = commonWatermelons.stream().mapToLong(a->1L).reduce(0, (a, b)->a+b);
		System.out.println(res);
	}

}
