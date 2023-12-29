package csu22011_a1;
// -------------------------------------------------------------------------
/**
 * This class contains only two static methods that search for points on the
 * same line in three arrays of integers.
 *
 * @author
 * @version 03/10/22 22:22:52
 */
class Collinear {

	public static int Y1 = 1;
	public static int Y2 = 2;
	public static int Y3 = 3;

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in
	 * arrays a1, a2, a3. This method is static, thus it can be called as
	 * Collinear.countCollinear(a1,a2,a3)
	 * 
	 * @param a1:
	 *            An UNSORTED array of integers. Each integer a1[i] represents
	 *            the point (a1[i], 1) on the plain.
	 * @param a2:
	 *            An UNSORTED array of integers. Each integer a2[i] represents
	 *            the point (a2[i], 2) on the plain.
	 * @param a3:
	 *            An UNSORTED array of integers. Each integer a3[i] represents
	 *            the point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a
	 *         horizontal line.
	 *
	 *         Array a1, a2 and a3 contain points on the horizontal line y=1,
	 *         y=2 and y=3, respectively. A non-horizontal line will have to
	 *         cross all three of these lines. Thus we are looking for 3 points,
	 *         each in a1, a2, a3 which lie on the same line.
	 *
	 *         Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e.,
	 *         they are on the same line) if
	 * 
	 *         x1(y2-y3)+x2(y3-y1)+x3(y1-y2)=0
	 *
	 *         In our case y1=1, y2=2, y3=3.
	 *
	 *         You should implement this using a BRUTE FORCE approach (check all
	 *         possible combinations of numbers from a1, a2, a3) which should run
	 *         in the worst case in O(N^3).
	 *
	 */
	static int countCollinear(int[] a1, int[] a2, int[] a3) {
		int count = 0;
		for (int i = 0; i < a3.length; i++)
		{
			for (int j = 0; j< a2.length; j++)
			{
				for (int k = 0; k< a1.length; k++)
				{
					if ( a1[k]*(Y2-Y3) + a2[j]*(Y3-Y1) + a3[i]*(Y1-Y2) == 0 )
					{
						count++;
					}
				}
			}
		}

		return count;
	}

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in
	 * arrays a1, a2, a3. This method is static, thus it can be called as
	 * Collinear.countCollinearFast(a1,a2,a3)
	 * 
	 * @param a1:
	 *            An UNSORTED array of integers. Each integer a1[i] represents
	 *            the point (a1[i], 1) on the plain.
	 * @param a2:
	 *            An UNSORTED array of integers. Each integer a2[i] represents
	 *            the point (a2[i], 2) on the plain.
	 * @param a3:
	 *            An UNSORTED array of integers. Each integer a3[i] represents
	 *            the point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a
	 *         horizontal line.
	 *
	 *         In this implementation you should make non-trivial use of
	 *         InsertionSort and Binary Search. This method should run in the
	 *         worst case in O(N^2 lg(N)).
	 *
	 */
	static int countCollinearFast(int[] a1, int[] a2, int[] a3) {
		sort(a3);
		int countResult = 0;		
		for (int i = 0; i < a1.length; i++)
		{
			for (int j = 0; j < a2.length; j++)
			{
				int a3Result = -(a1[i]*(Y2-Y3) + a2[j]*(Y3-Y1))/(Y1-Y2);
				if (binarySearch( a3, a3Result ))
					countResult++;
			}
		}
		return countResult;
	}

	// ----------------------------------------------------------
	/**
	 * Sorts an array of integers according to InsertionSort. This method is
	 * static, thus it can be called as Collinear.sort(a)
	 * 
	 * @param a:
	 *            An UNSORTED array of integers.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 *
	 * This method runs in the worst case in Theta(N^2) time.
	 */
	static void sort(int[] a) {
		for(int i = 0; i < a.length; i++){
			int j = i-1;
			while(j >= 0 && a[j]>a[j+1]){
				int temp = a[j];
				a[j] = a[j+1];
				a[j+1] = temp;
				j--;
			}				
		}
	}

	// ----------------------------------------------------------
	/**
	 * Searches for an integer inside an array of integers. This method is
	 * static, thus it can be called as Collinear.binarySearch(a,x)
	 * 
	 * @param a:
	 *            A array of integers SORTED in ascending order.
	 * @param x:
	 *            An integer.
	 * @return true if 'x' is contained in 'a'; false otherwise.
	 * 
	 * This method runs in the worst case in Theta(lg(N)) time.
	 *
	 */
	static boolean binarySearch(int[] a, int x) {
		int lo = 0, hi = a.length-1;
		while (lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if (x < a[mid]) hi = mid - 1;
			else if (x > a[mid]) lo = mid + 1;
			else return true;
		}
		return false;
	}




	public static int[] getArrayFromFile(String filename) {
		In in = new In(filename);
		int[] inputList = in.readAllInts();
		return inputList;
	}
	//class in (line 439)

	public static void main(String[] args) {
		int[] sizes = {1000, 2000, 4000, 5000};

		StdOut.println("Performance Testing -> countCollinear:");
		for (int n : sizes) {
			int[] a = getArrayFromFile("r0" + n + "-1.txt");
			int[] b = getArrayFromFile("r0" + n + "-2.txt");
			int[] c = getArrayFromFile("r0" + n + "-3.txt");

			Stopwatch stpWh1 = new Stopwatch();
			Collinear.countCollinear(a, b, c);
			double time1 = stpWh1.elapsedTime();

			StdOut.println("collinearLines for "+n+ " inputs"+" = " +Collinear.countCollinear(a, b, c));
			StdOut.printf("Elapsed runtime = %.2f seconds%n", time1);
		}

		StdOut.println("\nPerformance Testing -> countCollinearFast:");
		for (int n : sizes) {
			int[] a = getArrayFromFile("r0" + n + "-1.txt");
			int[] b = getArrayFromFile("r0" + n + "-2.txt");
			int[] c = getArrayFromFile("r0" + n + "-3.txt");

			Stopwatch stpWh2 = new Stopwatch();
			Collinear.countCollinearFast(a, b, c);
			double time2 = stpWh2.elapsedTime();
			StdOut.println("collinearLines for "+n+ " inputs"+" = " +Collinear.countCollinearFast(a, b, c));
			StdOut.printf("Elapsed runtime = %.2f seconds%n", time2);
		}
	}
}