import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Merlin
 *  @version HT 2020
 */


/*
 * 
 * These are my results from running the experiments 3 times and than dividing the answer by 3 in miliseconds.
 * +---------------------+----------------------+---------------------+----------------+-------------------------+
| Number Type         | Insertion            | Selection           | Quick          | Merge                   |
|                     |                      |                     |                |                         |
+---------------------+----------------------+---------------------+----------------+-------------------------+
| 1000 random         | 10 + 12 + 14 = 13. 6 |      4 + 10 + 7 = 7 | 0 + 1 + 0 = .3 |         2 + 2 + 4 = 2.6 |
+---------------------+----------------------+---------------------+----------------+-------------------------+
| 1000 few unique     | 0 + 1 + 1 = 0.6      | 19 + 8 + 6 = 11     | 0 + 1 + 0 = .3 | 1 + 1 + 0 = 0.6         |
+---------------------+----------------------+---------------------+----------------+-------------------------+
| 1000 nearly ordered | 0 + 0 + 0 = 0        | 0 + 0 + 0 = 0       | 0 + 0 + 0 = 0  | 12 + 7+ 15 = 11.33      |
+---------------------+----------------------+---------------------+----------------+-------------------------+
| 1000 reverse order  | 1 + 1 + 1 = 1        | 0 + 0 + 0 = 0       | 0 + 0 + 0 = 0  | 11 + 6 + 13 = 10        |
+---------------------+----------------------+---------------------+----------------+-------------------------+
| 1000 sorted         | 0 + 0 + 0 = 0        | 0 + 0 + 0 = 0       | 0 + 0 + 0 = 0  | 13 + 6 + 7 = 8.67       |
+---------------------+----------------------+---------------------+----------------+-------------------------+
| 10000 random        | 85 + 87  + 73 = 81.6 | 31 + 33 + 31 = 31.6 | 1 + 1 + 1 =1   | 42 + 32 + 33 =    35.67 |
+---------------------+----------------------+---------------------+----------------+-------------------------+

This is the table with just the averages of the results in ms.
+---------------------+-----------+-----------+-------+-------+
| Number Type         | Insertion | Selection | Quick | Merge |
|                     |           |           |       |       |
+---------------------+-----------+-----------+-------+-------+
| 1000 random         | 13. 6     |         7 |    .3 |   2.6 |
+---------------------+-----------+-----------+-------+-------+
| 1000 few unique     |  0.6      |  11       |  0.3  | 0.6   |
+---------------------+-----------+-----------+-------+-------+
| 1000 nearly ordered | 0         | 0         | 0     | 11.33 |
+---------------------+-----------+-----------+-------+-------+
| 1000 reverse order  |  1        | 0         | 0     | 10    |
+---------------------+-----------+-----------+-------+-------+
| 1000 sorted         |  0        | 0         | 0     | 8.67  |
+---------------------+-----------+-----------+-------+-------+
| 10000 random        | 81.6      |  31.6     | 1     | 35.67 |
+---------------------+-----------+-----------+-------+-------+

For averages that constantly became 0 such as quick I reran the tests in nanoseconds and converted for better comparison.
This is the summary of the results in ms.
+---------------------+-----------+-----------+-------+-------+
| Number Type         | Insertion | Selection | Quick | Merge |
|                     |           |           |       |       |
+---------------------+-----------+-----------+-------+-------+
| 1000 random         | 13. 6     |         7 |    .3 |   2.6 |
+---------------------+-----------+-----------+-------+-------+
| 1000 few unique     |  0.6      |  11       |  0.3  | 0.6   |
+---------------------+-----------+-----------+-------+-------+
| 1000 nearly ordered | 0.686     | 0.53      | 0.323 | 11.33 |
+---------------------+-----------+-----------+-------+-------+
| 1000 reverse order  |  1        | 0.59      | 0.52  | 10    |
+---------------------+-----------+-----------+-------+-------+
| 1000 sorted         |  0.66     | 0.526     | 0.446 | 8.67  |
+---------------------+-----------+-----------+-------+-------+
| 10000 random        | 81.6      |  31.6     | 1     | 35.67 |
+---------------------+-----------+-----------+-------+-------+
 
 //do quick and selection in nano seconds and convert to mili

*/
@RunWith(JUnit4.class)
public class SortComparisonTest
{
	//~ Constructor ........................................................
	@Test
	public void testConstructor()
	{
		new SortComparison();
	}

