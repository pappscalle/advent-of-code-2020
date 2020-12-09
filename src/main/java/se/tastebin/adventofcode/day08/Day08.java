package se.tastebin.adventofcode.day08;

import java.util.List;
import se.tastebin.adventofcode.ListFromFile;

public class Day08 {
    public static void main(String[] args) {
        
        List<String> strings = new ListFromFile("src/main/java/se/tastebin/adventofcode/day07/day07-test.txt").list();

        
        
        
    }
    
    
    private static class Row {
        private final String inst;
        private final int value;

        public Row(String row) {
            this(row.split(" ")[0],Integer.parseInt(row.split(" ")[1]));
        }
 
        public Row(String inst, int value) {
            this.inst = inst;
            this.value = value;
        }
        
        
        
    }
}
