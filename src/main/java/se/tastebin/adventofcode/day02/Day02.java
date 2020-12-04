package se.tastebin.adventofcode.day02;

import java.util.List;
import se.tastebin.adventofcode.ListFromFile;

public class Day02 {
    
        public static void main(String[] args) {
       
            List<String> strings = new ListFromFile("src/main/java/se/tastebin/adventofcode/day02/day02.txt").list();

            int count1 = 0;
            int count2 = 0;
            
            for (String str : strings) {
                String[] passwords = str.split(" ");
                String[] minMax = passwords[0].split("-");
                int min = Integer.valueOf(minMax[0]);
                int max = Integer.valueOf(minMax[1]);
                char ch = passwords[1].charAt(0);
                String password = passwords[2];
                
                Password psw = new Password(min, max, ch, password);
                if (psw.isValid()) {
                    count1++;
                }
                if (psw.isValid2()) {
                    count2++;
                }
            }
            
            System.out.println("count 1: " + count1);
            System.out.println("count 2: " + count2);
            
    }
    
    private static class Password {
        
        private final int min;
        private final int max;
        private final char chr;
        private final String password;

        public Password(int min, int max, char chr, String password) {
            this.min = min;
            this.max = max;
            this.chr = chr;
            this.password = password;
        }
        
       public boolean isValid() {
           long count = password.chars().filter(ch -> ch == chr).count();
           return count >= min && count <= max;
       } 
        
       public boolean isValid2() {
           if (min > password.length() || max > password.length()){
               return false;
           }
           
           return (password.charAt(min-1) == chr && password.charAt(max-1) != chr) ||
                   (password.charAt(min-1) != chr && password.charAt(max-1) == chr);
       }
    }    
        
}
