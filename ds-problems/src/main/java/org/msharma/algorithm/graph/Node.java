package org.msharma.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Mohan Sharma
 */
@EqualsAndHashCode(of = "data")
@NoArgsConstructor
public class Node<T> {
    private T data;
    private List<Node<T>> children = new ArrayList<>();
    private boolean visited;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
