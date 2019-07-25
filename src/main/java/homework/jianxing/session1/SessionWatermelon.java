package homework.jianxing.session1;

/**
 * 逻辑：

 1、如果没顾客，那就不卖

 2、来了N个顾客，要按顺序卖，不卖的客户也要告诉他不卖（卖0）。

 3、单个卖的逻辑在  sell0 实现。

 4、如果超过50个，只卖50个。

 5、如果需求小于0个，sell0 抛出异常，在 sell 捕获异常并登记为不卖（卖0）。

 5、在控制台打印出最终这批卖出去多少个。术语：xxx总共卖出去N个。

 6、实现自己的打印数组函数。格式: [1,2,3,4,5];
 */
public class SessionWatermelon {

    private static final int BUY_NUM_LIMIT = 50;

	public static void main(String[] args) {
	    int[] buyNums = {2, 50, 60, 0, -1};
        int[] sellNums = sell(buyNums);
        print(sellNums);
    }

	public static int[] sell(int[] buyNums){
        if (buyNums == null || buyNums.length == 0) {
            return new int[0];
        }

        int[] sellNums = new int[buyNums.length];
        int sellTotal = 0;
        for (int i = 0; i < buyNums.length; i++) {
            int sellNum = 0;
            try {
                sellNum = sell0(buyNums[i]);
            } catch (Exception e) {
                System.err.println("交易异常: " + e);
            }
            sellNums[i] = sellNum;
            sellTotal += sellNum;
        }

        System.out.printf("健行总共卖出去 %d 个%n", sellTotal);

        return sellNums;
	}

	private static int sell0(int buyNum){
	    if (buyNum < 0) {
	        throw new IllegalArgumentException("buyNum must not be negative: " + buyNum);
        }

        if (buyNum > BUY_NUM_LIMIT) {
	        return BUY_NUM_LIMIT;
        }

		return buyNum;
	}

    private static void print(int[] arr) {
        StringBuilder sb = new StringBuilder();

        sb.append('[');
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sb.append(arr[i]);
            } else {
                sb.append(',').append(arr[i]);
            }
        }
        sb.append(']');

        System.out.println(sb.toString());
    }
}
