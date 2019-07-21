package homework.tonemy.session2;

public interface Stack {
	/**
	 * ��ջ
	 * @return boolean
	 */
	boolean push(int value);

	/**
	 * ��ջ
	 * @return int
	 * @throws Exception 
	 */
	int pop() ;

	/**
	 * �鿴ջ��Ԫ��
	 */

	int peak();

	/**
	 * ջ�Ĵ�С
	 */

	int size();

	/**
	 * ջ�Ƿ�Ϊ��
	 * @return
	 */
	boolean isEmpty();
}
