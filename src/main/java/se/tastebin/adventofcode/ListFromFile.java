package se.tastebin.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class ListFromFile {

    private final String filename;

    public ListFromFile(String fileName) {
        this.filename = fileName;
    }
    
    public List<String> list(){
        
        //System.out.println(FileSystems.getDefault().getPath("").toAbsolutePath().toString());
        
        try {
            return Files.readAllLines(Path.of(filename));
        } catch (IOException ex) {
            throw new IllegalArgumentException(filename + " not found!", ex);
        }
    }
    
}