	//~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the methods work for empty arrays
	 */
	@Test
	public void testEmpty()
	{
		double[] a = {};
		double[] expectedResult = {};

		assertTrue("Insertion Sort",Arrays.equals(expectedResult,SortComparison.insertionSort(a)));
		assertTrue("Seelection Sort",Arrays.equals(expectedResult,SortComparison.selectionSort(a)));
		assertTrue("Merge Sort",Arrays.equals(expectedResult,SortComparison.mergeSort(a)));
		assertTrue("Quick Sort",Arrays.equals(expectedResult,SortComparison.quickSort(a)));
	}
	
	@Test
	public void testSingle()
	{
		double[] a = {1};
		double[] expectedResult = {1};

		assertTrue("Insertion Sort",Arrays.equals(expectedResult,SortComparison.insertionSort(a)));
		assertTrue("Seelection Sort",Arrays.equals(expectedResult,SortComparison.selectionSort(a)));
		assertTrue("Merge Sort",Arrays.equals(expectedResult,SortComparison.mergeSort(a)));
		assertTrue("Quick Sort",Arrays.equals(expectedResult,SortComparison.quickSort(a)));
	}

	@Test
	public void testRandom()
	{
		double[] a = {2,5,3,1,8,9,7,2,7,4,6};
		double[] b = {2,5,3,1,8,9,7,2,7,4,6};
		double[] c = {2,5,3,1,8,9,7,2,7,4,6};
		double[] d = {2,5,3,1,8,9,7,2,7,4,6};
		double[] expectedResult = {1,2,2,3,4,5,6,7,7,8,9};
		
		assertTrue("Insertion Sort",Arrays.equals(expectedResult,SortComparison.insertionSort(a)));
		assertTrue("Selection Sort",Arrays.equals(expectedResult,SortComparison.selectionSort(b)));
		assertTrue("Merge Sort",Arrays.equals(expectedResult,SortComparison.mergeSort(c)));
		assertTrue("Quick Sort",Arrays.equals(expectedResult,SortComparison.quickSort(d)));
	}
	
	@Test
	public void testSorted()
	{
		double[] a = {2,3,4,5};
		double[] expectedResult = {2,3,4,5};
		
		assertTrue("Insertion Sort",Arrays.equals(expectedResult,SortComparison.insertionSort(a)));
		assertTrue("Insertion Sort",Arrays.equals(expectedResult,SortComparison.selectionSort(a)));
		assertTrue("Merge Sort",Arrays.equals(expectedResult,SortComparison.mergeSort(a)));
		assertTrue("Qucik Sort",Arrays.equals(expectedResult,SortComparison.quickSort(a)));
	}

	@Test
	public void testDesceding()
	{
		double[] a = {9,8,7,6,5,5,4};
		double[] b = {9,8,7,6,5,5,4};
		double[] c = {9,8,7,6,5,5,4};
		double[] d = {9,8,7,6,5,5,4};

		double[] expectedResult = {4,5,5,6,7,8,9};

		assertTrue("Insertion Sort",Arrays.equals(expectedResult,SortComparison.insertionSort(a)));
		assertTrue("Selection Sort",Arrays.equals(expectedResult,SortComparison.selectionSort(b)));
		assertTrue("Merge Sort",Arrays.equals(expectedResult,SortComparison.mergeSort(c)));
		assertTrue("Quick Sort",Arrays.equals(expectedResult,SortComparison.quickSort(d)));

	}

	@Test
	public void testDuplicates()
	{
		double[] a = {5,5,5,6,7,6,6,7};
		double[] b = {5,5,5,6,7,6,6,7};
		double[] c = {5,5,5,6,7,6,6,7};
		double[] d = {5,5,5,6,7,6,6,7};

		double[] expectedResult = {5,5,5,6,6,6,7,7};
		
		assertTrue("Insertion Sort",Arrays.equals(expectedResult,SortComparison.insertionSort(a)));
		assertTrue("Selection Sort",Arrays.equals(expectedResult,SortComparison.selectionSort(b)));
		assertTrue("Merge Sort",Arrays.equals(expectedResult,SortComparison.mergeSort(c)));
		assertTrue("Quick Sort",Arrays.equals(expectedResult,SortComparison.quickSort(d)));
	}
	
