package stepik_1_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class SimulationOfNetworkPackageProcessingMy {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/stepik_1_2/input-network-package.txt"));

        int size = in.nextInt();
        int bufferSize = in.nextInt();
        int cpuTime = 0;

        Queue<Integer> queue = new LinkedBlockingQueue<>(size);

        for(int i = 0; i < bufferSize; i++){
            int arrival = in.nextInt();
            int duration = in.nextInt();

            while (!queue.isEmpty() && queue.peek() <= arrival){
                queue.toString();
                queue.poll();
            }

            if(cpuTime < arrival){
                System.out.println(arrival);
                cpuTime += arrival + duration;
                queue.add(cpuTime);
            } else if(queue.size() < size){
                System.out.println(cpuTime);
                cpuTime += duration;
                queue.add(cpuTime);
            } else {
                System.out.println("-1");
            }
        }
    }
}
