package homework.seven.Session4;

import org.junit.Assert;
import org.junit.Test;

public class ArrayStringTest {
  @Test
  public void length() {
    char[] chars = {'1', '2'};
    ArrayString arrayString = new ArrayString(chars);
    Assert.assertEquals(2, arrayString.length());
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void charAt() {
    char[] chars = {'1', '2'};
    ArrayString arrayString = new ArrayString(chars);
    Assert.assertEquals('2', arrayString.charAt(1));
    arrayString.charAt(-1);
    arrayString.charAt(3);
  }

  @Test
  public void indexOf() {
    char[] chars = {'1', '2', '3', '4', '5'};
    char[] subChars = {'3', '4', '5'};
    char[] subChars1 = {'6', '4', '5'};
    char[] subChars2 = {'3', '4', '7'};
    ArrayString arrayString = new ArrayString(chars);
    Assert.assertEquals(2, arrayString.indexOf(subChars));
    Assert.assertEquals(-1, arrayString.indexOf(subChars1));
    Assert.assertEquals(-1, arrayString.indexOf(subChars2));
  }

  @Test
  public void subString() {
    char[] chars = {'1', '2', '3', '4', '5'};
    ArrayString arrayString = new ArrayString(chars);
    StringInterface subString = arrayString.subString(1, 3);
    StringInterface compare = ArrayString.valueOf(23);
    Assert.assertEquals(compare, subString);
  }

  @Test
  public void reverse() {
    char[] chars = {'1', '2'};
    char[] reverse = {'2', '1'};
    ArrayString arrayString = new ArrayString(chars);
    Assert.assertEquals(new ArrayString(reverse), arrayString.reverse());
  }

  @Test
  public void valueOf() {
    StringInterface stringInterface = ArrayString.valueOf(12345);
    char[] chars = {'1', '2', '3', '4', '5'};
    StringInterface arrString = new ArrayString(chars);
    Assert.assertEquals(arrString, stringInterface);

    StringInterface stringInterface1 = ArrayString.valueOf(-12345);
    char[] chars1 = {'-', '1', '2', '3', '4', '5'};
    StringInterface arrString1 = new ArrayString(chars1);
    Assert.assertEquals(arrString1, stringInterface1);
  }
}
