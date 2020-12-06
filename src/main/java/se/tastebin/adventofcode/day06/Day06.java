package se.tastebin.adventofcode.day06;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import se.tastebin.adventofcode.ConcatenatedStringsFromFile;
import se.tastebin.adventofcode.GroupedStringsFromFile;

public class Day06 {
    
    public static void main(String[] args) {
        
        List<String> strings = new ConcatenatedStringsFromFile("src/main/java/se/tastebin/adventofcode/day06/day06.txt", "").list();
        
        long sum1 = strings.stream().mapToLong(s -> s.chars().distinct().count()).sum();
        System.out.println(String.format("sum #1: %d", sum1));
        
        
        List<List<String>> groups = new GroupedStringsFromFile("src/main/java/se/tastebin/adventofcode/day06/day06.txt").list();

        long sum2 = 0;
        
        for (List<String> group : groups) {

           long count = 0; 
            
           String input = merged(group); 
           String unique = input.chars().distinct().mapToObj(c->String.valueOf((char)c)).collect(Collectors.joining());
           
           char[] arr = unique.toCharArray();
           for (char c : arr) {
               if (frequency(input, c) == group.size()) {
                   count++;
               }
           }
            
           sum2 = sum2 + count;

        }
                
        System.out.println(String.format("sum #2: %d", sum2));
    }
    
    private static String merged(List<String> strings) {
        return strings.stream().reduce((a, b)-> a+b).get();                    
    }
    
    private static long frequency(String str, char ch) {
        return str.chars().filter(c-> c == ch).count();
    }
}
