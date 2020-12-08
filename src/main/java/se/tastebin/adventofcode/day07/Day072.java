package se.tastebin.adventofcode.day07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import se.tastebin.adventofcode.ListFromFile;

public class Day072 {

    public static void main(String[] args) {
        List<String> strings = new ListFromFile("src/main/java/se/tastebin/adventofcode/day07/day07.txt").list();
        
        BagsData bags = new BagsData(strings);
        
        List<String> keys = bags.keys();
        
        List<Bag> result = new ArrayList<>();
        
        for (String k : keys) {
            result.add(new Bag(k, bags));
        }
        
        //System.out.println(result);
        
        long count = result.stream().filter(b-> b.hasBag("shiny gold")).count();
        System.out.println(count);
    }
    

   
    private static class BagsData {
        
        private final Map<String, List<String>> bags = new HashMap<>();

        public BagsData(List<String> strings) {
               
            for (String row : strings) {
            
                String[] rowStuff = row.split("bags contain");

                String key = rowStuff[0].trim();

                List<String> other = getOther(rowStuff[1].trim());
                //System.out.println(key);


                bags.put(key, other);
            }
        }
        
        public List<String> get(String name) {
            return bags.get(name);
        }
         
        private List<String> getOther(String stuff) {

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

        private String cleanup(String in) {
            if ("no other bags.".equals(in.trim())) {
                return "";
            } else {
                String[] xx = in.trim().split(" ");
                return String.format("%s %s",xx[1], xx[2]);
            }
        }
        
        public List<String> keys() {
            return bags.keySet().stream().collect(Collectors.toList());
        }
    }
    
    private static class Bag {
        
        private final String name;
        private final List<Bag> content;

        public Bag(String name, BagsData bags) {
            this.name = name;
            
            List<String> other = bags.get(name);
            content = new ArrayList<>();
            for (String s : other) {
                content.add(new Bag(s, bags));
            }

        }
        
        public List<Bag> content() {
            return content;
        }

        public boolean isBag(String name) {
            return this.name.equals(name);
        }
        
        public boolean hasBag(String search) {
            return content.stream().anyMatch(b -> (b.isBag(search) || b.hasBag(search)));
        }
        
        @Override
        public String toString() {
            return name + content;
        }
        
        
        
    }
    
    
    
}
