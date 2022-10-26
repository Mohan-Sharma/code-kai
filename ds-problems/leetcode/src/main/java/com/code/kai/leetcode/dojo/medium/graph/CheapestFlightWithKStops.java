package com.code.kai.leetcode.dojo.medium.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * @author Mohan Sharma
 */
/*
Another shortest Path Algo: Bellman Ford.
Bellman-Ford algorithm is a single-source shortest path algorithm, so when you have negative edge weight
then it can detect negative cycles in a graph.
The only difference between the two is that Bellman-Ford is also capable of handling negative weights
whereas Dijkstra Algorithm can only handle positives.

distance[0, +inf, +inf, +inf ...]
Algo:
    1. Relax/loop using below logic n-1 times
    2. Loop over all edges:
        if(distance[src] + weight < distance[dest])
            distance[dest] = distance[src] + weight

After n-1 relaxation we will have the ans distance array i.e. the shortest distance possible. According to Bellman Ford
if after n-1 relaxation if we do one more relaxation and the distance reduces than there is a -ve cycle.
Why n-1 relaxation, suppose we have n nodes so n-1 edges. a->b b->c c->d d->e. Initially distance array is like [0, +inf, +inf, +inf, +inf].
If we apply the formula on first relaxation only the first 1st index gets updated and rest are skipped similarly on 2nd relaxation 2nd index
gets update and so on.. at n-1 relaxation all gets updated with shorted distance
 */
public class CheapestFlightWithKStops {

    /*
    Using Bellman Ford instead of Dijkstra why b/c it is little difficult to find shortest path taking k edges into considering while
    in Bellman we know that after n-1 relaxation we would obtain the shorest path distance. In this problem it would be k + 1
    since k would represent nodes in between src and dest. When we have 5 nodes we would do 4 relaxation. In this case there will
    suppose there are k = 1 nodes between source and dest means total is 3 nodes we would relax for 2 times(n-1 times) so it would be k+1
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.MAX_VALUE;
        }
        prices[src] = 0;
        for (int i = 0; i <= k + 1; i++) {
            int[] tempPrices = Arrays.copyOf(prices, prices.length);
            for (int[] flight: flights) {
                if (prices[flight[0]] == Integer.MAX_VALUE)
                    continue;
                if (prices[flight[0]] + flight[2] < tempPrices[flight[1]])
                    tempPrices[flight[1]] = prices[flight[0]] + flight[2];
            }
            prices = tempPrices;
        }
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }

    public int findCheapestPriceDijkstra(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> adjMatrix = createAdjMatrix(n, flights);
        Queue<int[]> q = new ArrayDeque<>();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.MAX_VALUE;
        }
        prices[src] = 0;
        q.add(new int[] {0, src, 0});

        while (!q.isEmpty()) {
            int[] nextDest = q.poll();
            int stopCount = nextDest[0];
            int stopNode = nextDest[1];
            int price = nextDest[2];
            if (stopCount <= k + 1 && price <= prices[stopNode]) {
                prices[stopNode] = price;
                if (stopNode != dst) {
                    for (int[] neighbor : adjMatrix.get(stopNode)) {
                        q.add(new int[]{stopCount + 1, neighbor[0], price + neighbor[1]});
                    }
                }
            }
        }

        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }

    private List<List<int[]>> createAdjMatrix(int n, int[][] flights) {
        List<List<int[]>> ajdMatrix = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ajdMatrix.add(i, new ArrayList<>());
        }

        for (int i = 0; i < flights.length; i++) {
            ajdMatrix.get(flights[i][0]).add(new int[]{ flights[i][1], flights[i][2] });
        }
        return ajdMatrix;
    }

    //{{0, 1, 5}, {0, 3, 2},{1, 2, 5},{1, 4, 1},{3, 1, 2},{4, 2, 1}}
    public static void main(String[] args) {
        System.out.println(new CheapestFlightWithKStops().findCheapestPriceDijkstra(5, new int[][]{{0, 1, 5}, {0, 3, 2},{1, 2, 5},{1, 4, 1},{3, 1, 2},{4, 2, 1}}, 0, 2, 2));
    }
}
