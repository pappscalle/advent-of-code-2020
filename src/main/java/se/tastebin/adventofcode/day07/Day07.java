package se.tastebin.adventofcode.day07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import se.tastebin.adventofcode.ListFromFile;

public class Day07 {

    public static void main(String[] args) {
        List<String> strings = new ListFromFile("src/main/java/se/tastebin/adventofcode/day07/day07-test.txt").list();

        Map<String, List<String>> bags = new HashMap<>();
        
        for (String row : strings) {
            
            String[] rowStuff = row.split("bags contain");
            
            String key = rowStuff[0];
            
            List<String> other = getOther(rowStuff[1]);
            //System.out.println(key);
            
            
            bags.put(key, other);
        }
        
        
        System.out.println(bags);
        
        Set<String> keys = bags.keySet();
        
        for (String key : keys) {
        }
        
        
    }

    
    
    private final static List<String> getOther(String stuff) {
        
        String[] xs = stuff.split(",");
      
        List<String> result = new ArrayList<>();
        for (String x : xs) {
            String cleaned = cleanup(x);
            if (!cleaned.isEmpty()) {
                result.add(cleaned);
            }
        }
        return result;
    }
    
    private final static String cleanup(String in) {
        if ("no other bags.".equals(in.trim())) {
            return "";
        } else {
            String[] xx = in.trim().split(" ");
            return String.format("%s %s",xx[1], xx[2]);
        }
    }
    
    private static class Lookup {
        
        Map<String, List<String>> bags;

        public Lookup(Map<String, List<String>> bags) {
            this.bags = bags;
        }
        
        boolean has(String value) {
            List<String> other = bags.get(value);
            if (other.isEmpty()) {
                return false;
            } else if (other.contains("shiny gold")) {
                return true;
            } else {
                for (String s : other) {
                    
                }
            }
        } 
        
    }
    
    
//    private static class Bag {
//        
//        private final String name;
//        private final List<String> content;
//
//        public Bag(String name, List<String> content) {
//            this.name = name;
//            this.content = content;
//        }
//        
//        
//        
//    }
    
    
    
}
