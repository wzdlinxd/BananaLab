package homework.lucien.session1;

public class SessionWatermelon {
	//---------------------С�к�������---------------------------
	/**   �߼���
		1�����û�˿ͣ��ǾͲ���
		2������N���˿ͣ�Ҫ��˳�����������Ŀͻ�ҲҪ��������������0����
		3�����������߼��� sell0 ʵ�֡�
		4���������50����ֻ��50����
		5���������С��0����sell0 �׳��쳣���� sell �����쳣���Ǽ�Ϊ��������0����
		5���ڿ���̨��ӡ��������������ȥ���ٸ������xxx�ܹ�����ȥN����
		6��ʵ���Լ��Ĵ�ӡ���麯������ʽ: [1,2,3,4,5];
	 * */
	public static void main(String[] args) {
		int[] num = {-2,1,50,51};
		int[] sell = sell(num);
		printArray(sell);
	}
	
	public static int[] sell(int[] buyNum) {
		//�ж�����Ϊ�ջ�null
		if(null==buyNum||buyNum.length==0) {
			return new int[0];
		}
		//����һ���������������������
		int[] result = new int[buyNum.length];
		int totalBuynum = 0;
		
		for(int i=0;i<buyNum.length;i++) {
			int currentBuynum = buyNum[i];
			int sellnum = 0;
			try {
				sellnum = sell0(currentBuynum);
			} catch (Exception e) {
				sellnum = 0;
			}
			result[i] = sellnum;
			totalBuynum+=sellnum;
			
		}
		System.out.println("lucien�ܹ�����"+totalBuynum+"������");
		
		return result;
	}
	
	private static int sell0(int buynum) throws Exception{
		if(buynum<0) {
			throw new Exception("û��������~~~~~~~~~~~~");
		}
		if(buynum>50) {
			return 50;
		}
		return buynum;
	}
	//ʵ�������ӡ����
	private static void printArray(int[] arr) {
		if(null==arr||arr.length==0) {
			System.out.println("[]");
			return;	
		}
		StringBuilder strb = new StringBuilder().append('[');
		for(int i=0;i<arr.length;i++) {
			if(i==0) {
				strb.append(arr[i]);
			}else {
				strb.append(',').append(arr[i]);
			}
		}
		strb.append(']');
		System.out.println(strb.toString());
		
	}
	

}
