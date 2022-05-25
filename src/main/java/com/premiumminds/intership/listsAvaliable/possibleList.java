package com.premiumminds.intership.listsAvaliable;

import java.util.Arrays;

public class possibleList {
	
	/**
	 * A function that return an array with 1 on the index that is still a possibility given his position
	 * 
	 * @param startingPoint Starting point to see where it could connect
	 * @param usedElems Array with 1 on the index of the used elements
	 * @return An array with 1 on the index that is a possibility to connect
	 */
	public static int[] getAllPossibilities (int startingPoint, int[] usedElems) { //startingPoint [0,8]
		
		int[] possibilities = new int[9];
		
		if(startingPoint == 4) {
			
			Arrays.fill(possibilities, 1);
			possibilities[4] = 0;
			
		} else if ((startingPoint % 2) == 0) {
			
			for(int i = 1; i < possibilities.length; i+=2) {
				possibilities[i] = 1;
			}
			possibilities[startingPoint] = 0;
			possibilities[4] = 1;
			
		} else {

			Arrays.fill(possibilities, 1);
			possibilities[startingPoint] = 0;
			if (startingPoint == 1) {
				possibilities[7] = 0;
			} else if (startingPoint == 3) {
				possibilities[5] = 0;
			} else if (startingPoint == 5) {
				possibilities[3] = 0;
			} else {
				possibilities[1] = 0;
			}
			
		}
		
		return filterUsed(startingPoint, possibilities, usedElems);
	}
	
	/**
	 * Given the position and the usedElems it filters the already used but it checks if it allows
	 * to access the number "behind" ex: [2, 8] because of the 5
	 * 
	 * @param position The point to check the next possibilities
	 * @param newList The possibilities to connect
	 * @param usedElems The elements already used
	 * @return An array with the newList filtered
	 */
	private static int[] filterUsed(int position, int[] newList, int[] usedElems) {  //position [0,8]
		
		for (int i = 0; i < newList.length; i++) {
			
			if (usedElems[i] == 1) {
				
				int maybe = getBlocked(position, i, usedElems);
				if (maybe != 10) {

					newList[maybe] = 1;
				}
	
				newList[i] = 0;
	
			}
			
		}
		
		return newList;
	}

	/**
	 * A function that checks if the next position was blocking an available number
	 * 
	 * @param position The point to check the next possibilities
	 * @param next The next possibility
	 * @param usedElem an array with the already used elements
	 * @return 10 if there's no number available or the index of the available number
	 */
	private static int getBlocked (int position, int next, int[] usedElem) {
		
		int i = 10;
		
		if ((position % 2) == 0) {
			
			i = pairBlocked(position, next);
			
		} else if (position != 5) {
			
			i = oddBlocked(position, next);
			
		}		
		
		if (i == 10 || usedElem[i] == 1)
			i = 10;
		
		return i;
	}

	private static int oddBlocked (int position, int next) {
		
		int i = 10;
		
		if (next == 4) {
			if (position == 1)
				i = 7;
			else if (position == 3)
				i = 5;
			else if (position == 5)
				i = 3;
			else {
				i = 1;
			}
			
		}
		
		return i;
	}

	private static int pairBlocked (int position, int next) {
		
		int i = 10;
		
		if (position == 0) {
			if (next == 1) 
				i = 2;
			else if (next == 3)
				i = 6;
			else if (next == 4)
				i = 8;
		} else if (position == 2) {
			if (next == 1) 
				i = 0;
			else if (next == 4)
				i = 6;
			else if (next == 5)
				i = 8;
		} else if (position == 6) {
			if (next == 3) 
				i = 0;
			else if (next == 4)
				i = 2;
			else if (next == 7)
				i = 8;
		} else if (position == 8) {
			if (next == 4) 
				i = 0;
			else if (next == 5)
				i = 2;
			else if (next == 7)
				i = 6;
		}
		
		return i;
	}

}
