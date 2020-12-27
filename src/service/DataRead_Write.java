package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public abstract class DataRead_Write {
    private static Path FILE;

    public Path initialise(String path){
        if(FILE == null){
            FILE = Paths.get(path);
        }
        return FILE;
    }

    public ArrayList<String> read() throws IOException {
        return new ArrayList<>(Files.readAllLines(FILE));
    }

    public void write(String data) throws IOException {
        data += "\n";
        Files.write(FILE,  data.getBytes(), StandardOpenOption.APPEND);
    }
}
