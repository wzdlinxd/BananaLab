package homework.seven.session5;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Seven-Steven
 * @date 2019-09-05 23:59
 */
public class BananaHashMapTest {
  @Test
  public void testAll() {
    // Test put
    BananaHashMap<String, String> map = new BananaHashMap<>(3);
    map.put("test", "test");
    map.put("tes", "test");
    map.put("test", "12");
    map.put("test", "1");
    map.put("1", "2");
    map.put("1", "3");
    map.put("test", "test");
    map.put(null, null);
    // test get
    Assert.assertEquals(map.get("test"), "test");
    Assert.assertEquals(map.get("tes"), "test");
    Assert.assertEquals(map.get("1"), "3");
    Assert.assertNull(map.get(null));
    Assert.assertNull(map.get("false"));
    // test size
    Assert.assertEquals(map.size(), 4);
    // test containsKey
    Assert.assertFalse(map.containsKey("false"));
    Assert.assertTrue(map.containsKey(null));
    Assert.assertTrue(map.containsKey("test"));
    Assert.assertTrue(map.containsKey("tes"));
    Assert.assertTrue(map.containsKey("1"));

  }
}
