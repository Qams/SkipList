import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentSkipListMap;
public class Main {
	
	public static SkipList<Integer, Integer> skipList = new SkipList<>();
	public static ConcurrentSkipListMap<Integer, Integer> conSkipList = new ConcurrentSkipListMap<>();
	public static ArrayList<Integer> keys = new ArrayList<>();
	public static ArrayList<Integer> values = new ArrayList<>();
	public static final int howMany = 100000;
	
	public static void fillKeysAndValues()
	{
		for(int i=0;i<howMany;i++)
		{
			keys.add(i);
			values.add(i);
		}
		Collections.shuffle(keys);
		Collections.shuffle(values);
	}
	
	public static SkipListTestInfo putTest()
	{
		long start = System.currentTimeMillis();
		for(int i=0;i<howMany;i++)
		{
			skipList.put(keys.get(i), values.get(i));
		}
		long stop = System.currentTimeMillis();
		long start2 = System.currentTimeMillis();
		for(int i=0;i<howMany;i++)
		{
			conSkipList.put(keys.get(i), values.get(i));
		}
		long stop2 = System.currentTimeMillis();
		return new SkipListTestInfo("put", stop-start, stop2-start2);
	}
	
	public static SkipListTestInfo containsKeyTest()
	{
		long start = System.currentTimeMillis();
		for(int i=0;i<howMany;i++)
		{
			skipList.containsKey(keys.get(i));
		}
		long stop = System.currentTimeMillis();
		long start2 = System.currentTimeMillis();
		for(int i=0;i<howMany;i++)
		{
			conSkipList.containsKey(keys.get(i));
		}
		long stop2 = System.currentTimeMillis();
		return new SkipListTestInfo("contaisKey", stop-start, stop2-start2);
	}
	
	public static SkipListTestInfo getTest()
	{
		long start = System.currentTimeMillis();
		for(int i=0;i<howMany;i++)
		{
			skipList.get(keys.get(i));
		}
		long stop = System.currentTimeMillis();
		long start2 = System.currentTimeMillis();
		for(int i=0;i<howMany;i++)
		{
			conSkipList.get(keys.get(i));
		}
		long stop2 = System.currentTimeMillis();
		return new SkipListTestInfo("get", stop-start, stop2-start2);
	}
	
	public static SkipListTestInfo removeTest()
	{
		long start = System.currentTimeMillis();
		for(int i=0;i<howMany;i++)
		{
			skipList.remove(keys.get(i));
		}
		long stop = System.currentTimeMillis();
		long start2 = System.currentTimeMillis();
		for(int i=0;i<howMany;i++)
		{
			conSkipList.remove(keys.get(i));
		}
		long stop2 = System.currentTimeMillis();
		return new SkipListTestInfo("remove", stop-start, stop2-start2);
	}
	
	public static void main(String[] args) {
		
		fillKeysAndValues();
		System.out.println(putTest());
		System.out.println(containsKeyTest());
		System.out.println(getTest());
		System.out.println(removeTest());
		
	}
}
