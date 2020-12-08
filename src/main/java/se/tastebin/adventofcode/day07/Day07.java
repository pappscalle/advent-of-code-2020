package se.tastebin.adventofcode.day07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import se.tastebin.adventofcode.ListFromFile;

public class Day07 {

    public static void main(String[] args) {
        List<String> strings = new ListFromFile("src/main/java/se/tastebin/adventofcode/day07/day07-test.txt").list();
        
        Rules rules = new Rules(strings);
       
        List<String> keys = rules.keys();
        
        List<Bag> result = new ArrayList<>();
        
        for (String k : keys) {
            result.add(new Bag(k, 0, rules));
        }
        
        // #1
        
        long count = result.stream().filter(b-> b.hasBag("shiny gold")).count();
        System.out.println(String.format("Number of bag colors: %d", count));
        
        // #2
        
        Bag gold = new Bag("shiny gold", 1, rules);
        System.out.println(gold);
        System.out.println(String.format("Number of bags: %d ", gold.count()));
    }
    

   
    private static class Rules {
        
        private final Map<String, List<BagWithAmount>> bags = new HashMap<>();

        public Rules(List<String> strings) {
               
            for (String row : strings) {
            
                String[] rowStuff = row.split("bags contain");

                String key = rowStuff[0].trim();

                List<BagWithAmount> other = getOther(rowStuff[1].trim());

                bags.put(key, other);
            }
        }
        
        public List<BagWithAmount> get(String name) {
            return bags.get(name);
        }
         
        public List<String> keys() {
            return bags.keySet().stream().collect(Collectors.toList());
        }
        
        private List<BagWithAmount> getOther(String stuff) {

            String[] xs = stuff.split(",");

            List<BagWithAmount> result = new ArrayList<>();
            for (String x : xs) {
                BagWithAmount cleaned = cleanup(x);
                if (!cleaned.isEmpty()) {
                    result.add(cleaned);
                }
            }
            return result;
        }

        private BagWithAmount cleanup(String in) {
            if ("no other bags.".equals(in.trim())) {
                return new BagWithAmount("", 0);
            } else {
                String[] xx = in.trim().split(" ");
                int val = Integer.parseInt(xx[0]);
                return new BagWithAmount(String.format("%s %s",xx[1], xx[2]), val);
            }
        }
        

    }
    
    private static class BagWithAmount {
        private final String name;
        private final int amount;

        public BagWithAmount(String name, int amount) {
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

        public Bag(String name, int value, Rules rules) {
            this.name = name;
            this.value = value;
            
            List<BagWithAmount> other = rules.get(name);
            content = new ArrayList<>();
            for (BagWithAmount bwa : other) {
                content.add(new Bag(bwa.name(), bwa.amount(), rules));
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
            return String.format("%s %s %s", name, value, content);
        }
        
        
        
    }
    
    
    
}
