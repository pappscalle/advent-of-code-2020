package se.tastebin.adventofcode.day08;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import se.tastebin.adventofcode.ListFromFile;

public class Day08 {
    public static void main(String[] args) {
        
        List<String> strings = new ListFromFile("src/main/java/se/tastebin/adventofcode/day08/day08.txt").list();
        int current = 0;
        int loop = 0;
        while (current < strings.size()) {

            loop++;
            current = 0;
            int accumulator = 0;
            int steps = 0;
            Set<Integer> visited = new HashSet<>();
            while (!visited.contains(current)){     
               steps++; 
                
               visited.add(current);
               Row row = new Row(strings.get(current));
               String instr = row.instruction();
               int value = row.value();
               if ("jmp".equals(instr) && loop == steps) {
                   instr = "nop";
                   
               }
               switch (instr) {
                   case "nop": current++; break;
                   case "acc": accumulator+=value; current++; break; 
                   case "jmp": current+=value; break;
               }
               System.out.println(String.format("%d: %s %d current: %d acc: %d", steps, instr, value, current, accumulator));
            }


        }

    }
    private static class Row {
        private final String row;
        
 
        public Row(String row) {
            this.row = row;
        }
        
        public String instruction() {
            return row.split(" ")[0];
        }
        
        public int value() {
            return Integer.parseInt(row.split(" ")[1]);
        }
        
    }
}
