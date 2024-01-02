package Q891011;

public class Q8 {

	public static void main(String[] args) {
		//// ADD BINARY TREE /////////
	}
	public static <T> boolean isOfTheSameStructure(BinaryTree<Integer> bt1, BinaryTree<Integer> bt2)
	{
		if(Boolean.logicalAnd(bt1 == null, bt2 == null))
		{
			return true;
		}
		else if(Boolean.logicalAnd(bt1 != null, bt2 == null))
		{
			return false;
		}
		else if(Boolean.logicalAnd(bt1 == null, bt2 != null))
		{
			return false;
		}
		else
		{
			return isOfTheSameStructure(bt1.getLeft(),bt2.getLeft()) && isOfTheSameStructure(bt1.getRight(),bt2.getRight());
		}
	}


}
