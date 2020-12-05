package se.tastebin.adventofcode.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import se.tastebin.adventofcode.ListFromFile;

public class Day04 {
    
    public static void main(String[] args) {
        
        List<String> strings = new ListFromFile("src/main/java/se/tastebin/adventofcode/day04/day04.txt").list();

        List<Passport> passport = new ArrayList<>();
        
        StringBuilder result = new StringBuilder();
        for (String row : strings) {
            if (!row.isBlank()) {                
                result.append(row).append(" ");
            } else {
                passport.add(new Passport(result.toString()));
                result.setLength(0);
            }
        }
        passport.add(new Passport(result.toString()));
        
        long count = passport.stream().filter(p->p.isValid()).count();
        System.out.println(String.format("Number of valid passports: %d", count));
    }
    
    private static class Passport {
        
        private final String input;
        
        public Passport(String input) {
            this.input = input;
        }
        
        public boolean isValid() {
           
            String[] pairs = input.split(" ");
            
            Map<String, PasswordField> map = new HashMap();
            for (String pair : pairs) {
                String key = pair.split(":")[0];
                String value = pair.split(":")[1];
                switch(key) {                    
                    case "byr": map.put(key, new BirthYear(value)); break;
                    case "iyr": map.put(key, new IssueYear(value)); break;
                    case "eyr": map.put(key, new ExpirationYear(value)); break;
                    case "hgt": map.put(key, new Height(value)); break;
                    case "hcl": map.put(key, new HairColor(value)); break;
                    case "ecl": map.put(key, new EyeColor(value)); break;
                    case "pid": map.put(key, new PassportId(value)); break;
                    case "cid": map.put(key, new CountryId(value)); break;
                    default:
                } 
                
            }
                    
            return (map.size() == 8 || (map.size() == 7 && !map.containsKey("cid"))) &&
                    map.entrySet().stream().allMatch(e -> e.getValue().isValid());
        }
        
    }
    
    private static interface PasswordField {
        boolean isValid();
    }
    
    private static class BirthYear implements PasswordField{
        private final String year;

        public BirthYear(String year) {
            this.year = year;
        }
        
        @Override
        public boolean isValid() {
            try {
                int y = Integer.parseInt(year);
                return (year.length() == 4) && (y >= 1920) && (y <= 2002);
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
    
    private static class IssueYear implements PasswordField {

        private final String year;

        public IssueYear(String year) {
            this.year = year;
        }

        @Override
        public boolean isValid() {
            try {
                int y = Integer.parseInt(year);
                return (year.length() == 4) && (y >= 2010) && (y <= 2020);
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
    
    private static class ExpirationYear implements PasswordField {
        
        private final String year;

        public ExpirationYear(String year) {
            this.year = year;
        }

        @Override
        public boolean isValid() {
            try {
                int y = Integer.parseInt(year);
                return (year.length() == 4) && (y >= 2020) && (y <= 2030);
            } catch (NumberFormatException e) {
                return false;
            }
        }

    }
    
    private static class Height implements PasswordField {
        private final String height;

        public Height(String height) {
            this.height = height;
        }

        @Override
        public boolean isValid() {
            String value = height.substring(0, height.length()-2);
            
            int val;
            
            try {
                val = Integer.parseInt(value);                
            } catch (NumberFormatException e) {
                return false;
            }
            
            String unit = height.substring(height.length()-2);
            if ("cm".equalsIgnoreCase(unit)) {
                return (val >= 150) && (val <= 193);
            } else if ("in".equalsIgnoreCase(unit)) {
                return (val >= 59) && (val <= 76);
            } else {
                return false;
            }
        }
        
    }
    
    private static class HairColor implements PasswordField {

        private final String color;

        public HairColor(String color) {
            this.color = color;
        }

        @Override
        public boolean isValid() {
            if (color.length() != 7 && !color.startsWith("#")) {
                return false;
            }
            
            String val = color.substring(1);
            try {
                Long.parseLong(val, 16);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
            
        }        
    }
    
    private static class EyeColor implements PasswordField {

        private final String color;

        public EyeColor(String color) {
            this.color = color;
        }
        
        @Override
        public boolean isValid() {
            List<String> colors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
            return colors.contains(color);
        }
        
    }
    
    private static class PassportId implements PasswordField {

        private final String pid;

        public PassportId(String pid) {
            this.pid = pid;
        }
        
        @Override
        public boolean isValid() {
            return (pid.length() == 9) && pid.chars().allMatch(Character::isDigit);
        }
        
    }
    
    private static class CountryId implements PasswordField {

        private final String cid;

        public CountryId(String cid) {
            this.cid = cid;
        }
        
        @Override
        public boolean isValid() {
            return true;
        }
        
    }
}
