package Q891011;

public class Q9Again {
	public static <T> int Q9(BinaryTree<T> bt1, int min, int max)
	{
		if(min>max)
		{
			return 0;
		}
		if(bt1 == null)
		{
			return 0;
		}
		if(bt1.isLeaf())
		{
			return 1;
		}
		return Q9Helper(bt1,min,max,0);
	}
	public static <T> int Q9Helper(BinaryTree<T> bt1, int min, int max,int i)
	{
		if(i<max && i>=min)
		{
			if(bt1.isLeaf())
			{
				return 1;
			}
		}
		return Q9Helper(bt1.getLeft(),min,max,i++) + Q9Helper(bt1.getRight(),min,max,i++);
	}

}
