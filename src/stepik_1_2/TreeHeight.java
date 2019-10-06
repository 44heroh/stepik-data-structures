package stepik_1_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TreeHeight {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/stepik_1_2/input-tree.txt"));
        int root = -1;
        int[] nodes = new int[scanner.nextInt()];
        for(int i = 0; i < nodes.length; i++){
            int node = scanner.nextInt();
            root = node == -1 ? i : root;
            nodes[i] = node;
        }
        scanner.close();

        int maxHeight = 0;
        for(int node : nodes){
            int parent = node;
            int height = 1;
            while(true){
                if(parent != -1){
                    height++;
                    parent = nodes[parent];
                } else {
                    maxHeight = maxHeight > height ? maxHeight : height;
                    break;
                }
            }
        }

        System.out.println(maxHeight);
    }
}
