package Heap.Q218TheSkylineProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//Use max heap to keep track of the biggest number for now.
//When the biggest number in the heap changes, add that point in the result.
public class HeapSolution {
	public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for (int[] b : buildings){
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }
        
        Collections.sort(height, (a,b) -> {
            if (a[0] != b[0]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        
        Queue<Integer> pq = new PriorityQueue<>((a,b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for (int[] h : height){
            if (h[1] < 0){
                pq.offer(-h[1]);
            }else {
                pq.remove(h[1]);
            }
            int cur = pq.peek();
            if (prev != cur) {
                result.add(new int[]{h[0],cur});
                prev = cur;
            }
        }
        return result;
    }
}
