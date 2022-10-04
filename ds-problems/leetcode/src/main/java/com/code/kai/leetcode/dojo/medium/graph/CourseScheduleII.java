package com.code.kai.leetcode.dojo.medium.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * @author Mohan Sharma
 */
public class CourseScheduleII {

    // Very similar to CourseSchedule
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return takenCourseBFS(numCourses, prerequisites);
    }

    // Solution: Kahn's Topological sort
    private int[] takenCourseBFS(int courses, int[][] prerequisites) {

        int[] result = new int[courses]; int index = 0;
        List<Integer>[] adjMatrix = new List[courses];

        IntStream.range(0, courses).forEach(i -> adjMatrix[i] = new ArrayList<>());
        // create indegree array
        int[] indegree = new int[courses];
        for (int[] prereq : prerequisites) {
            indegree[prereq[0]]++;
            adjMatrix[prereq[1]].add(prereq[0]);
        }


        // populate q with all courses whose indegree is 0 meaning no incoming edges meaning are independent and can be taken first
        Queue<Integer> q = new ArrayDeque<>(courses);
        for (int i = 0; i < courses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                result[index++] = i;
            }
        }

        //empty q and check if dependencies present then for dependency decrement indegree and if indegree 0 add to queue for next processing
        while (!q.isEmpty()) {
            int course = q.poll();
            for (int dependency : adjMatrix[course]) {
                if (--indegree[dependency] == 0) {
                    result[index++] = dependency;
                    q.add(dependency);
                }
            }
        }
        return index == courses ? result : new int[] {};
    }

    public static void main(String[] args) {
        int[] result = new CourseScheduleII().findOrder(2, new int[][] {{0, 1}});
        System.out.println(Arrays.toString(result));
    }
}
