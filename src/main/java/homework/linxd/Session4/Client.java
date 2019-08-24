package homework.linxd.Session4;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @author linxd
 * @date 2019/8/11 14:57
 * Description: No Description
 */
public class Client {
    public static void main(String[] args) {
        System.out.println(((ArrayString) ArrayString.valueOf(-12543)).chars);
        System.out.println(ArrayString.valueOf(-12543).indexOf("".toCharArray()));
        System.out.println(((ArrayString) ArrayString.valueOf(-12543).subString(3, 7)).chars);
        System.out.println(((ArrayString) ArrayString.valueOf(-12543).reverse()).chars);
        System.out.println(((ArrayString) (new ArrayString().reverse())).chars);
        System.out.println();
    }
}
