package homework.sweet.session2;

import java.util.Arrays;

public class StackImpl implements Stack{
    private static final int DEFAULT_CAPACIT = 10;
    protected int[] elementData;
    protected int elementCount;
    protected int modCount;

    public StackImpl() {
        this(DEFAULT_CAPACIT);
    }

    public StackImpl(int initialCapacity) {
        this.elementData = new int[initialCapacity];
    }

    @Override
    public boolean push(int value) {
        growCapacity(elementCount + 1);
        elementData[elementCount++] = value;
        return true;
    }

    @Override
    public int pop() {
        int length = elementCount;
        int obj = peak();
        removeElementAt(length -1);
        return obj;
    }

    @Override
    public int peak() {
        return 0;
    }

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public void growCapacity(int minCapacity){
        int oldCapacity = elementData.length;
        int newcapacity = 0;
        newcapacity = DEFAULT_CAPACIT << 1;
        if (newcapacity < oldCapacity) {
            newcapacity = oldCapacity;
        }

        elementData = Arrays.copyOf(elementData,newcapacity);
    }

    public void removeElementAt(int index){
        if (index > elementCount){
            throw new ArrayIndexOutOfBoundsException(index + " >= " + elementCount);
        } else if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        elementCount--;
        elementData[elementCount] = 0;
//        int[] test = new int[10];

//        System.arraycopy(elementData,0,test,0,elementCount - 1);
//        System.out.println("heheh");
    };
}
