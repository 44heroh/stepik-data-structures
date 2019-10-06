package stepik_2_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MinimalHeap {
    private static int size;
    private static int[] heap;
    private  static int swapCounter = 0;
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/stepik_2_3/input-minheap.txt"));
        size = in.nextInt();
        heap = new int[size];
        for (int i = 0; i < size; i++){
            heap[i] = in.nextInt();
        }
        fixHear();

        System.out.println(swapCounter);
        if (swapCounter != 0) {
            System.out.println(stringBuilder.toString());
        }
    }

    private static void fixHear() {
        for(int i = size / 2 - 1; i >= 0; i--){
            siftDown(i);
        }
    }

    private static void siftDown(int index) {
        int smallestIndex;
        int top = heap[index];
        while (index < size / 2){
            int leftChild = 2 * index + 1;
            int rightchild = leftChild + 1;

            if(rightchild >= size){
                smallestIndex = heap[leftChild] > heap[index] ? index : leftChild;
            } else if(heap[leftChild] > heap[rightchild]){
                smallestIndex = rightchild;
            } else {
                smallestIndex = leftChild;
            }

            if(top <= heap[smallestIndex]){
                break;
            }

            swapCounter++;
            stringBuilder.append(String.format("%s %s %n", index, smallestIndex));
            System.out.println(Arrays.toString(heap));
            heap[index] = heap[smallestIndex];
            index = smallestIndex;
            heap[index] = top;
            System.out.println(Arrays.toString(heap));
        }
    }
}
