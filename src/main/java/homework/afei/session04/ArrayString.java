package session004;

import java.util.Objects;

public class ArrayString implements StringInterface {
	
	char[] chAy;  //����ֻ�Ƕ���һ���ַ������顣
	
	//����String����Ĺ��캯����
	public ArrayString(String str) {
        Objects.requireNonNull(str);
        this.chAy = str.toCharArray();
    }
	
	//����ն���Ĺ��캯����
	public ArrayString() {
        this.chAy = "".toCharArray();//���û�д�String���Ͱ����մ���
    }
	
	
	//�����ַ�����Ĺ��캯����Ϊ��ʵ�ַ������ַ���������
		public ArrayString(char[] chars) {
			this.chAy=chars;
	        this.chAy = "".toCharArray();//���û�д�String���Ͱ����մ���
	    }
	
	
	public static void main(String[] args) {
		ArrayString as=new ArrayString("afei") ;
		int len=as.length();
		char pos=as.charAt(3);
		char[] test001= {'a','f'};
		int existNum=as.indexOf(test001);//�����һ���ַ����飬����Ҫ���ַ���
		
		
		
		System.out.println("�ַ�������Ϊ��"+len);
		System.out.println("��N���ַ���ֵΪ����"+pos);
		System.out.println("���ַ�����λ��Ϊ����"+existNum);
		
				
	}
	
	
	
	@Override
	public int length() {
		// TODO Auto-generated method stub
		return this.chAy.length;
	}

	@Override
	public char charAt(int position) {
		// TODO Auto-generated method stub
		if ((position < 0) || (position >= this.chAy.length)) {
            throw new StringIndexOutOfBoundsException(position);
        }
        return this.chAy[position];
		
	}

	//������Ҫע�⣬�������һ���ַ����飬����һ���ַ���
	@Override
	public int indexOf(char[] target) {
		// TODO Auto-generated method stub
        Objects.requireNonNull(target);
        if (target.length == 0 || target.length > this.chAy.length) {
            return -1;
        }

        int max = this.chAy.length - target.length;
        for (int i = 0; i <= max; i++) {
            if (target[0] == this.chAy[i]) {
                for (int j = 1; j < target.length; j++) {
                    if (target[j] != this.chAy[i + j]) {
                        return -1;
                    }
                }
                return i;
            }
        }

        return -1;
	}

	@Override
	public StringInterface subString(int start, int end) {
		// TODO Auto-generated method stub
//		ArrayString si=new ArrayString(); --�½�һ���ַ������캯�����ɣ�
		
		if(start<0 || end>this.chAy.length ||end<start) {
			try {
				throw new Exception("��β�����");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int subStrLen=end-start;
		char[] char1=new char[subStrLen];
		for(int i=0;i<subStrLen;i++) {
			char1[i]=this.chAy[start];
			start++;
		}
		
		return new ArrayString(char1);
	}

	
	/**
	 * ��β��ת�ַ�����Ҫ��ֻ��ռ�� O(1) �Ķ���ռ�
	 * @return
	 */
	@Override
	public StringInterface reverse() {
		// TODO Auto-generated method stub
		char[] char2=new char[this.length()];
		int index=0;
		for(int i=this.length();i>0;i--) {
			char2[index]=this.chAy[i];
			index++;
		}
		
		return new ArrayString(char2);
	}

}
