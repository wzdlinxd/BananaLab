package homework.lucien.session2;

public class StackTest {

	public static void main(String[] args) {
		// ��̬��ԭ��
		/**
		 * �������:
		 * 1�� Stack ���͵����� stack1 ָ�� StackImpl ���͵�ʵ��; ������ stack2ָ��StackWithLogImpl ���͵�ʵ��
		 * ������ָ��һ���½��� StackWithLogImpl ���͵�ʵ��; ͨ�� Stack ���͵����� stack1 ���� StackImpl ����ʵ����
		 * push ������ stack1 �����Ԫ�� "3", ����̨�����; ͨ�� Stack ���͵����� stack2 ���� StackWithLogImpl
		 * ����ʵ���� push ������ stack2 �����Ԫ�� "3", ����̨���� push ������ log; ���� java ��̬��һ�����ͳ���.
		 * 2��stack1 �� stack2 ��ȻͬΪ Stack���͵�����, ������ָ���ʵ�����Ͳ�����ͬ, ����ִ�еľ��巽��Ҳ����ͬ.
		 */
		Stack stack1 = new StackImpl();
		Stack stack2 = new StackWithLogImpl();
		stack1.push(3);
		stack2.push(2);
		stack2.push(3);
		stack2.pop();
	}
}
