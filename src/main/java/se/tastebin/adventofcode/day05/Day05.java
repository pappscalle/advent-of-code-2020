package se.tastebin.adventofcode.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import se.tastebin.adventofcode.ListFromFile;

public class Day05 {
    
    public static void main(String[] args) {
        
        List<String> strings = new ListFromFile("src/main/java/se/tastebin/adventofcode/day05/day05.txt").list();
        
        List<Integer> seats = new ArrayList<>();
        
        for (String code : strings) {
            
            String r = code.trim().substring(0, 7).replace("B", "1").replace("F", "0");
            String c = code.trim().substring(code.length()-3).replace("R", "1").replace("L", "0");
            
            int row = Integer.parseInt(r, 2);
            int column = Integer.parseInt(c, 2);
            
            seats.add(row *8 + column);
            
            //System.out.println(String.format("row %d, column %d, seat ID %d", row, column, row *8 + column));
        }
        Integer max = seats.stream().mapToInt(v -> v).max().orElse(0);
        
        System.out.println(max);
        
       List<Integer> sorted = seats.stream().sorted().collect(Collectors.toList());
       System.out.println(sorted);
       
       int num = sorted.get(0);
       for (int current : sorted) {
           if (current != num) {
              System.out.println(String.format("num %d", num)); 
              break;
           }
           num++;
       }
       
  
       sorted.stream().reduce((n1, n2) -> {
           if ((n2 - n1) > 1) {
               System.out.println(""+n1);
           }
           return n2;
       });
       
    }
    
}
