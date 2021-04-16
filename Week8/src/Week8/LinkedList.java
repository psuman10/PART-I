package Week8;

public class LinkedList {

    public static class Node {
        int data;

        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
        
    }
 
    public Node head = null;
    public Node tail = null;

//    public void addNode(int data) {
//        Node newNode = new Node(data);
//        
//        if(head == null) {
//            head = newNode;
//        }
//        else {
//
//            Node current = head;
//            while(current.next != null) {
//                current = current.next;
//            }
//            current.next = newNode;
//        }
//
//    }
    
    public void addNode (int data) {
        Node newNode = new Node(data);
        
        if(head == null) {
            head = newNode;
            tail = head;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void printList() {
        Node current = head;
        while(current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    public int size() {
        
        int count = 0;
        Node current = head;
        while(current!=null) {
            current = current.next;
            count++;
        }
        
        return count;
    }
    
    public int get(int i) {
        Node current = head;
        int size = size();
        if(size != 0 && i<size) {
            for(int j=0; j<i; j++) {
                current = current.next;
            }
        
            return current.data;
        }
        return -1;
    }

}