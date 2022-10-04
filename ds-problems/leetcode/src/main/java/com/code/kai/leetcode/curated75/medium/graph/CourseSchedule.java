package com.code.kai.leetcode.curated75.medium.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Mohan Sharma
 */
public class CourseSchedule {

    // Problem: Basically problem courses with number from 0 to n-1 and dependencies among the numbered courses and
    // ask if all 0 to n-1 can be taken or not.
    // Solution: Topological Sort: 0 to n-1 taken or not? mean look from 0 .. n-1 and check one by one. Now for each numbered course,
    // are dependent on other courses meaning we need a dependent map<Dependent, Dependencies>. Now start for each number
    // course and check if it can be taken. It can be taken only if all the dependencies can be taken and so on..
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //return canFinishCourseDFS(prerequisites);
        return canFinishCourseBFS(numCourses, prerequisites);
    }

    private boolean canFinishCourseDFS(int[][] prerequisites) {
        // creating the map<Dependent, Dependencies>
        Map<Integer, List<Integer>> courseDependencies = new HashMap<>();
        for (int[] coursePrerequisite : prerequisites) {
            List<Integer> allPrerequisites = courseDependencies.getOrDefault(coursePrerequisite[1], new ArrayList<>());
            allPrerequisites.add(coursePrerequisite[0]);
            courseDependencies.put(coursePrerequisite[1], allPrerequisites);
        }

        Set<Integer> visited = new HashSet<>();
        // check each course from 0 .. n -1 if can be taken or the only one's provided as input
        // since others are not having dependencies(not provided) automatically can be taken
        for (int i : courseDependencies.keySet()) {
            if (!canFinishCourse(i, visited, courseDependencies)) {
                return false;
            }
        }
        return true;
    }

    private boolean canFinishCourse(int course, Set<Integer> assumeCourseTaken, Map<Integer, List<Integer>> courseDependencies) {
        // course cannot be taken since cyclic dependency may be e.g. [1, 3] and [3, 1]
        if (assumeCourseTaken.contains(course))
            return false;
        // while checking a course, there are no dependencies so yes it can be taken
        if (courseDependencies.get(course) ==  null || courseDependencies.get(course).isEmpty())
            return true;
        // just assuming this course can be taken so marking as taken then go to dependencies to check if dependencies can be taken
        assumeCourseTaken.add(course);
        for (int preCourse : courseDependencies.get(course)) {
            if (!canFinishCourse(preCourse, assumeCourseTaken, courseDependencies)) {
                return false;
            }
        }
        // all dependencies could be taken, so assumption was true hence un-marking the course and removing from map(processing complete)
        assumeCourseTaken.remove(course);
        courseDependencies.put(course, new ArrayList<>());
        return true;
    }

    // Solution: Kahn's Topological sort
    private boolean canFinishCourseBFS(int courses, int[][] prerequisites) {

        // create adjacency matrix
        List<List<Integer>> adjMatrix = new ArrayList<>(courses);
        for (int i = 0; i < courses; i++) {
            adjMatrix.add(new ArrayList<>(courses));
        }

        // create indegree array
        int[] indegree = new int[courses];
        for (int[] prereq : prerequisites) {
            indegree[prereq[0]]++;
            adjMatrix.get(prereq[1]).add(prereq[0]);
        }

        // populate q with all courses whose indegree is 0 meaning no incoming edges meaning are independent and can be taken first
        Queue<Integer> q = new ArrayDeque<>(courses);
        for (int i = 0; i < courses; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        int takenCourses = 0;

        //empty q and check if dependencies present then for dependency decrement indegree and if indegree 0 add to queue for next processing
        while (!q.isEmpty()) {
            int course = q.poll();
            takenCourses++;

            for (int dependency : adjMatrix.get(course)) {
                if (--indegree[dependency] == 0)
                    q.add(dependency);
            }
        }

        return takenCourses == courses;
    }

    public static void main(String[] args) {
        System.out.println(new CourseSchedule().canFinish(8, new int[][] {{1,0},{2,6},{1,7},{6,4},{7,0},{0,5}}));
    }
}
