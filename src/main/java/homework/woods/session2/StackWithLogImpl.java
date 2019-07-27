package homework.woods.session2;

import homework.woods.exception.BizServiceException;
import homework.woods.utils.WoodsPrinter;

import java.util.Arrays;

/**
 * @author woods
 */
public class StackWithLogImpl implements Stack{

    private final static int ORIGINAL_LENGTH = 2;
    private int[] stackArray;
    private int size;

    public StackWithLogImpl(){
        stackArray = new int[ORIGINAL_LENGTH];
        size = 0;
    }

    @Override
    public boolean push(int value) {
        WoodsPrinter.println("start push");
        // 栈是否已满，满则扩容
        if (size >= stackArray.length)
        {
            stackArray = Arrays.copyOf(stackArray, stackArray.length + ORIGINAL_LENGTH);
        }
        stackArray[size] = value;
        size ++;
        WoodsPrinter.println("end push");
        return true;
    }

    @Override
    public int pop() {
        if (size <= 0)
        {
            throw new BizServiceException("空栈！");
        }
        WoodsPrinter.println("start pop");
        size --;
        int value = stackArray[size];
        // 栈空间过度浪费，适度缩容
        if (size < stackArray.length - ORIGINAL_LENGTH - ORIGINAL_LENGTH)
        {
            stackArray = Arrays.copyOf(stackArray, stackArray.length - ORIGINAL_LENGTH);
        }
        WoodsPrinter.println("end pop");
        return value;
    }

    @Override
    public int peak() {
        if (size <= 0)
        {
            throw new BizServiceException("空栈！");
        }
        WoodsPrinter.println("start peak");
        WoodsPrinter.println("end peak");
        return stackArray[size-1];
    }

    @Override
    public int size() {
        return size;
    }

    public int maxSize(){
        return stackArray.length;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0)
        {
            return true;
        }else {
            return false;
        }
    }
}
