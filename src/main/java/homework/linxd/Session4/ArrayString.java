package homework.linxd.Session4;


/**
 * @Author linxd
 * 这里你自己实现的时候，要去掉 abstract
 */
public class ArrayString implements StringInterface {

    char[] chars;

    public ArrayString() {
        chars = new char[0];
    }

    /**
     * 根据数字返回对应的字符串
     *
     * @param number
     * @return
     */
    public static StringInterface valueOf(Integer number) throws NumberFormatException {
        Integer num = number;

        if (num < 0) {
            num = -num;
        }
        //计算数字位数
        int length = 0;
        int[] size = new int[]{9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};
        for (int i = 0; i < size.length; i++) {
            if (num <= size[i]) {
                length = i + 1;
                break;
            }
        }

        //创建字符数组
        char[] result = null;

        //负数加符号
        if (number < 0) {
            result = new char[length + 1];
            result[0] = '-';
        } else {
            result = new char[length];
        }
        //逐个位拆分
        int index = result.length;
        while (num != 0) {
            result[--index] = (char) (48 + num % 10);
            num = num / 10;
        }

        ArrayString str = new ArrayString();
        str.chars = result;

        return str;
    }

    @Override
    public int length() {
        return chars != null ? chars.length : 0;
    }

    @Override
    public char charAt(int position) {
        if (position < 0 || position >= chars.length) {
            throw new StringIndexOutOfBoundsException(position + "不符合范围");
        }
        return chars[position];
    }

    @Override
    public int indexOf(char[] target) {
        boolean flag = false;
        for (int i = 0; i <= chars.length - target.length; i++) {
            int k = i;
            for (int j = 0; j < target.length; j++) {
                if (chars[k] == target[j]) {
                    k++;
                } else {
                    break;
                }
                if (j == target.length - 1) {
                    flag = true;
                }

            }
            if (flag) {
                return k - target.length;
            }
        }
        return -1;
    }

    @Override
    public StringInterface subString(int start, int end) {
        if (start < 0) {
            start = 0;
        }
        if (end > chars.length) {
            end = chars.length;
        }
        char[] newChar = new char[end - start];
        int index = 0;
        for (int i = start; i < end; i++) {
            newChar[index++] = chars[i];
        }
        ArrayString str = new ArrayString();
        str.chars = newChar;
        return str;
    }

    @Override
    public StringInterface reverse() {
        for (int i = 0; i < chars.length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length-1-i];
            chars[chars.length-1-i] = temp;
        }

        return this;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (char aChar : chars) {
            s.append(aChar);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        System.out.println(ArrayString.valueOf(-12543));
        System.out.println(ArrayString.valueOf(-12543).indexOf("".toCharArray()));
        System.out.println(ArrayString.valueOf(-12543).subString(3, 7));
        System.out.println(ArrayString.valueOf(-12543).reverse());
        System.out.println();
    }
}
