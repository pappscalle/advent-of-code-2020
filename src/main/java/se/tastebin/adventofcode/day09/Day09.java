package se.tastebin.adventofcode.day09;

import java.util.List;
import java.util.stream.Collectors;
import se.tastebin.adventofcode.ListFromFile;

public class Day09 {
    public static void main(String[] args) {
        List<String> strings = new ListFromFile("src/main/java/se/tastebin/adventofcode/day09/day09-test.txt").list();
        List<Long> numbers = strings.stream().map(s-> Long.valueOf(s)).collect(Collectors.toList());
        
        System.out.println(numbers);
        
        
        
        
    }
}
