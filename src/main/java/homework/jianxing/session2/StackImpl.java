package homework.jianxing.session2;

import homework.jianxing.utils.StringUtils;

/**
 * Simple Stack implementation. Not thread safe, not support capacity auto-increment.
 *
 * @author logow.whl
 */
public class StackImpl implements Stack {

    private static final int DEFAULT_CAPACITY_SIZE = 64;

    private int[] elements;
    private int size;
    private int index;

    public StackImpl() {
        this(DEFAULT_CAPACITY_SIZE);
    }

    public StackImpl(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must greater than 0");
        }

        elements = new int[capacity];
        size = 0;
        index = -1;
    }

    @Override
    public int push(int e) {
        if (index >= elements.length - 1) {
            throw new IllegalStateException("stack overflow");
        }

        elements[++index] = e;
        size++;

        return size;
    }

    @Override
    public int pop() {
        if (index < 0) {
            throw new IllegalStateException("stack underflow");
        }

        int e = elements[index--];
        size--;

        return e;
    }

    @Override
    public int peak() {
        if (index < 0) {
            throw new IllegalStateException("stack is empty");
        }
        return elements[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return StringUtils.toString(elements, 0, size);
    }
}
