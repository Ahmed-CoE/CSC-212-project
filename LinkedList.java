
public class LinkedList <T> implements List <T>{

	private Node <T> head;
	private Node <T> current;
	
	public LinkedList() {
		head = current = null;
	}
	
	public boolean empty() {
		return head == null;
	}
	
	public boolean full() {
		return false;
	}
	
	public boolean last() {
	return current.next == null;
	}
	
	public void findFirst() {
		current = head;
	}
	
	public void findNext() {	
		current = current.next;
	}
	
	public T retrieve() {
		return current.data;
	}
	
	public void update(T e) {
		current.data = e;
	} 
	
	
	public void  insert (T val) {
		if(empty())
			head = current = new Node(val);
		
		else {
			Node tmp = new Node();
			tmp = current.next;
			current.next = new Node(val);
			current = current.next;
			current.next = tmp;
		}
	}
	
	public void remove() {
		if(head == current) {
			head = head.next;
			current = head;}
		else {
			Node tmp = head;
			while(!(tmp.next==current)) {
				tmp = tmp.next;
			}
			current = current.next;
			tmp.next= current;
		}
		
		if(current == null)
			current = head;
	}
	
	public void display() {
		if(empty())
			return;
		current = head;
		while(!last()) {
			System.out.println(retrieve());
			current = current.next;
		}
		System.out.println(retrieve());
		
	}
	
	public void reverse() {
		if(empty())
			return;
		
		Node <T> prev = null;
		current = head;
		Node <T> after = head.next;
		
		while(current != null) {
			current.next = prev;
			prev = current;
			current = after;
			if(current != null)
			after = current.next;
		}
		
		head = prev;
		current = head;
	}
	}
		
	







