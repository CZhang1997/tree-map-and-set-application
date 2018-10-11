package cxz173430;
/**
 * @author 		Churong Zhang 
 * 				cxz173430
 * 				Sept 25 2018
 * 				Dr. Raghavachari
 * 				This class is for Intensive Track Assignment 3. TreeMaps
 * 				Problem 1, 2 and 3 is complete,
 */

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;


public class Application {

	/*
	 * Problem 1
	 * check how many pair of indexs in the array can sum to X
	 * @param A the array we want to check
	 * @param X the sum we want
	 * @return how many pair of copies can produce the sum X
	 */
	static int howMany(int[] A, int X) { // RT = O(nlogn).
	     // How many indexes i,j (with i != j) are there with A[i] + A[j] = X?
	     // A is not sorted, and may contain duplicate elements
	     // If A = {3,3,4,5,3,5} then howMany(A,8) returns 6
		//////////////////////////////////////////////////////////
		/////////////////Add array elements to the map////////////
		int result = 0;
		Map<Integer, Integer> map = new TreeMap<>();
		for(int a : A)
		{
			Integer i = map.get(a);
			if(i == null)
			{
				map.put(a, 1);
			}
			else
			{
				map.put(a, i + 1);
			}
		}
		/////////////////////////////////////////////////////////
		/////////////Create iterator for the map/////////////////
		//Set<Entry<Integer,Integer>>map.entrySet();
		for(Map.Entry<Integer,Integer> me: map.entrySet())
		{
			Integer e = me.getKey();
			if(e*2 > X)
			{
				break;
			}
			Integer c = me.getValue();
			Integer c2 = map.get(X-e);
			if(c2 != null)
			{
				if(e == X-e)
				{
					result = result + c * (c-1)/2;
				}
				else
				{
					result = result + c * c2;
				}
			}
		}
/*		// first thought, then realize there I can use Entry set instead
		Collection<Integer> values = map.values();
		Set<Integer> keys = map.keySet();
		//////should Use map.entrySet() to iterate over key/value pairs in maps. Code for key=x/2 is incorrect.
		Iterator<Integer> iterValue = values.iterator();
		Iterator<Integer> iterKeys = keys.iterator();
		
		Integer key = 0;
		Integer value = 0;
		
		////////////////////////////////////////////////////////
		///loop through the map until key is less than X - key
		while(iterKeys.hasNext() && key < X - key)
		{
			key = iterKeys.next();
			value = iterValue.next();
			
			Integer y = map.get(X - key);
			if(y != null)
			{
				if(X - key == key)
				{/// in case if X is 2 * key // this mean that key and y are the same 
					result = result + (value * (value-1) /2);
				}
				else
				{
					result = result + (value * y);
				}
				
			}
			
		} */
		return result;
	   }
	/*
	 * Problem 2
	 * return an array of T that the input array's element only appear once
	 * @param A the input array
	 * @return the elements that only appear once from the input array
	 */
	static<T extends Comparable<? super T>> T[] exactlyOnce(T[] A) { // RT = O(nlogn).
	     // Ex: A = {6,3,4,5,3,5}.  exactlyOnce(A) returns {6,4}
		Map<T, Integer> map = new TreeMap<>();
		int size = 0;// size for return array
		for(T a : A)
		{
			Integer i = map.get(a);
			if(i == null)
			{
				map.put(a, 1);
				size++;  // add one when there is a new element
			}
			else
			{
				map.put(a, i + 1);
				if(i == 1)
				{
					size--; // decrease one if it is the second time found the same element
				}
			}
		}
		
		T[] result = (T[])new Comparable[size]; //(T[]) Array.newInstance(class<T>, size);
		int index = 0;
		for(T i: A)
		{
			
			if(map.get(i) == 1)
			{
				result[index] = i;
				index++;
			}
		}
		
		return result;
	   }
	/*
	 * Problem 3
	 * find the maximum of consecutive integers from the input array
	 * @param A the input array
	 * @return the maximum number of consecutive integers
	 */
	static int longestStreak(int[] A) { // RT = O(nlogn).
	     // Ex: A = {1,7,9,4,1,7,4,8,7,1}.  longestStreak(A) return 3,
	     //    corresponding to the streak {7,8,9} of consecutive integers
	     //    that occur somewhere in A.
		Set<Integer> set = new TreeSet<>();
		for(int a : A)
		{
			set.add(a);
		}
		
		Iterator<Integer> iter = set.iterator();
		int resultMax = 1;
		int result = 1;
		Integer prev = Integer.MAX_VALUE;
		int index = 0;
		int size = set.size();
		
		while(iter.hasNext())
		{
			int current = iter.next();
			
			if(current - 1 == prev)
			{
				result ++;
				if(result > resultMax)
				{
					resultMax = result;
				}
			}
			else
			{
				result = 1;
				if(size - index <= resultMax)
				{
					// When the current count of result reset
					// and the max result is greater than or equal to
					// the remaining elements, break the loop
					// because even if the remaining elements
					// are consecutive, the current result will 
					// not greater than max result
					break; 
				}
			}
			prev = current;
			index++;
			
		}
		
		return resultMax;
	   }
	
	
	public static void main(String [] args)
	{
		int [] a = {4,4,4,4};//{3,3,4,5,3,5,4, 8 ,7, 4}; // {1, 2, 3 , 3, 4, 1, 2, 5};
		int [] A = {1,7,9,4,1,7,4,8,7,1,11,16,13, 18};
		Integer [] c = {6,3,4,5,3,5};
		
		System.out.println(howMany(a, 8));
		System.out.println(longestStreak(A));
		Object [] d = exactlyOnce(c);
		System.out.println(Arrays.toString(d));
	
		
	}
	
}
