package com.viki.java.util.HashMap;


public class HashMapTest {
	
	/**
	 * 1��HashMap�ڲ���Ա��
	 * 	a) int threshold:
	 * 		������(yu)ֵ����table��entryԪ�ص��ܸ���size����thresholdֵ��������ӵ�Ԫ�ض�Ӧ��Ͱ���ǿա�ʱ����ᷢ�����ݣ�
	 * 		threshold = size * loadFactor������ֵ
	 * 
	 * 	b) transient Entry<K,V>[] table:
	 * 		һ�����ڴ洢HashMap�е�EntryԪ�ص�������
	 * 
	 * 	c) final float loadFactor:
	 * 		�������ӣ������趨hash��С�ڴﵽ�������Ķ��ٰٷֱ�ʱ���������ݲ���,�����ڴ�����hashMapʱ�趨
	 * 
	 * 	d) static final float DEFAULT_LOAD_FACTOR = 0.75f;
	 * 		Ĭ�ϼ�������Ϊ0.75
	 * 
	 * 	e) static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; 
	 * 		Ĭ�ϳ�ʼ������Ϊ16�������ڴ�����hashMapʱ�趨����ӦΪtable����ĳ��ȣ�
	 * 		����Ҫע����ǣ�table�������ڴ���hashMapʱ������ֻ���ڡ�put���ݡ�ʱ�Ŵ������Ҹ�table�ĳ��Ȳ���һ������ָ����Ĭ��������
	 * 		���ǻ�õ�һ�����ָ�����������������2��N�η�����ֵ��
	 * 
	 * 	f) transient int size:
	 * 		sizeָtable�����е�entry���ܸ�������������table.length.��Ϊtable��ÿһ��Ԫ���൱��һ��Ͱ��
	 * 		ÿһ��Ͱ������һ�����˹���ޣ�������ṹ��ÿһ��Entry�����п��ܻẬ�ж��EntryԪ�ء�
	 * 		��������һ�����ݲ��洢ʱ�����ָ����ݶ�Ӧ��table�±����ڵ�entryԪ���Ѿ���ֵ������Ϊ��ʱ�����ˡ�ײͰ�¼�������ʱ�����Ԫ����ӵ��������ǰ�ˣ�ԭ��Ԫ�ر������ڸ�Ԫ�صĺ�ˡ�
	 * 	
	 * **/
	
	/**
	 * 2.1������һ���µ�HashMap����
	 * 	���¿��Կ������ڴ���HashMap����ʱ����û�г�ʼ��table���ԡ�����loadFactor�ڴ���hashMapʱָ����
	 * 	init()����Ϊ�գ������Ҫ����һ���̳�hashMap���࣬���Ը���init()������ʵ���Զ���ĳ�ʼ������
	 * **/
/*    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);

        this.loadFactor = loadFactor;
        threshold = initialCapacity;
        init();
    }*/
	
	
	/**
	 * 2.2������һ��Map���󹹽�HashMap����
	 * 	����������裺
	 * 		a)	��ʼ��һ���յ�hashMap����
	 * 		b)	�����table����������Ͱ�ĸ�������Ϊtable�����ڴ�ռ�
	 * 		c)	�����е�MapԪ����ӵ�hashMap��table������
	 * **/
/*    public HashMap(Map<? extends K, ? extends V> m) {
        this(Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1,
                      DEFAULT_INITIAL_CAPACITY), DEFAULT_LOAD_FACTOR);
        inflateTable(threshold);

        putAllForCreate(m);
    }
    private void inflateTable(int toSize) {
        // Find a power of 2 >= toSize
        int capacity = roundUpToPowerOf2(toSize);

        threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
        table = new Entry[capacity];
        initHashSeedAsNeeded(capacity);
    }
    private static int roundUpToPowerOf2(int number) {
        // assert number >= 0 : "number must be non-negative";
        return number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }*/
	
	/**
	 * 3.1����hashMap��table�����Ԫ��
	 * 	�����ж�table�ǲ��ǿյģ�����ǣ��������ݣ�
	 * 	���Ԫ�ص�keyΪnull����hashMap��Ѹ�Ԫ�ط��ڵ�һ��Ͱ���档����Ѿ�����null key,��᷵�ظ�keyԭ�ȵ�value�����򷵻�Null.
	 * 		��Ϊnull��hashֵ��Զ��0��������һ���ᱻ�洢�ڵ�һ��Ͱ����
	 * 	����key��hashֵ������hashֵ�ҵ�table�ж�Ӧ��Ͱ��������Ͱ������Key�Ƿ��Ѿ�������table�У�����������滻��value������ԭ����value
	 * 	��������table�ĸ�Ͱ�������Ԫ��
	 * **/
/*    public V put(K key, V value) {
        if (table == EMPTY_TABLE) {
            inflateTable(threshold);
        }
        if (key == null)
            return putForNullKey(value);
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }

        modCount++;
        addEntry(hash, key, value, i);
        return null;
    }
    void addEntry(int hash, K key, V value, int bucketIndex) {
        if ((size >= threshold) && (null != table[bucketIndex])) {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }
    void createEntry(int hash, K key, V value, int bucketIndex) {
        Entry<K,V> e = table[bucketIndex];
        table[bucketIndex] = new Entry<>(hash, key, value, e);
        size++;
    }*/
	
	/**
	 * 3.2������ϸ��
	 * 	����һ��������ֵ��С��entry����
	 * 	����hashSeed���Ұ�����ת�Ƶ��µ�������
	 * 	�����µ�thresholdֵ
	 * **/
/*    void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable, initHashSeedAsNeeded(newCapacity));
        table = newTable;
        threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    }*/
	
	/**
	 * 4����HashMap�и���key��ȡEntry����value
	 * 		����key��hashֵ������hashֵ�ҵ�table�ж�Ӧ��entry����
	 * 		ѭ���������ж�key��ֵ���������ȵģ��򷵻�;���û�У��򷵻�null;
	 * **/
/*    final Entry<K,V> getEntry(Object key) {
        if (size == 0) {
            return null;
        }

        int hash = (key == null) ? 0 : hash(key);
        for (Entry<K,V> e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash &&
                ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }
		public V get(Object key) {
	        if (key == null)
	            return getForNullKey();
	        Entry<K,V> entry = getEntry(key);
	
	        return null == entry ? null : entry.getValue();
	    }*/
	
	public static void main(String[] args){
		System.out.println(roundUpToPowerOf2(16));
	}
	
	
	
    private static int roundUpToPowerOf2(int number) {
    	int MAXIMUM_CAPACITY = 1<<30;
        // assert number >= 0 : "number must be non-negative";
        return number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }
    
}
