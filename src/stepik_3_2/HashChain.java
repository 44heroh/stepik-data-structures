package stepik_3_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class HashChain {
    private static final int DIVIDER = 1_000_000_007;
    private static final int BASE = 263;

    public static void main(String[] args) {
        try(Scanner in = new Scanner(new File("src/stepik_3_2/input_hash-chain.txt")))
        {
            HashTable hashTable = new HashTable(in.nextInt());
            int operationCount = in.nextInt();
            for(int i = 0; i < operationCount; i++)
            {
                String operation = in.next();
                if(operation.startsWith("a"))
                {
                    hashTable.add(in.next());
                }
                else if (operation.startsWith("c"))
                {
                    System.out.println(hashTable.getValues(in.nextInt()));
                }
                else if (operation.startsWith("f"))
                {
                    System.out.println(hashTable.find(in.next()));
                }
                else
                {
                    hashTable.delete(in.next());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class HashTable
    {
        Words[] table;

        private HashTable(int size)
        {
            this.table = new Words[size];
        }

        private long pow(int pow)
        {
            long result = 1;
            for (int i = 0; i < pow; i++)
            {
                result = (result * BASE) % DIVIDER;
            }

            return result;
        }

        private int hashCode(String str)
        {
            long hashCode = 0;
            int i = 0;
            for(char ch : str.toCharArray())
            {
                hashCode = (((hashCode + (ch * pow(i))) % DIVIDER) + DIVIDER ) % DIVIDER;
                i++;
            }

            return (int) (hashCode % table.length);
        }

        private void add(String str)
        {
            int hashCode = hashCode(str);
            if(table[hashCode] == null)
            {
                table[hashCode] = new Words();
            }
            table[hashCode].add(str);
        }

        private String find(String str)
        {
            int hashCode = hashCode(str);
            if(table[hashCode] == null || table[hashCode].isEmpty())
            {
                return "no";
            }
            else
            {
                if(table[hashCode].contains(str))
                {
                    return "yes";
                }
                else
                {
                    return "no";
                }
            }
        }

        private void delete(String str)
        {
            int hashCode = hashCode(str);
            if(table[hashCode] != null && !table[hashCode].isEmpty())
            {
                table[hashCode].delete(str);
            }
        }

        private String getValues(int hashCode)
        {
            if (table[hashCode] == null || table[hashCode].isEmpty())
            {
                return "";
            }
            else
            {
                return table[hashCode].getValues();
            }
        }

        private class Words {
            private LinkedList<String> wordsList;

            private Words(){
                wordsList = new LinkedList<>();
            }

            private void add(String str)
            {
                if(!contains(str))
                {
                    wordsList.addFirst(str);
                }
            }

            private boolean contains(String str)
            {
                boolean contains = false;
                for(String string : wordsList)
                {
                    if (str.equals(string))
                    {
                        contains = true;
                    }
                }

                return contains;
            }

            private void delete(String string)
            {
                Iterator<String> iterator = wordsList.iterator();
                while (iterator.hasNext())
                {
                    if (iterator.next().equals(string))
                    {
                        iterator.remove();
                        break;
                    }
                }
            }

            private String getValues()
            {
                StringBuilder stringBuilder = new StringBuilder();
                for (String aWordsList : wordsList)
                {
                    stringBuilder.append(aWordsList);
                    stringBuilder.append(" ");
                }

                return stringBuilder.toString();
            }

            private boolean isEmpty()
            {
                return wordsList.isEmpty();
            }
        }
    }
}
