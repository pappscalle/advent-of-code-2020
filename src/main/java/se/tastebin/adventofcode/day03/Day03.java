package se.tastebin.adventofcode.day03;

import java.util.List;
import se.tastebin.adventofcode.ListFromFile;

public class Day03 {
    
    public static void main(String[] args) {
       
            List<String> strings = new ListFromFile("src/main/java/se/tastebin/adventofcode/day03/day03.txt").list();
            
            long c1 = numberOfTrees(strings, 1, 1);
            long c2 = numberOfTrees(strings, 3, 1);
            long c3 = numberOfTrees(strings, 5, 1);
            long c4 = numberOfTrees(strings, 7, 1);
            long c5 = numberOfTrees(strings, 1, 2);
            
            long total = c1 * c2 * c3 *c4 *c5;
            
            System.out.println(String.format("Number of trees: %d", total));
    }
    

    private static long numberOfTrees(List<String> strings, int dx, int dy) {
            int sizeX = strings.get(0).length();
            int sizeY = strings.size();
            
            int currentX = 0;
            int currentY = 0;
            
            int counter = 0;
            
            while (currentY < sizeY-1) {
                currentX = (currentX + dx) % sizeX;
                currentY = currentY + dy;
                char ch = strings.get(currentY).charAt(currentX);
                if (ch == '#') {
                    counter++;
                }
            }
            return counter;
    }
    
    
}
