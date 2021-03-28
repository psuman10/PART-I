package Week7A;

import java.util.*;

import java.util.Random;
import java.util.Scanner;


public class main {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Number of Coordinates:");
		int maxCoordinate = sc.nextInt();
		
	
		
		Coordinate point[] = new Coordinate[maxCoordinate];
		
		Random rand = new Random();
		
		for( int i=0;i<maxCoordinate; i++) {
			int x = rand.nextInt(10);
			int y = rand.nextInt(10);
			
			point[i] = new Coordinate(x,y);
		}
		
		
//		point[0]= new coordinate(5,5);
//		point[1]= new coordinate(4,3);
//		point[2]= new coordinate(2,1);
//		point[3]= new coordinate(3,2);
//		point[4]= new coordinate(0,3);
//		point[5]= new coordinate(3,4);
//		point[6]= new coordinate(0,0);
//		point[0]= new coordinate(5,5);
		
		JarvisMarchAlgorithm gfw = new JarvisMarchAlgorithm();
		gfw.convexhall(point);
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time::" +(endTime-startTime)+ " ms");
	}

}
