package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		LLNode<E> first = head;
		
		sb.append("\n[");
		while (first != null) {
			sb.append(first.data + ", ");
			first = first.next;
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		
		return sb.toString();
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) {
		// TODO: Implement this method
		
		if (element == null) {
			throw new NullPointerException("Element to add is null!");
		}
		
		LLNode<E> newNode = new LLNode<E>(element);
		newNode.prev = tail.prev;
		newNode.next = tail;
		tail.prev.next = newNode;
		tail.prev = newNode;
		
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) {
		// TODO: Implement this method.
		
		if (index >= size|| index < 0) {
			throw new IndexOutOfBoundsException("Index out of bounds to get!");
		} else {
			// currentNode is the one after the head (sentinel)
			LLNode<E> currentNode = head.next;
			// traverse the linked list by re-assigning currentNode until the index
			for (int i = 0; i < index; i++) {
				currentNode = currentNode.next;
			}
			return currentNode.data;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) 
	{
		// TODO: Implement this method
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index out of bounds for add at index!");			
		} else if (element == null) {
			throw new NullPointerException("Element is null for add at index");
		} else if (index == size) {
			// if index is the same as the list size, call the add method to add it to the end
			this.add(element);
		} else {
			LLNode<E> current = head;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			new LLNode<E>(element, current, current.next);
			size++;
		}		
		
	}


	/** Return the size of the list */
	public int size() {
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index out of bounds to remove!");
		} else {
			LLNode<E> removeNode = head.next;
			for (int i = 0; i < index; i++) {
				removeNode = removeNode.next;
			}
			removeNode.next.prev = removeNode.prev;
			removeNode.prev.next = removeNode.next;
			size--;
			return removeNode.data;
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index out of bounds for set!");			
		} else if (element == null) {
			throw new NullPointerException("Element is null for set!");
		} 
		
		LLNode<E> newNodeR = head.next;
		for (int i = 0; i < index; i++) {
			newNodeR = newNodeR.next;
		}
		E oldData = newNodeR.data;
		newNodeR.data = element;
		return oldData;
	}
	
	public static void main(String args[]) {
		MyLinkedList<Integer> ll = new MyLinkedList<Integer>();
//		int indexGet = 2;
//		int indexRemove = 0;
//		
//		ll.add(1);
//		ll.add(2);
//		ll.add(3);
//		
//		System.out.println("Linked List initial" + ll);
//		System.out.println("Linked List initial size --> " + ll.size());
//		System.out.println("Linked List get at index: " + indexGet + " --> " + ll.get(indexGet));
//		System.out.println("Linked List remove at index: " + indexRemove + " --> " + ll.remove(indexRemove));
//		System.out.println("Linked List after remove " + ll);
//		System.out.println("Linked List size after remove " + ll.size());
		
		MyLinkedList<String> lls = new MyLinkedList<String>();
		int indexGetString = 0;
		int indexReplaceString = 0;
		
		lls.add("new");
		lls.add("string");
		lls.add("here");
		
		System.out.println("Linked List initial" + lls);
		System.out.println("Linked List initial size --> " + lls.size());
		lls.add(0, "another");
		System.out.println("Linked List after add at index" + lls);
		System.out.println("Linked List size after add at index " + lls.size());
		System.out.println("Linked List get at index: " + indexGetString + " --> " + lls.get(indexReplaceString));
		lls.set(indexReplaceString, "replaced");
		System.out.println("Linked List after set" + lls);
		
	}
	
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) {
		this.data = e;		
	}
	
	public LLNode(E e, LLNode<E> prevNode, LLNode<E> nextNode) {
		this(e);
		this.next = nextNode;
		this.prev = prevNode;
		prevNode.next = this;
		nextNode.prev = this;
	}
	
	public String toString() {
		return this.toString();
	}	
}
