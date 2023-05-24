import java.util.*;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        
        Map<Integer, List<Integer>> graph = buildGraph(routes);
        Set<Integer> visitedStops = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        visitedStops.add(source);
        
        int numBuses = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            numBuses++;
            
            for (int i = 0; i < size; i++) {
                int currStop = queue.poll();
                
                List<Integer> buses = graph.get(currStop);
                for (int bus : buses) {
                    for (int nextStop : routes[bus]) {
                        if (nextStop == target) {
                            return numBuses;
                        }
                        
                        if (!visitedStops.contains(nextStop)) {
                            visitedStops.add(nextStop);
                            queue.offer(nextStop);
                        }
                    }
                }
            }
        }
        
        return -1;
    }
    
    private Map<Integer, List<Integer>> buildGraph(int[][] routes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                graph.putIfAbsent(stop, new ArrayList<>());
                graph.get(stop).add(i);
            }
        }
        
        return graph;
    }
}
