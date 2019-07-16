package homework.jianxing.utils;

public abstract class StringUtils {

    public static String toString(int[] arr, int off, int len) {
        if (arr == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || len > arr.length - off) {
            throw new IndexOutOfBoundsException();
        }

        if (len == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                sb.append(arr[off + i]);
            } else {
                sb.append(", ").append(arr[off + i]);
            }
        }
        sb.append(']');

        return sb.toString();
    }
}
