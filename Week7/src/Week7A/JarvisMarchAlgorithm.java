package Week7A;
import java.util.Stack;
public class JarvisMarchAlgorithm {
	
	
	public int direction(Coordinate p, Coordinate q, Coordinate r) {
		int area = (q.x-p.x)*(r.y-q.y)-(q.y-p.y)*(r.x-q.x);
		return area;
		
	}
	
	public void convexhall(Coordinate point[]) {
		Stack<Coordinate> stack = new Stack<Coordinate>();
		int left = 1 ; 
		int n = point.length;
		for(int i=1; i<n; i++) {
			
			if(point[i].x<point[left].x) {
				left = i;
			}
		}
		int p = left;
		do {
			stack.add(point[p]);
			int q = (p+1)%n;
			for(int r=0; r<n; r++) {
				if(direction(point[p], point[q], point[r])>0){
					q=r;
				}
			}
			p=q;

		}
		while(p!=left); 
			stack.add(point[p]);
			
		for(Coordinate i: stack) {
			System.out.println("\nconvexhall point("+i.x+","+i.y+")");
		}
		
		
	}

	
	

}
