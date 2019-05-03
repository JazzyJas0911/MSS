/*
 * Write 2 functions to calculate the MSS of a given array one with running time of O(n) and one with O(nlogn).
 * Request the user to enter a positive integer, and call it n.
 * 1. Generate n random integers between -100 to 100 and save them in array a. 
 * 2. Print the generated array and the outputs of your two functions. 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main{

	public static void main(String[] args){

		Scanner keyboard = new Scanner(System.in);
		List<Integer> a = new ArrayList<>();
		Random randomGenerator = new Random();
		
		System.out.println("Enter a positive integer");
		int n = keyboard.nextInt();
	
		for(int i = 0; i < n; i++){
			int num = -100 + randomGenerator.nextInt(200);
			a.add(num);
		}
		
		System.out.println("Generated Array: ");
		for(int i = 0; i < a.size(); i++){
			System.out.print(a.get(i) + ", ");
		}
		
		int MSS1 = MSS_n(a);
		int MSS2 = MSS_nlogn(a, 0, a.size() - 1);
		
		System.out.println("\nMSS O(n): " + MSS1);
		System.out.println("MSS O(nlogn): " + MSS2);
	}
	
	public static int MSS_n(List<Integer> a){
		int temp = 0;
		int MSS = 0;
		for(int i = 0; i < a.size(); i++){
			temp += a.get(i);
			if(temp > MSS)
				MSS = temp;
			else if(temp < 0)
				temp = 0;
		}
		return MSS;
	}
	
	public static int MSS_nlogn(List<Integer> a, int start, int end){
		int MSS = 0;
		if(start == end)
			MSS = a.get(start);
		else{
			int middle = (start + end) / 2;
			int MSS_left = MSS_nlogn(a, start, middle);
			int MSS_right = MSS_nlogn(a, middle + 1, end);
			int temp = 0;
			int lSum = 0;
			for(int i = middle; i >= 0; i--){
				temp += a.get(i);
				if(temp > lSum)
					lSum = temp;
			}
			temp = 0;
			int rSum = 0;
			for(int i = middle + 1; i < end; i++){
				temp += a.get(i);
				if(temp > rSum)
					rSum = temp;
			}
			int MSS_middle = lSum + rSum;
			MSS =  Math.max(Math.max(MSS_left, MSS_right), MSS_middle);
		}
		return MSS;
	}

}