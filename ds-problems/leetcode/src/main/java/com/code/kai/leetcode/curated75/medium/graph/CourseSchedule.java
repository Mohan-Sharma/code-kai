package com.code.kai.leetcode.curated75.medium.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Mohan Sharma
 */
public class CourseSchedule {

    Map<Integer, List<Integer>> courseDependencies = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int[] coursePrerequisite : prerequisites) {
            List<Integer> allPrerequisites = courseDependencies.getOrDefault(coursePrerequisite[1], new ArrayList<>());
            allPrerequisites.add(coursePrerequisite[0]);
            courseDependencies.put(coursePrerequisite[1], allPrerequisites);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!canFinishCourse(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean canFinishCourse(int course) {
        if (visited.contains(course))
            return false;
        if (courseDependencies.get(course) ==  null || courseDependencies.get(course).isEmpty())
            return true;
        visited.add(course);
        for (int preCourse : courseDependencies.get(course)) {
            if (!canFinishCourse(preCourse)) {
                return false;
            }
        }
        visited.remove(course);
        courseDependencies.put(course, new ArrayList<>());
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new CourseSchedule().canFinish(2, new int[][] {{0,1}, {1, 0}}));
    }
}
