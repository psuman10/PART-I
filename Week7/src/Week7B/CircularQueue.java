package Week7B;

public class CircularQueue {
	int queue[]=new int [6];
	int front;
	int rear;
	int size;

	 


	//enqueue
	public void enqueue(int value) {
	    
	    if (!isFull()) {
	        queue[rear]=value;
	        //
	        rear=(rear+1)%6;
	        size++;
	    }else {
	        System.out.println("Queue is full");
	    }
	    
	}
	//dequeue
	public void dequeue() {
	    if (!isEmpty()) {
	        front = (front+1)%6;
	        size--;    
	    }else{
	        System.out.println("Queue is empty");
	    }
	    
	}
	//isFull
	public boolean isFull() {
	    return size==6;
	}
	//isEmpty
	public boolean isEmpty() {
	return size==0;    
	}
	//display
	public void display() {
	    System.out.println("front:"+front);
	    System.out.println("rear:"+rear);
	    System.out.println("size:"+size);
	    
	    System.out.println();
	    
	    System.out.print("element in queue:  ");
	    
	for(int i=0; i<size;i++) {
	    System.out.print(queue[(front+i)%6]+ " ");
	}
	System.out.println();
	System.out.println("\nactual array");
	for(int i:queue) {
	    System.out.print(i+"  ");
	}

	 

	}
	    
	
}