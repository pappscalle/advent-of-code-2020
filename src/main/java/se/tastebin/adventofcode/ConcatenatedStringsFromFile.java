package se.tastebin.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class ConcatenatedStringsFromFile {

    private final String filename;
    private final String separator;

    public ConcatenatedStringsFromFile(String fileName, String separator) {
        this.filename = fileName;
        this.separator = separator;
    }
    
    public List<String> list(){
        
        //System.out.println(FileSystems.getDefault().getPath("").toAbsolutePath().toString());
        
        try {
            List<String> strings = Files.readAllLines(Path.of(filename));
            
            List<String> output = new ArrayList<>();
            
            StringBuilder result = new StringBuilder();
            for (String row : strings) {
                if (!row.isBlank()) {                
                    result.append(row).append(separator);
                } else {
                    output.add(result.toString());
                    result.setLength(0);
                }
            }
            output.add(result.toString());
            return output;
        } catch (IOException ex) {
            throw new IllegalArgumentException(filename + " not found!", ex);
        }
    }
    
}
