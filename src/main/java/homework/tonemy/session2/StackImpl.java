package homework.tonemy.session2;

import java.util.Arrays;

import java.util.function.IntPredicate;

public class StackImpl implements Stack {
	int maxSize = 5;
	int[] arr = new int[maxSize];
	int cur = -1; 
	@Override
	public boolean push(int value) {
		// TODO Auto-generated method stub
		cur ++;
		if(cur == maxSize) {
			maxSize *= 2;
			arr = Arrays.copyOf(arr, maxSize);
		}
		arr[cur] = value;
		return true;
	}

	@Override
	public int pop()  {
		// TODO Auto-generated method stub
		if(cur == -1) {
				throw new StackUnderFlow("Õ»Îª¿Õ");
		}
		int top = arr[cur];
		cur --;
		return top;
	}

	@Override
	public int peak() {
		// TODO Auto-generated method stub
		if(cur == -1) {
			throw new StackUnderFlow("Õ»Îª¿Õ");
		}
		int top = arr[cur];
		return top;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return cur+1;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(cur == -1) {
			return true;
		}
		return false;
	}

}
