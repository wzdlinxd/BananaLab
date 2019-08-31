package homework.linxd.Session5;

/**
 * 去重统计的 BitMap
 * @param
 */
public class BananaBitMap implements CountingInterface<Integer> {

    private byte[] bits;

    private int capacity;

    public BananaBitMap(int capacity) {
        this.capacity = capacity;
        bits = new byte[getIndex(this.capacity) + 1];
    }

    /**
     * 获取所在数组元素下标
     * @param num
     * @return
     */
    private int getIndex(int num) {
        return num >> 3;
    }

    /**
     * 获取所在数组元素中的偏移量
     * @param num
     * @return
     */
    private int getPosition(Integer num){
        return num & 7;
    }

	@Override
	public void add(Integer key) {
        bits[getIndex(key)] |= 1 << getPosition(key);
	}

	@Override
	public int size() {
        int count = 0;
        int size = bits.length;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j <= 8; j++) {
                int k = bits[i] & 1 << j - 1;
                count += k != 0 ? 1 : 0;
            }
        }
		return count;
	}

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean contains(int key) {
        return (bits[getIndex(key)] & 1 << getPosition(key)) != 0;
    }

    public static void main(String[] args) {
        BananaBitMap bitMap = new BananaBitMap(9);
        bitMap.add(1);
        bitMap.add(2);
        bitMap.add(3);
        bitMap.add(4);
        bitMap.add(5);
        bitMap.add(6);
        System.out.println(bitMap.size());
        bitMap.add(3);
        System.out.println(bitMap.size());
    }
}
