package homework.lucien.session2;

public interface Stack {

	/**
	 * ��ջ
	 * @return
	 */
	int push(int e);

	/**
	 * ��ջ
	 * @return
	 */
	int pop();

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
