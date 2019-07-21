package homework.zhou.session2;

import com.bigbanana.lab.Session2.Stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class StackImpl implements Stack {

    int [] stack ;
    int top;

    public StackImpl(int size) {
        stack = new int[size];
        top = 0;
    }

    @Override
    public boolean push(int value) {
        //扩容
        if (top ==  stack.length){
            int[] temp = Arrays.copyOf(stack,2*stack.length);
            stack = temp;
        }
        stack[top++] = value;
        return false;
    }

    @Override
    public int pop() {
        if (top==0){
            throw new EmptyStackException();
        }
        else{
            return stack[--top];
        }
       // return 0;
    }

    @Override
    public int peak() {
        if (top == 0){
            throw new EmptyStackException();
        }
        return  stack[top-1];
        //return 0;
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public boolean isEmpty() {
        return top ==0 ;
    }
}
