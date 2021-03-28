package Week7B;
public class main {
    public static void main(String[] args) {
    	CircularQueue soft=new CircularQueue();
            soft.enqueue(10);
            soft.enqueue(20);
            soft.enqueue(30);
            soft.enqueue(40);
            soft.enqueue(50);
            soft.enqueue(60);
            soft.dequeue();
            soft.dequeue();
            soft.enqueue(70);
            soft.display();
        }

 

}