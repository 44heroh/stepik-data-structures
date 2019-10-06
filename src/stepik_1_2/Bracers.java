package stepik_1_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Bracers {
    private static Map<Character, Character> pairBrace;

    static {
        pairBrace = new HashMap<>();
        pairBrace.put(')', '(');
        pairBrace.put(']', '[');
        pairBrace.put('}', '{');
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/stepik_1_2/input-bracers.txt"));
        char[] input = scanner.next().toCharArray();
        Deque<Pair> bracers = new ArrayDeque<>();
        int errorPosition = -1;

        for(int i = 0; i < input.length; i++){
            char inputChar = input[i];
            if(inputChar == '(' || inputChar == '[' || inputChar == '{'){
                bracers.push(new Pair(inputChar, i));
            } else if(inputChar == ')' || inputChar == ']' || inputChar == '}'){
                if(bracers.isEmpty()){
                    errorPosition = i + 1;
                    break;
                } else {
                    if(bracers.peek().character != pairBrace.get(inputChar)){
                        errorPosition = i + 1;
                        break;
                    } else {
                        bracers.pop();
                    }
                }
            }
            if((i + 1 == input.length) && !bracers.isEmpty()){
                errorPosition = bracers.peek().position + 1;
                break;
            }
        }
        if(errorPosition != -1){
            System.out.println(errorPosition);
        } else {
            System.out.println("Success");
        }
    }

    private static class Pair {
        char character;
        int position;

        Pair(char character, int position){
            this.character = character;
            this.position = position;
        }
    }
}
