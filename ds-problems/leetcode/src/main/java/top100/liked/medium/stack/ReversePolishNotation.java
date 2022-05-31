package top100.liked.medium.stack;

import java.util.List;
import java.util.Stack;

/**
 * @author Mohan Sharma
 */
public class ReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        List<String> operators = List.of("+", "-", "*","/");
        for (String token : tokens) {
            if (operators.contains(token)) {
                int secondOpr = stack.pop();
                int firstOpr = stack.pop();
                if (token.equals("+"))
                    stack.push(firstOpr + secondOpr);
                else if (token.equals("-"))
                    stack.push(firstOpr - secondOpr);
                else if (token.equals("*"))
                    stack.push(firstOpr * secondOpr);
                else if (token.equals("/"))
                    stack.push(firstOpr / secondOpr);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(new ReversePolishNotation().evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
    }
}
