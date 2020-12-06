package se.tastebin.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class GroupedStringsFromFile {

    private final String filename;

    public GroupedStringsFromFile(String fileName) {
        this.filename = fileName;
    }
    
    public List<List<String>> list(){
        
        //System.out.println(FileSystems.getDefault().getPath("").toAbsolutePath().toString());
        
        try {
            List<String> strings = Files.readAllLines(Path.of(filename));
            
            List<List<String>> output = new ArrayList<>();
            
            List<String> group = new ArrayList<>();
            for (String row : strings) {
                if (!row.isBlank()) {                
                    group.add(row);
                } else {
                    output.add(group);  
                    group = new ArrayList<>();
                }
            }
            output.add(group);
            return output;
        } catch (IOException ex) {
            throw new IllegalArgumentException(filename + " not found!", ex);
        }
    }
    
}
