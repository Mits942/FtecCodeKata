package hu.ftec.kata.munging.reader;

import hu.ftec.kata.munging.WeatherRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class FileLineReader {

    private final List<String> lines = new ArrayList<>();

    public static FileLineReader getReader(Path filepath) {
        return new FileLineReader().read(filepath);
    }

    //@Test
    public FileLineReader read(Path filepath) {
        if(!Files.exists(filepath)) {
            throw new RuntimeException(String.format("Nem létezik a fájl! (%s)",filepath.toAbsolutePath().toString()));
        }
        this.lines.clear();
        try {
            Stream<String> lines = Files.lines(filepath);
            lines.forEach(line -> this.lines.add(line));
            lines.close();
            return this;
        }
        catch (IOException e) {
            throw new RuntimeException(String.format("IO hiba! (%s)", e.getMessage()));
        }
    }

    public Stream<String> getLines() {
        return this.lines.stream();
    }

}
