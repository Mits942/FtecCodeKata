package hu.ftec.kata.munging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class WeatherData {

    private final List<WeatherRecord> days = new ArrayList<>();

    public static WeatherData getWeatherData(Path filepath) {
        return new WeatherData().read(filepath);
    }

    //@Test
    public WeatherData read(Path filepath) {
        if(!Files.exists(filepath)) {
            throw new RuntimeException(String.format("Nem létezik a fájl! (%s)",filepath.toAbsolutePath().toString()));
        }
        this.days.clear();
        try {
            Stream<String> lines = Files.lines(filepath);
            lines.forEach(line -> {
                if(WeatherRecord.isWeatherRecord(line)) {
                    this.days.add(WeatherRecord.getWeatherRecord(line));
                }
            });
            lines.close();
            return this;
        }
        catch (IOException e) {
            throw new RuntimeException(String.format("IO hiba! (%s)", e.getMessage()));
        }
    }

    public List<WeatherRecord> getDays() {
        return Collections.unmodifiableList(this.days);
    }

}
