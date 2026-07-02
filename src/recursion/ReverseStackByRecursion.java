package recursion;

import java.util.Stack;

public class ReverseStackByRecursion {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println("Original Stack: " + stack);
        reverseStack(stack);
        System.out.println("Reversed Stack: " + stack);
    }

    private static void reverseStack(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int temp = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack,temp);
    }

    private static void insertAtBottom(Stack<Integer> stack , int temp){
        if(stack.isEmpty()){
            stack.push(temp);
            return;
        }
        int top = stack.pop();
        insertAtBottom(stack,temp);
        stack.push(top);
    }


}
