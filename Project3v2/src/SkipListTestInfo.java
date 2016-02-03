public class SkipListTestInfo {
	
	private String methodName;
	private long time;
	private long time2;
	
	public SkipListTestInfo(String methodName, long time, long time2)
	{
		this.methodName = methodName;
		this.time = time;
		this.time2 = time2;
	}
	
	public String toString()
	{
		return "Method " + methodName + " time: " + time + " ms;" + " ConcurrentSkipListMap " + methodName + " time: " + time2;
	}
	

}
