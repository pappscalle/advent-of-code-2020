
package se.tastebin.adventofcode.day01;

import java.util.List;
import se.tastebin.adventofcode.ListFromFile;

public class Day01 {

    public static void main(String[] args) {
       
            List<String> strings = new ListFromFile("src/main/java/se/tastebin/adventofcode/day01/day01.txt").list();

            Expenses expenses = new Expenses(strings);
            
            System.out.println(expenses.sumTwo());
            System.out.println(expenses.sumThree());
    }

    private static class Expenses {
        
        private final List<String> expenses;

        public Expenses(List<String> expenses) {
            this.expenses = expenses;
        }
        
        public String sumTwo() {
            for (String expense1 : expenses) {
                int a = Integer.parseInt(expense1);
                for (String expense2 : expenses) {
                    int b = Integer.parseInt(expense2);
                    if (a + b == 2020) {
                        return String.format("a=%d, b=%d, a*b=%d", a, b, a*b);
                    }
                }
            }
            return "Not found!";
        }
        
        public String sumThree() {
            for (String expense1 : expenses) {
                int a = Integer.parseInt(expense1);
                for (String expense2 : expenses) {
                    int b = Integer.parseInt(expense2);
                    for (String expense3 : expenses) {
                        int c = Integer.parseInt(expense3);
                        if (a + b + c == 2020) {
                            return String.format("a=%d, b=%d, c=%d, a*b*c=%d", a, b, c, a*b*c);
                        }
                    }
                }
            }
            return "Not Found!";
        }
    }
}
