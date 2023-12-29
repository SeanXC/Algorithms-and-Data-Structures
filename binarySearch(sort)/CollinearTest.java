package csu22011_a1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 03/10/22 22:33:19
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
	//~ Constructor ........................................................
	@Test
	public void testConstructor()
	{
		new Collinear();
	}

	//~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the two methods work for empty arrays
	 */

	@Test
	public void testEmpty()
	{
		int expectedResult = 0;

		assertEquals("countCollinear with 3 empty arrays should return zero", 
				expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
		assertEquals("countCollinearFast with 3 empty arrays should return zero", 
				expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
	}

	@Test
	public void testNormalCollinear()
	{
		int expectedResult = 1;

		int []ar1 = {5};
		int []ar2 = {10};
		int []ar3 = {15};

		assertEquals("countCollinear with 3 arrays with normal inputs should return one",     
				expectedResult, Collinear.countCollinear(ar1, ar2, ar3));
		assertEquals("countCollinear with 3 arrays with normal inputs should return one", 
				expectedResult, Collinear.countCollinearFast(ar1, ar2, ar3));
	}

	@Test
	public void testDiffSizeOfAr()
	{
		int expectedResult = 1;

		int []ar1 = {10, 15};
		int []ar2 = {10};
		int []ar3 = {5, 15};

		assertEquals("countCollinear with 3 different size of arrays should return one",     
				expectedResult, Collinear.countCollinear(ar1, ar2, ar3));
		assertEquals("countCollinear with 3 different size of arrays should return one", 
				expectedResult, Collinear.countCollinearFast(ar1, ar2, ar3));
	}
	@Test
	public void testUnsort()
	{
		int expectedResult = 1;

		int []ar1 = {15, 10};
		int []ar2 = {10};
		int []ar3 = {15, 5};

		assertEquals("countCollinear with 3 different Unsort arrays should return one",     
				expectedResult, Collinear.countCollinear(ar1, ar2, ar3));
		assertEquals("countCollinear with 3 different Unsort arrays should return one", 
				expectedResult, Collinear.countCollinearFast(ar1, ar2, ar3));
	}

	@Test
	public void testNegative()
	{
		int expectedResult = 1;

		int []ar1 = {10, -15};
		int []ar2 = {-10};
		int []ar3 = {-5, 15};

		assertEquals("countCollinear with negative arrays should return one",     
				expectedResult, Collinear.countCollinear(ar1, ar2, ar3));
		assertEquals("countCollinear with negative arrays should return one", 
				expectedResult, Collinear.countCollinearFast(ar1, ar2, ar3));
	}

	@Test
	public void testNoCollinear()
	{
		int expectedResult = 0;

		int []ar1 = {1};
		int []ar2 = {5};
		int []ar3 = {8};

		assertEquals("countCollinear with noCollinear arrays should return zero",     
				expectedResult, Collinear.countCollinear(ar1, ar2, ar3));
		assertEquals("countCollinear with noCollinear arrays should return zero", 
				expectedResult, Collinear.countCollinearFast(ar1, ar2, ar3));
	}
}
