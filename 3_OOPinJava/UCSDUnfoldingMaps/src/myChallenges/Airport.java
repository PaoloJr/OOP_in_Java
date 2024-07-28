package myChallenges;

import java.util.ArrayList;

public class Airport {
	
	private String city;
	private String country;
	private String code3;
	
	// toFind is a city name
	public static String findAirportCodeLinearSearch(String toFind, Airport[] airports) {
		
		// linear search
		for (int i = 0; i < airports.length; i++) {
			Airport currAirport = airports[i];
			String currCityAirport = currAirport.getCity();
			// use .equals (to compare the String of characters) instead of `==` which compares the exact same object in memory (references)
			if (currCityAirport.equals(toFind)) {
				return currAirport.getCode();
			}
		}
		return null;
	}
	
	// binary search
	public static String findAirporCodeBinarySearch(String toFind, Airport[] airports) {
		int low = 0;
		int mid;
		int high = airports.length - 1;
		
		while(low <= high) {
			// possible overflow (when array is very large)
//			mid = (low + high) / 2;
			// to guard against an overflow
			mid = low + ((high - low) / 2);
			int compare = toFind.compareTo(airports[mid].getCity());
			if (compare < 0) {
				high = mid - 1;
			} else if (compare > 0) {
				low = mid + 1;
			} else {
				return airports[mid].getCode();
			}						
		}
		
		return null;		
	}

	// selection sort algorithm
	// sort elements or array in-place (no return)
	// SearchAndSort.java file in `demos` package
	public static void selectionSort(int[] numbers) {
		int indexMin;
		for (int i = 0; i < numbers.length - 1; i++) {
			// set minimum value to first index
			indexMin = i;
			// loop over rest of array and compare indexMin with `j` (the next index)
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[j] < numbers[indexMin]) {
					indexMin = j;
				}
			}
			// likely have a helper method to swap values
			// swap(numbers, indexMin, i);
		}
	}		
	
	
	public String getCity() {
		return this.city;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public String getCode() {
		return this.code3;
	}
}