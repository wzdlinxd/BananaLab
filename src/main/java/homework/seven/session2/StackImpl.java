package homework.seven.session2;

import org.junit.Test;

/**
 * 栈实现
 *
 * @author Seven-Steven
 * @date 2019-07-17 21:25
 */
public class StackImpl implements Stack {
  /**
   * 栈内元素
   */
  private int[] elements = new int[0];

  @Test
  public void test() {
    int[] array = new int[0];
    System.out.println(array[0]);
  }

  /**
   * 对整型数组进行截取操作, 截取区间前闭后开
   *
   * @param array 截取前的数组
   * @param start 截取起始位置, 包含起始位置对应元素
   * @param end   截取终止位置, 不包含终止位置对应元素
   * @return 截取后的新数组
   */
  private int[] sliceArray(int[] array, int start, int end) {
    if (null == array) {
      return new int[0];
    }

    if (start < 0 || end > array.length || start > end) {
      throw new IndexOutOfBoundsException();
    }

    int[] result = new int[end - start];
    for (int i = start, j = 0; i < end; i++, j++) {
      result[j] = array[i];
    }

    return result;
  }

  /**
   * 在转入 array 的 index 位置之前插入 element
   * @param array 插入元素之前的数组
   * @param element 待插入数组的元素
   * @param index 元素插入的位置
   * @return 在指定位置插入元素之后的新数组
   */
  private int[] insert2Array(int[] array, int element, int index) {
    if (null == array) {
      return new int[0];
    }

    if (index > array.length) {
      throw new IndexOutOfBoundsException();
    }

    int[] result = new int[array.length + 1];
    int currentElement;
    for (int i = 0, j = 0; i < result.length; i++, j++) {
      if (i != index) {
        currentElement = array[j];
      } else {
        currentElement = element;
        j--;
      }

      result[i] = currentElement;
    }

    return result;
  }

  @Override
  public int push() {
    return 0;
  }

  @Override
  public int pop() {
    if (this.elements.length == 0) {
      throw new RuntimeException("this stack is empty!");
    }

    int result = this.elements[this.elements.length - 1];
    this.elements = sliceArray(this.elements, 0, this.elements.length - 1);
    return result;
  }

  @Override
  public int peak() {
    if (this.elements.length == 0) {
      throw new RuntimeException("this stack is empty!");
    }
    
    return this.elements[this.elements.length - 1];
  }

  @Override
  public int size() {
    return this.elements.length;
  }

  @Override
  public boolean isEmpty() {
    return this.size() != 0;
  }
}
