package stepik_1_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Scanner;

public class SimulationOfNetworkPackageProcessing {
    private static int time = 0;
    private static int bufferSize;
    private static ArrayDeque<Package> buffer;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/stepik_1_2/input-network-package.txt"));
        bufferSize = scanner.nextInt();
        buffer = new ArrayDeque<>(bufferSize);
        int packageCount = scanner.nextInt();
        
        if(packageCount == 0){
            System.out.println("");
        } else if(packageCount == 1){
            System.out.println(scanner.nextInt());
        } else {
            for(int i = 0; i < packageCount; i++){
                process(new Package(scanner.nextInt(), scanner.nextInt()));
            }
            scanner.close();
        }
    }

    private static void process(Package pack) {

        if(buffer.size() < bufferSize){
            System.out.println("pack = " + pack.toString());
            System.out.println("if");
            System.out.println(Math.max(time, pack.average));

            if(pack.average <= time){
                time = pack.getEndTime();
            } else {
                time += pack.duration;
            }
            pack.end = time;
            buffer.add(pack);
        } else if(pack.average >= buffer.getFirst().end){
            System.out.println("pack = " + pack.toString());
            System.out.println("else if");
            System.out.println(Math.max(time, pack.average));
            if(time >= buffer.getLast().end){
                time += pack.duration;
            } else {
                time = buffer.getLast().end + pack.duration;
            }
            buffer.removeFirst();
            pack.end = time;
            buffer.add(pack);
        } else {
            System.out.println("-1");
        }
    }

    private static class Package{
        int average;
        int duration;
        int end;
        
        Package(int average, int duration){
            this.average = average;
            this.duration = duration;
        }

        int getEndTime()
        {
            return average + duration;
        }

        @Override
        public String toString() {
            return "average = " + this.average + " duration = " + this.duration + " end = " + this.end;
        }
    }
}
