package recursion;

import java.util.Stack;

public class SortStackByRecursion {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);

        System.out.println("Original Stack: " + stack);
        sortStack(stack);
        System.out.println("Sorted Stack: " + stack);
    }

    private static void sortStack(Stack<Integer> stack) {
        if(stack.isEmpty()){
            return;
        }
        int temp = stack.pop();
        sortStack(stack);
        insert(stack,temp);
    }

    private static void insert(Stack<Integer> stack, int temp){
        if(stack.isEmpty() || stack.peek() <= temp){
            stack.push(temp);
            return;
        }
        int top = stack.pop();
        insert(stack,temp);
        stack.push(top);
    }
}
