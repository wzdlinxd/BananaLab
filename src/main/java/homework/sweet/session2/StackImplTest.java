package homework.sweet.session2;

public class StackImplTest {
    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.size());
        System.out.println(stack.pop());
        for (int i = 0; i < stack.size(); i++) {
            System.out.println(stack.toString());
        }
    }
}
