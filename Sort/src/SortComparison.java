import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Merlin Prasad
 *  @version HT 2020
 */

class SortComparison {

	/**
	 * Sorts an array of doubles using InsertionSort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 *
	 */
	static double [] insertionSort (double a[]){
		double temp = 0 ;
		int size = a.length;
		if(size > 1)
		{
			for(int i = 0; i< size ; i ++)
			{
				for(int j = i; j > 0; j --)
				{
					if(a[j] < a[j-1])
					{
						temp = a[j-1];
						a[j-1] = a[j];
						a[j] = temp ;
					}
				}
			}
		}
		return a;
	}

	/**
	 * Sorts an array of doubles using Selection Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double [] selectionSort (double a[]){
		double temp = 0 ;
		int size = a.length;
		double smallest = 0 ;
		int smallIndex = 0 ;
		if( size > 1)
		{
			for(int i = 0; i< size-1 ; i ++)
			{
				smallest = a[i];
				smallIndex = i ;
				for(int j = i+1; j <size ; j ++)
				{
					if(a[j] < smallest)
					{
						smallest = a[j];
						smallIndex = j ;
					}
				}
				temp = a[i];
				a[i] = smallest;
				a[smallIndex] = temp ;
			}
		}
		return a;

	}

	/**
	 * Sorts an array of doubles using Quick Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double [] quickSort (double a[]){
		if(a.length > 1)
		{
			quickSort(a,0,a.length-1);
		}
		return a ;
	}

	private static void quickSort(double a[],int lo,int hi){
		if (hi <= lo)
		{
			return;
		}

		int pivot = 0 ;
		pivot = split(a,lo,hi);
		quickSort(a,lo,pivot-1);
		quickSort(a,pivot+1,hi);
	}

	private static int split(double[] a, int lo, int hi) {
		int i = lo ;
		int j = hi  ;
		double pivot = a[lo] ;
		double temp = 0 ;

		while(i < j)
		{
			while(a[i] <= pivot && i< hi)
			{
				i++;
			}
			while (lo < j && a[j] > pivot )
			{
				j--;
			}
			if(i < j)
			{
				temp = a[j];
				a[j] = a[i];
				a[i] = temp ;
			}
		}

		temp = a[lo];
		a[lo] = a[j];
		a[j] = temp ;

		return j;
	}

	/**
	 * Sorts an array of doubles using Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted order.
	 */

	static double[] mergeSort (double[] a) {	
		int size = a.length;
		double[] temp = new double[size];	
		int hi = a.length -1 ;
		int lo = 0 ;
		int h = 0 ;
		int mid = 0 ;
		if(size > 1)
		{
			for(int i = 1; i< size ; i = 2 * i)
			{
				for(int j =  0; j < size ; j = j + 2 * i)
				{
					h = (j + 2*i) - 1 ;
					hi = Math.min(h, size-1) ;
					mid = i + j - 1 ;
					lo = j ;
					merge(a,temp, lo,mid,hi);
				}
			}
		}
		return a;
	}

	private static double[] merge(double a[],double temp[],int low, int mid,int hi){
		for(int i = 0 ; i < a.length ; i ++) 
		{
			temp[i] = a[i];
		}
		
		int k = low ;
		int i = low ;
		int j = mid + 1;
		while(i <= mid && j <= hi)
		{
			if(temp[i] <temp[j])
			{
				a[k] = temp[i];
				k ++ ;
				i ++ ;
			}
			else
			{
				a[k] = temp[j];
				k ++ ;
				j ++ ;
			}
		}

		while (i<= mid && k < a.length)
		{
			a[k] = temp[i];
			k ++ ;
			i ++ ;
		}

		while (k < a.length && j<= hi )
		{
			a[k] = temp[j];
			k ++ ;
			j ++ ;
		}

		return a ;
	}




	//	public static void main(String[] args) {
	//
	//		//todo: do experiments as per assignment instructions
	//		
	//	}

}//end class
