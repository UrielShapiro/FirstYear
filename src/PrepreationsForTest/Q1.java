package PrepreationsForTest;

public class Q1 {

	public static void main(String[] args) {
		int[][] arr = Q1(5,10);
		for (int i = 0; i < arr.length; i++) 
		{
			for (int k = 0; k < arr[0].length; k++)
			{
				System.out.print(arr[i][k] + "\t");
			}
			System.out.println();
		}
	}
	public static int[][] Q1(int n, int m)
	{
		int[][] ans = new int[n][m];
		for (int i = 0; i < m; i++) {
			ans[0][i]=1;
		}
		for (int i = 0; i < n; i++) {
			ans[i][0]=1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				ans[j][i]=ans[j-1][i]+ans[j][i-1];
			}		}
		return ans;

	}
	//	 public static int[][] Q1(int n, int m)
	//	    {
	//	        int[][] ans = new int[n][m];
	//	        for(int i = 0; i < m; i++)
	//	        {
	//	            ans[0][i] = 1;
	//	        }
	//	        for(int i = 0; i < n; i++)
	//	        {
	//	            ans[i][0] = 1;
	//	        }
	//	        for(int i = 1; i < n; i++)
	//	        {
	//	            for(int j = 1; j < m; j++)
	//	            {
	//	                ans[i][j] = ans[i-1][j] + ans[i][j-1];
	//	            }
	//	        }
	//	        return ans;
	//	    }

}
