package com.viki.java.util.HashMap;


public class HashMapTest {
	
	/**
	 * 1、HashMap内部成员：
	 * 	a) int threshold:
	 * 		扩容阈(yu)值，当table中entry元素的总个数size超过threshold值并且新添加的元素对应的桶【非空】时，则会发生扩容；
	 * 		threshold = size * loadFactor的整数值
	 * 
	 * 	b) transient Entry<K,V>[] table:
	 * 		一个用于存储HashMap中的Entry元素的容器；
	 * 
	 * 	c) final float loadFactor:
	 * 		加载因子，用于设定hash大小在达到总容量的多少百分比时，触发扩容操作,可以在创建该hashMap时设定
	 * 
	 * 	d) static final float DEFAULT_LOAD_FACTOR = 0.75f;
	 * 		默认加载因子为0.75
	 * 
	 * 	e) static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; 
	 * 		默认初始化容量为16，可以在创建该hashMap时设定，对应为table数组的长度，
	 * 		但需要注意的是，table并不会在创建hashMap时创建，只有在【put数据】时才创建并且该table的长度并不一定等于指定的默认容量，
	 * 		而是会得到一个与该指定容易最相近的满足2的N次方的数值。
	 * 
	 * 	f) transient int size:
	 * 		size指table中所有的entry的总个数，并不等于table.length.因为table的每一个元素相当于一个桶，
	 * 		每一个桶类似于一组俄罗斯套娃，呈链表结构，每一个Entry链表中可能会含有多个Entry元素。
	 * 		如果在添加一个数据并存储时，发现该数据对应的table下标所在的entry元素已经有值，则认为此时发生了【撞桶事件】，此时会把新元素添加到链表的最前端，原有元素被链接在该元素的后端。
	 * 	
	 * **/
	
	/**
	 * 2.1、创建一个新的HashMap对象
	 * 	从下可以看出，在创建HashMap对象时，并没有初始化table属性。并且loadFactor在创建hashMap时指定；
	 * 	init()方法为空，如果需要创建一个继承hashMap的类，可以覆盖init()方法，实现自定义的初始化操作
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
	 * 2.2、基于一个Map对象构建HashMap对象
	 * 	具体操作步骤：
	 * 		a)	初始化一个空的hashMap对象
	 * 		b)	计算出table的容量，即桶的个数，并为table分配内存空间
	 * 		c)	把所有的Map元素添加到hashMap的table数组中
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
	 * 3.1、往hashMap的table中添加元素
	 * 	首先判断table是不是空的，如果是，则先扩容；
	 * 	如果元素的key为null，则hashMap会把该元素放在第一个桶里面。如果已经存在null key,则会返回该key原先的value，否则返回Null.
	 * 		因为null的hash值永远是0，所以它一定会被存储在第一个桶里面
	 * 	计算key的hash值，根据hash值找到table中对应的桶，遍历该桶，看该Key是否已经存在于table中，如果存在则替换掉value并返回原来的value
	 * 	否则往该table的该桶中添加新元素
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
	 * 3.2、扩容细节
	 * 	创建一个新容量值大小的entry数组
	 * 	更新hashSeed并且把数据转移到新的数组中
	 * 	更新新的threshold值
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
	 * 4、从HashMap中根据key获取Entry或者value
	 * 		计算key的hash值，根据hash值找到table中对应的entry链表
	 * 		循环该链表，判断key的值，如果有相等的，则返回;如果没有，则返回null;
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
