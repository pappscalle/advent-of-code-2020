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
            String c = code.trim().substring(code.length() - 3).replace("R", "1").replace("L", "0");

            int row = Integer.parseInt(r, 2);
            int column = Integer.parseInt(c, 2);

            seats.add(row * 8 + column);

        }
        
        Integer max = seats.stream().mapToInt(v -> v).max().orElse(0);

        System.out.println(String.format("Largest seat ID: %d", max));

        List<Integer> sorted = seats.stream().sorted().collect(Collectors.toList());

        int num = sorted.get(0);
        for (int current : sorted) {
            if (current != num) {
                System.out.println(String.format("Missing number: %d", num));
                break;
            }
            num++;
        }

    }

}
