package com.premiumminds.internship.screenlocking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.premiumminds.intership.listsAvaliable.possibleList;

/**
 * Created by aamado on 05-05-2022.
 */
class ScreenLockinPattern implements IScreenLockinPattern {

	/**
	 * Method to count patterns from firstPoint with the length
	 * @param firstPoint initial matrix position
	 * @param length the number of points used in pattern
	 * @return number of patterns
	*/
	public Future<Integer> countPatternsFrom(int firstPoint, int length) {
		ExecutorService service = Executors.newSingleThreadExecutor (); //Could be better for example creating length threads and which
																		// go to a different next point and do everything else
		return service.submit(new Task(firstPoint-1, length));
	}
		
}

class Task implements Callable<Integer> {
	
	int startPoint;							// The initial point to see where it connects [0,8]
	int[] alreadyUsed;						// An array with the already used elements, those elements 
											// have 0 in the index equal to the element in the array
	int len;								// The length of the combinations
	List<Integer> sequences; 				// The current sequence 
	List<List<Integer>> finalSequences;		// The final list with all the sequences
	
	
	/**
	 * Constructor for the class Task
	 * 
	 * @param startingPoint The initial point to see where it connects [0,8]
	 * @param length The length of the combinations
	 */
	public Task (int startingPoint, int length) {
		this.startPoint = startingPoint;
		this.alreadyUsed = new int[9];
		this.len = length;
		this.sequences = new ArrayList<Integer>();
		this.finalSequences = new ArrayList<List<Integer>>();
	}
	
	@Override
	public Integer call() throws Exception {
		if (len < 10 && len > 0 && startPoint >= 0 && startPoint < 9) {
			solver(this.startPoint, this.alreadyUsed, this.len, this.sequences, this.finalSequences);
		}
		
		return finalSequences.size();
	}
	
	/**
	 * Function that adds to allSequences all the combinations
	 * 
	 * @param startingPoint The initial point to see where it connects [0,8]
	 * @param used An array with 1 on the index of the used elements
	 * @param length The length of the combinations
	 * @param sequence A list to complete with a sequence 
	 * @param allSequences A list with all the sequences
	 */
	public void solver(int startingPoint, int[] used, int length, List<Integer> sequence, List<List<Integer>> allSequences) {
		
		if (length > 1) {
			if(sequence.size() <= (len-length)) {
				sequence.add(startingPoint);
			} else {
				sequence.set(len-length, startingPoint);
			}
			int[] possibilities = possibleList.getAllPossibilities(startingPoint, used);
			for (int i = 0; i < possibilities.length; i++) {
				if (possibilities[i] == 1) {
					used[startingPoint] = 1;
					solver(i, used, length-1, sequence, allSequences);
					used[startingPoint] = 0;
				}
			}
			
		} else {
			if(sequence.size() <= (len-length)) {
				sequence.add(startingPoint);
			} else {
				sequence.set(len-length, startingPoint);
			}
			List<Integer> copy = new ArrayList<>(sequence);
			allSequences.add(copy);
			return;
		}
		
	}
	
}
