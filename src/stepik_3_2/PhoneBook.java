package stepik_3_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/stepik_3_2/input_phone-book.txt"));
        int operationCount = in.nextInt();

        String[] table = new String[10000000];

        for(int i = 0; i < operationCount; i++)
        {
            String operation = in.next();
            int number = in.nextInt();
            if(operation.startsWith("a"))
            {
                table[number] = in.next();
            }
            else if(operation.startsWith("f"))
            {
                System.out.println(table[number] == null ? "not found" : table[number]);
            }
            else
            {
                table[number] = null;
            }
        }
    }
}
