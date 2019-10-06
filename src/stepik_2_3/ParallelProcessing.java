package stepik_2_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParallelProcessing {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/stepik_2_3/input-parallelprocessing.txt"));
        Heap heap = new Heap(in.nextInt());
        int processCount = in.nextInt();
        for(int i = 0; i < processCount; i++){
            Processor firstFree = heap.getFirstFree();
            System.out.println(String.format("%s %s", firstFree.number, firstFree.time));
            long newTime = in.nextLong();
            if(newTime != 0){
                heap.changeTime(newTime);
            }
        }
    }

    private static class Processor
    {
        private int number;
        private long time;

        private Processor(int number, long time)
        {
            this.number = number;
            this.time = time;
        }
    }

    private static class Heap
    {
        private Processor[] processors;
        private int size;

        private Heap(int size){
            this.size = size;
            processors = new Processor[size];
            for(int i = 0; i < size; i++){
                processors[i] = new Processor(i, 0);
            }
        }

        private void siftDown(int index){
            int smallIndex;
            Processor top = processors[index];
            while (index < size / 2)
            {
                int leftChild = 2 * index + 1;
                int rightChild = leftChild + 1;

                if(rightChild >= size)
                {
                    smallIndex = processors[leftChild].time > processors[index].time ? index : leftChild;
                }
                else if(processors[leftChild].time > processors[rightChild].time)
                {
                    smallIndex = rightChild;
                }
                else if(processors[leftChild].time == processors[rightChild].time)
                {
                    smallIndex = processors[leftChild].number > processors[rightChild].number ? rightChild : leftChild;
                }
                else
                {
                    smallIndex = leftChild;
                }

                if(top.time < processors[smallIndex].time || (top.time == processors[smallIndex].time && top.number <= processors[smallIndex].number))
                {
                    break;
                }

                processors[index] = processors[smallIndex];
                index = smallIndex;
                processors[index] = top;
            }
        }

        private Processor getFirstFree()
        {
            return processors[0];
        }

        private void changeTime(long newTime)
        {
            processors[0].time += newTime;
            siftDown(0);
        }
    }
}