	// TODO: add more tests here. Each line of code and ech decision in Collinear.java should
	// be executed at least once from at least one test.

	// ----------------------------------------------------------
	/**
	 *  Main Method.
	 *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
	 *
	 */


	public static double[] readFile (String filename) {
		ArrayList<Double> numbers = new ArrayList<Double>();
		File file = new File(filename); 
		try
		{
			FileReader fR = new FileReader (file);
			BufferedReader words = new BufferedReader(fR);
			try
			{
				String line = words.readLine();
				while (line != null) 
				{
					numbers.add(Double.parseDouble(line));
					line = words.readLine();
				}
				words.close();		
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		double[] digits = numbers.stream().mapToDouble(Double::doubleValue).toArray();
		return digits;
	}

	public static void main(String[] args)
	{
		String file1 = "C:\\Users\\merli\\eclipse-workspace\\Sort\\src\\numbers1000.txt" ;
		double[] numbers1= readFile(file1);

		String file2 = "C:\\Users\\merli\\eclipse-workspace\\Sort\\src\\numbers1000Duplicates.txt" ;
		double[] numbers2 = readFile(file2);
		
		String file3 = "C:\\Users\\merli\\eclipse-workspace\\Sort\\src\\numbersNearlyOrdered1000.txt" ;
		double[] numbers3 = readFile(file3);
		
		String file4 = "C:\\Users\\merli\\eclipse-workspace\\Sort\\src\\numbersReverse1000.txt" ;
		double[] numbers4 = readFile(file4);
		
		String file5 = "C:\\Users\\merli\\eclipse-workspace\\Sort\\src\\numbersSorted1000.txt" ;
		double[] numbers5 = readFile(file5);
		
		String file6 = "C:\\Users\\merli\\eclipse-workspace\\Sort\\src\\numbers10000.txt" ;
		double[] numbers6 = readFile(file6);
				
		long start = 0 ;
		long end = 0 ;
		double duration = 0 ;
		double timeInNano = 0 ;	
		start = System.nanoTime();
		SortComparison.insertionSort(numbers1);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for insertionSort 1000 numbers is  : " + (duration));

		numbers1 = readFile(file1); 	
		start = System.nanoTime();
		SortComparison.selectionSort(numbers1);
		end = System.nanoTime();
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for selectionSort 1000 numbers is  : " + (duration));
		
		numbers1 = readFile(file1);
		start = System.nanoTime();
		SortComparison.quickSort(numbers1);
		end = System.nanoTime();
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for quickSort 1000 numbers is  : " + (duration));
		timeInNano = end - start ;
		System.out.println("Execution time in  nanoseconds is  : " + (timeInNano));

		numbers1 = readFile(file1);
		start = System.nanoTime();
		SortComparison.selectionSort(numbers1);
		end = System.nanoTime();
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for mergeSort 1000 numbers is  : " + (duration));

		
		numbers2 = readFile(file2);
		start = System.nanoTime();
		SortComparison.insertionSort(numbers2);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for insertionSort 1000 dulpicate numbers is  : " + (duration ));

		numbers2 = readFile(file2);
		start = System.nanoTime();
		SortComparison.insertionSort(numbers2);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for selectionSort 1000 dulpicate numbers is  : " + (duration));
		
		numbers2 = readFile(file2);
		start = System.nanoTime();
		SortComparison.quickSort(numbers2);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for quickSort 1000 dulpicate numbers is  : " + (duration));
		timeInNano = end - start ;
		System.out.println("Execution time in  nanoseconds is  : " + (timeInNano));
			
		numbers2 = readFile(file2);
		start = System.nanoTime();
		SortComparison.insertionSort(numbers2);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for mergeSort 1000 dulpicate numbers is  : " + (duration));
		timeInNano = end - start ;
		System.out.println("Execution time in  nanoseconds is  : " + (timeInNano));

		numbers3 = readFile(file3);
		start = System.nanoTime();
		SortComparison.insertionSort(numbers3);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for insertionSort 1000 near ordered is  : " + (duration ));
		timeInNano = end - start ;
		System.out.println("Execution time in  nanoseconds is  : " + (timeInNano));

		numbers3 = readFile(file3);
		start = System.nanoTime();
		SortComparison.selectionSort(numbers3);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for selectectionSort 1000 near ordered is  : " + (duration ));
		timeInNano = end - start ;
		System.out.println("Execution time in  nanoseconds is  : " + (timeInNano));
		
		numbers3 = readFile(file3);
		start = System.nanoTime();
		SortComparison.quickSort(numbers3);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);	
		System.out.println("Execution time in  for quickSort 1000 near ordered is  : " + (duration ));
		timeInNano = end - start ;
		System.out.println("Execution time in  nanoseconds is  : " + (timeInNano));

		numbers3 = readFile(file3);
		start = System.nanoTime();
		SortComparison.mergeSort(numbers3);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for mergeSort 1000 near ordered is  : " + (duration ));

		numbers4 = readFile(file4);
		start = System.nanoTime();
		SortComparison.insertionSort(numbers4);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for insertionSort 1000 reverse ordered is  : " + (duration ));
		timeInNano = end - start ;
		System.out.println("Execution time in  nanoseconds is  : " + (timeInNano));

		numbers4 = readFile(file4);
		start = System.nanoTime();
		SortComparison.selectionSort(numbers4);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for selectionSort 1000 reverse ordered is  : " + (duration ));
		timeInNano = end - start ;
		System.out.println("Execution time in  nanoseconds is  : " + (timeInNano));
		
		
		numbers4 = readFile(file4);
		start = System.nanoTime();
		SortComparison.quickSort(numbers4);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for quickSort 1000 reverse ordered is  : " + (duration ));
		timeInNano = end - start ;
		System.out.println("Execution time in  nanoseconds is  : " + (timeInNano));
		
		numbers4 = readFile(file4);
		start = System.nanoTime();
		SortComparison.mergeSort(numbers4);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for mergeSort 1000 reverse ordered is  : " + (duration ));
		
		numbers5 = readFile(file5);
		start = System.nanoTime();
		SortComparison.insertionSort(numbers5);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for insertionSort 1000 ordered is  : " + (duration ));
		timeInNano = end - start ;
		System.out.println("Execution time in  nanoseconds is  : " + (timeInNano));
		
		numbers5 = readFile(file5);
		start = System.nanoTime();
		SortComparison.selectionSort(numbers5);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for selectionSort 1000 ordered is  : " + (duration ));
		timeInNano = end - start ;
		System.out.println("Execution time in  nanoseconds is  : " + (timeInNano));
		
		numbers5 = readFile(file5);
		start = System.nanoTime();
		SortComparison.quickSort(numbers5);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for quickSort 1000 ordered is  : " + (duration ));
		timeInNano = end - start ;
		System.out.println("Execution time in  nanoseconds is  : " + (timeInNano));
		
		numbers5 = readFile(file5);
		start = System.nanoTime();
		SortComparison.mergeSort(numbers5);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for mergeSort 1000 ordered is  : " + (duration ));
		timeInNano = end - start;
		System.out.println("Execution time in  nanoseconds is  : " + (timeInNano));
		
		numbers6 = readFile(file6);
		start = System.nanoTime();
		SortComparison.insertionSort(numbers6);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for insertionSort 10000  is  : " + (duration ));
		
		numbers6 = readFile(file6);
		start = System.nanoTime();
		SortComparison.selectionSort(numbers6);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for selectionSort 10000  is  : " + (duration ));
		
		numbers6 = readFile(file6);
		start = System.nanoTime();
		SortComparison.quickSort(numbers6);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for quickSort 10000  is  : " + (duration ));
		
		numbers6 = readFile(file6);
		start = System.nanoTime();
		SortComparison.mergeSort(numbers6);
		end = System.nanoTime();	
		duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("Execution time in  for mergeSort 10000  is  : " + (duration ));


	}
}
