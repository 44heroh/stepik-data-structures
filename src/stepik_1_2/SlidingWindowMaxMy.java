package stepik_1_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class SlidingWindowMaxMy {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/stepik_1_2/input-sinding-windows-max.txt"));

        int arrSize = in.nextInt();
        int[] arr = new int[arrSize];
        for(int i = 0; i < arrSize; i++){
            arr[i] = in.nextInt();
        }
        int window = in.nextInt();
        in.close();
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 0; i < arrSize; i++){
            if(i >= window){
                int x = deque.peekFirst();
                System.out.println(x);
                if(x == arr[i - window]){
                    deque.pollFirst();
                }
            }

            while (!deque.isEmpty() && deque.peekLast() < arr[i]){
                deque.pollLast();
            }
            deque.addLast(arr[i]);
        }

        if(!deque.isEmpty() && arrSize >= window){
            System.out.println(deque.peekFirst());
        }
    }
}
