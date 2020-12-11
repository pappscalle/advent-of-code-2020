package se.tastebin.adventofcode.day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import se.tastebin.adventofcode.ListFromFile;

public class Day09 {
    public static void main(String[] args) {
        List<String> strings = new ListFromFile("src/main/java/se/tastebin/adventofcode/day09/day09.txt").list();
        List<Long> numbers = strings.stream().map(s-> Long.valueOf(s)).collect(Collectors.toList());
        
        System.out.println(numbers);
        
        int range = 25;
        
        List<Long> sublist = numbers.subList(0, 5);
        
        System.out.println(getPairs(sublist));
        System.out.println(getSums(sublist));
        
        for (int i = 0; i<numbers.size(); i++) {
            if (i > range) {
                List<Long> sums = getSums(numbers.subList(i-range, i));
                if (!sums.contains(numbers.get(i))) {
                    System.out.println(numbers.get(i));
                }
            }
        }
        
    }
    
    private static List<Long> getSums(List<Long> numbers) {
        List<Long> result = new ArrayList<>();
        for(int n = 0; n < numbers.size(); n++) {
            for(int m = n+1; m < numbers.size(); m++){
                result.add(numbers.get(n) + numbers.get(m));
            }
        }
        return result;
    }
    
//    private static List<List<Long>> getPairs(List<Long> numbers) {
//        List<List<Long>> result = new ArrayList<>();
//        for(int n = 0; n < numbers.size(); n++) {
//            for(int m = n+1; m < numbers.size(); m++){
//                result.add(Arrays.asList(numbers.get(n), numbers.get(m)));
//            }
//        }
//        return result;
//    }
   
    
}
