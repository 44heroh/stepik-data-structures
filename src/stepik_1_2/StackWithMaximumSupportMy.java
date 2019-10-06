package stepik_1_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class StackWithMaximumSupportMy {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/stepik_1_2/input-stack-with-max.txt"));
        int requestCount = in.nextInt();
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> maxStack = new Stack<>();
        StringBuilder resString = new StringBuilder();
        for(int i = 0; i <= requestCount; i++){
            String request = in.nextLine();
            if(request.contains("push")){
                int value = Integer.parseInt(request.split(" ")[1]);
                stack.push(value);
                maxStack.push(Math.max(maxStack.isEmpty() ? 0 : maxStack.peek(), value));
                /*stack.add(Integer.parseInt(request.split(" ")[1]));
                if(maxStack.isEmpty() || maxStack.peek() < stack.peek()){
                    maxStack.push(stack.peek());
                } else {
                    maxStack.push(maxStack.peek());
                }*/
            } else if(request.contains("max")) {
                /*System.out.println("max = " + (maxStack.isEmpty() ? 0 : maxStack.peek()));*/
                resString.append(maxStack.isEmpty() ? 0 : maxStack.peek());
                resString.append("\n");
            } else if(request.contains("pop")) {
                stack.pop();
                maxStack.pop();
            }
        }
        System.out.println(resString);
    }
}
