package Week4A;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
class Solution {
    
    int maxDiff = Integer.MIN_VALUE;
    public int minimumEffortPath(int[][] heights) 
    {
        int r = heights.length;
        int c = heights[0].length;
     
        PriorityQueue<Point> q = new PriorityQueue<Point>((a,b) -> a.diff - b.diff);
        q.add(new Point(0,0,heights[0][0],0));
        Set<String> visited = new HashSet<>();
        int[][] dirs = {{0,1}, {1, 0}, {-1, 0}, {0, -1}};
        
        while(!q.isEmpty())
        {
            Point curr = q.poll();
            maxDiff = Math.max(maxDiff, curr.diff);
            if(curr.x == r-1 && curr.y == c-1) return maxDiff;
            
            visited.add(getKey(curr.x,curr.y));
            for(int[] d : dirs)
            {
                int nextX = d[0] + curr.x;
                int nextY = d[1] + curr.y;
                String key = getKey(nextX,nextY);
                    
                if(nextX < 0 || nextX >= r || nextY < 0 || nextY >= c || visited.contains(key)) continue;
                
                int nextDiff = Math.abs(heights[nextX][nextY]-curr.h);
                q.add(new Point(nextX, nextY, heights[nextX][nextY], nextDiff));
            }
        }
        
        return -1;
    }
    public int maximumEffortPath(int[][] heights) 
    {
        int r = heights.length;
        int c = heights[0].length;
     
        PriorityQueue<Point> q = new PriorityQueue<Point>((a,b) -> b.diff - a.diff);
        q.add(new Point(0,0,heights[0][0],0));
        Set<String> visited = new HashSet<>();
        int[][] dirs = {{0,1}, {1, 0}, {-1, 0}, {0, -1}};
        
        while(!q.isEmpty())
        {
            Point curr = q.poll();
            maxDiff = Math.max(maxDiff, curr.diff);
            if(curr.x == r-1 && curr.y == c-1) return maxDiff;
            
            visited.add(getKey(curr.x,curr.y));
            for(int[] d : dirs)
            {
                int nextX = d[0] + curr.x;
                int nextY = d[1] + curr.y;
                String key = getKey(nextX,nextY);
                    
                if(nextX < 0 || nextX >= r || nextY < 0 || nextY >= c || visited.contains(key)) continue;
                
                int nextDiff = Math.abs(heights[nextX][nextY]-curr.h);
                q.add(new Point(nextX, nextY, heights[nextX][nextY], nextDiff));
            }
        }
        
        return -1;
    }
    
    private String getKey(int x, int y)
    {
        return x + "->" + y;
    }
                                                     
    class Point
    {
        int x;
        int y;
        int h;
        int diff;
        
        public Point(int x, int y, int h, int diff)
        {
            this.x = x;
            this.y = y;
            this.h = h;
            this.diff = diff;
        }
    }
 
    
    public static void main(String [] args) {
        
        int heights [][] = {{1,3,8,9},{8,7,2,6},{13,3,6,4},{13,1,5,3}};
        Solution s=new Solution();
        int a=s.minimumEffortPath(heights);
        int b=s.maximumEffortPath(heights);
        System.out.println("absolute minimum effort:  "+ a);
        System.out.println("absolute maximum effort: "+ b);
        System.out.println("efforts  absolute differences: "+ (b-a));
        
        
    }
}