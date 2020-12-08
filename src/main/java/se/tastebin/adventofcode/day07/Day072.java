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
            result.add(new Bag(k, 0, bags));
        }
        
        //System.out.println(result);
        
        long count = result.stream().filter(b-> b.hasBag("shiny gold")).count();
        System.out.println(count);
        
        Bag gold = new Bag("shiny gold", 1, bags);
        System.out.println(gold);
         System.out.println(gold.count());
    }
    

   
    private static class BagsData {
        
        private final Map<String, List<B2>> bags = new HashMap<>();

        public BagsData(List<String> strings) {
               
            for (String row : strings) {
            
                String[] rowStuff = row.split("bags contain");

                String key = rowStuff[0].trim();

                List<B2> other = getOther(rowStuff[1].trim());
                //System.out.println(key);


                bags.put(key, other);
            }
        }
        
        public List<B2> get(String name) {
            return bags.get(name);
        }
         
        private List<B2> getOther(String stuff) {

            String[] xs = stuff.split(",");

            List<B2> result = new ArrayList<>();
            for (String x : xs) {
                B2 cleaned = cleanup(x);
                if (!cleaned.isEmpty()) {
                    result.add(cleaned);
                }
            }
            return result;
        }

        private B2 cleanup(String in) {
            if ("no other bags.".equals(in.trim())) {
                return new B2("", 0);
            } else {
                String[] xx = in.trim().split(" ");
                int val = Integer.parseInt(xx[0]);
                return new B2(String.format("%s %s",xx[1], xx[2]), val);
            }
        }
        
        public List<String> keys() {
            return bags.keySet().stream().collect(Collectors.toList());
        }
    }
    
    private static class B2 {
        private final String name;
        private final int amount;

        public B2(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }
        
        public String name() {
            return name;
        }
        
        public int amount() {
            return amount;
        }
        
        public boolean isEmpty() {
           return "".equals(name) && amount == 0;
        }
    }
    
    private static class Bag {
        
        private final String name;
        private final int value;
        private final List<Bag> content;

        public Bag(String name, int value, BagsData bags) {
            this.name = name;
            this.value = value;
            
            List<B2> other = bags.get(name);
            content = new ArrayList<>();
            for (B2 s : other) {
                content.add(new Bag(s.name(), s.amount(), bags));
            }

        }
        
        public List<Bag> content() {
            return content;
        }
        
        public int value() {
            return value;
        }

        public boolean isBag(String name) {
            return this.name.equals(name);
        }
        
        public boolean hasBag(String search) {
            return content.stream().anyMatch(b -> (b.isBag(search) || b.hasBag(search)));
        }
        
        public long count() {
           long total = 0;
           
           for (Bag b : content) {
               total = total + b.value() + b.count();
           }
            
           return value * total;
        }
        
        @Override
        public String toString() {
            return name+" "+value+ " " + content;
        }
        
        
        
    }
    
    
    
}
