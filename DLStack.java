/**
* DLStack represents a stack which is implemented using a doubly linked list
*  and define methods to manipulate the doubly linked stack.
*
* @author Maya Al Hourani
*/

public class DLStack<T> implements DLStackADT<T> {
	private DoubleLinkedNode<T> top;
	private int numItems;
	
	//Initializing top and numItems for the stack.
	public DLStack() {
		top = null;
		numItems = 0;
	}
	
	//Push Method
	public void push(T dataItem) {
		
		//Creating a node for the dataItem
		DoubleLinkedNode<T> temp = new DoubleLinkedNode<T>(dataItem);
		
		//if stack is empty
		if (top == null) {
			top = temp;
		} else { //Otherwise node is added to the top of the stack
		temp.setPrevious(top);
		top.setNext(temp);
		top = temp;
		}
		numItems++;
	}
	
	//Pop Method
	public T pop() throws EmptyStackException{
		
		//if the stack is empty.
		if (top == null) {
			throw new EmptyStackException("Stack is empty.");
		}
	
	//Otherwise element is stored, removed then returned.
	T result = top.getElement();
	top = top.getPrevious();
	numItems--;
	return result;
	}
	
	//Pop method with int k parameter
	public T pop(int k) throws InvalidItemException{
		DoubleLinkedNode<T> current = top;
		DoubleLinkedNode<T> previous;
		DoubleLinkedNode<T> next;
		T result = null;
		
		// If k is larger than the number of elements in the stack.
		if (k > numItems || k <= 0) {
			throw new InvalidItemException("Invalid value.");
		} 
			//Finding k in the stack.
			for (int i = 1; i < k; i++) {
				current = current.getPrevious();
			}
			result = current.getElement();
			previous = current.getPrevious();
			next = current.getNext();
			
			// Checking different cases, e.g removing front or rear node.
			if (current == top) { //If k points to the front node.
				top = previous;
			} else if (previous != null) { //If k is in between nodes.
				previous.setNext(next);
				next.setPrevious(previous);
				current.setNext(null);
				current.setPrevious(null);
			} else { //if k points to the rear.
				next.setPrevious(null);
				current.setNext(null);
			}
			numItems--;
		return result;
			}
	
	//Peek method
	public T peek() throws EmptyStackException {
		
		//If stack is empty
		if (top == null) {
			throw new EmptyStackException("Stack is empty!");
		} else {
			return top.getElement();
		}
		
	}
	
	//isEmpty Method
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	//size Method
	public int size() {
		return numItems;
	}
	
	//getTop Method
	public DoubleLinkedNode<T> getTop(){
		return top;
	}
	
	//toString Method 
	public String toString() {
		DoubleLinkedNode<T> current = top;
		String string = "[";
		
		while (current != null) {
			
			string += current.getElement() + " ";
			current = current.getNext();
		}
		string += "]";
		return string;
	}
}