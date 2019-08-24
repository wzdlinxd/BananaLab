package homework.seven.session4;

import org.junit.Assert;
import org.junit.Test;

public class LinkedStringTest {
  @Test
  public void length() {
    char[] chars = {'1', '2'};
    LinkedString linkedString = LinkedString.valueOf(chars);
    Assert.assertEquals(2, linkedString.length());
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void charAt() {
    char[] chars = {'1', '2'};
    LinkedString linkedString = LinkedString.valueOf(chars);
    Assert.assertEquals('2', linkedString.charAt(1));
    linkedString.charAt(-1);
    linkedString.charAt(3);
  }

  @Test
  public void indexOf() {
    char[] chars = {'1', '2', '3', '4', '5'};
    char[] subChars = {'3', '4', '5'};
    char[] subChars1 = {'6', '4', '5'};
    char[] subChars2 = {'3', '4', '7'};

    LinkedString linkedString = LinkedString.valueOf(chars);
    Assert.assertEquals(2, linkedString.indexOf(subChars));
    Assert.assertEquals(-1, linkedString.indexOf(subChars1));
    Assert.assertEquals(-1, linkedString.indexOf(subChars2));
  }

  @Test
  public void subString() {
    char[] chars = {'1', '2', '3', '4', '5'};
    LinkedString linkedString = LinkedString.valueOf(chars);
    StringInterface subString = linkedString.subString(1, 3);
    StringInterface compare = LinkedString.valueOf(23);
    Assert.assertEquals(compare, subString);
  }

  @Test
  public void reverse() {
    char[] chars = {'1', '2'};
    char[] reverse = {'2', '1'};
    LinkedString linkedString = LinkedString.valueOf(chars);
    Assert.assertEquals(LinkedString.valueOf(reverse), linkedString.reverse());
  }

  @Test
  public void valueOf() {
    StringInterface stringInterface = LinkedString.valueOf(12345);
    char[] chars = {'1', '2', '3', '4', '5'};
    StringInterface arrString = LinkedString.valueOf(chars);
    Assert.assertEquals(arrString, stringInterface);

    StringInterface stringInterface1 = LinkedString.valueOf(-12345);
    char[] chars1 = {'-', '1', '2', '3', '4', '5'};
    StringInterface arrString1 = LinkedString.valueOf(chars1);
    Assert.assertEquals(arrString1, stringInterface1);
  }
}
