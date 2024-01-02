package Q891011;

public class TirgulQuestions {
	public static <T> int kod(BinaryTree<T> bt)
	{
		if(bt == null)
		{
			return 0;
		}
		return kod(bt.getLeft())+kod(bt.getRight())+1;
	}
	
	public static <T> int isLeaf(BinaryTree<T> bt)
	{
		if(bt == null)
		{
			return 0;
		}
		if(bt.isLeaf())
		{
			return 1;
		}
		return kod(bt.getLeft())+kod(bt.getRight());
	}
}
