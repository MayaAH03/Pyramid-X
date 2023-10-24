/**
* This class is used to compute the path of the entrance of the pyramid and all its treasure chambers.
*
* @author Maya Al Hourani
*/

public class PathFinder {
	private Map pyramidMap;
	
	//Main constructor to create map, otherwise throws an exception.
	public PathFinder(String fileName){
		try {
			pyramidMap = new Map(fileName);
		} catch (Exception e) {
			System.out.println("File not found.");
		} 
	}
	
	//path method uses the algorithm provided in the assignment outline 
	//to find a path from the entrance to all the treasure chambers in the map.
	public DLStack path() {
		
		//Initializing starting chambers and number of chambers collected.
		int collectedTreasure = 0;
		int totalTreasureChambs = pyramidMap.getNumTreasures();
		
		//Creating a stack to store the chambers in.
		DLStack <Chamber> path = new DLStack <Chamber>();
		
		//Pushing the entrance chamber into the stack.
		path.push(pyramidMap.getEntrance());
		
		//Marking the entrance chamber as pushed.
		pyramidMap.getEntrance().markPushed();
		
		
		//Algorithm provided in assignment outline to run through the map and look for treasure chambers.	
		while (!(path.isEmpty())) {
			if (path.peek() != null) {
				//If treasure is found the collectedTreasure is increased.
				if (path.peek().isTreasure()){ 
					collectedTreasure ++; 
			 
					if (collectedTreasure == totalTreasureChambs) {
						break;
					}
				}
			}
			 //Calling bestChamber method to determine which chamber is the best to go to.
			 if (bestChamber(path.peek()) != null) {
				 
				 //The bestChamber is pushed to the stack and marked as pushed
				 path.push(bestChamber(path.peek()));
				 path.peek().markPushed();
				 
			 //Otherwise it pops the top chamber in the stack and marks it as popped.	 
			 } else {
				 path.peek().markPopped();
				 path.pop();
			 }
	}
		return path;
	}
	
	//getMap Method
	public Map getMap() {
		return pyramidMap;
	}
	
	
	//isDim Method checks to see if currentChamber is dim.
	public boolean isDim(Chamber currentChamber) {
		
		//Makes sure the current chamber is not a lighted one or if its null.
		if (!(currentChamber.isLighted()) && currentChamber != null) {
			
			//Loops through neighboring chambers to see if any surrounding chambers are lighted.
			for (int i = 0; i < 6; i++) {
				if(currentChamber.getNeighbour(i) != null) {
					if (!(currentChamber.isSealed())) { 
						if (currentChamber.getNeighbour(i).isLighted()) {
							return true;
								}
							}
						}
					}
				}
		
		return false;
	}
	
	
	//bestChamber method to decide which chamber to go into.
	public Chamber bestChamber(Chamber currentChamber) {
		
		//Looking for an unmarked treasure chamber (if any) to move to.
		for (int i = 0; i < 6; i++) { 
			if (currentChamber.getNeighbour(i) != null && currentChamber != null) {
				if (currentChamber.getNeighbour(i).isTreasure()) {
					if (!(currentChamber.getNeighbour(i).isMarked())) {
						return currentChamber.getNeighbour(i);
					}
				}
			}
		}
		
		//Looking for an unmarked lighted chamber (if any) to move to.
		for (int i = 0; i < 6; i++) {
			if (currentChamber.getNeighbour(i) != null && currentChamber != null) {
				if (currentChamber.getNeighbour(i).isLighted()) {
					if (!(currentChamber.getNeighbour(i).isMarked())) {
						return currentChamber.getNeighbour(i);
						}
					}
				}
			}
		
		//Looking for an unmarked dim chamber (if any) to move to.
		for (int i = 0; i < 6; i++) {
			if (currentChamber.getNeighbour(i) != null && currentChamber != null) {
				if (isDim(currentChamber.getNeighbour(i))== true) { 
						if (!(currentChamber.getNeighbour(i).isMarked())) {
							return currentChamber.getNeighbour(i);
						}
					}
				}
			}
		
	//otherwise if there is none of the above chambers, return null.
	return null;
	}
}