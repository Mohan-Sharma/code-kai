package top100.liked.easy.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class MinStackUsingFormula {

    List<Integer> list = new ArrayList<>();
    int min;
    public MinStackUsingFormula() {}

    public void push(int val) {
        if (list.isEmpty()) {
            list.add(val);
            min = val;
        } else {
            if (val < min) {
                list.add(2 * val - min);
                min = val;
            } else
                list.add(val);
        }
    }

    public void pop() {
        if (list.isEmpty())
            throw new IllegalStateException("Stack is empty.");
        int top = list.get(list.size() - 1);
        if (top < min) {
            min = 2 * min - top;
        }
        list.remove(list.size() - 1);
    }

    public int top() {
        if (list.isEmpty())
            throw new IllegalStateException("Stack is empty.");
        int top = list.get(list.size() - 1);
        if (top < min)
            return min;
        else return top;
    }

    public int getMin() {
        return min;
    }
}
