package hu.ftec.kata.munging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class WeatherData {

    private List<WeatherRecord> days = new ArrayList<>();

    public static WeatherData getWeatherData(Path filepath) {
        WeatherData weatherData = new WeatherData();
        weatherData.process(filepath);
        return weatherData;
    }

    //@Test
    public WeatherData process(Path filepath) {
        if(!Files.exists(filepath)) {
            throw new RuntimeException(String.format("Nem létezik a fájl! (%s)",filepath.toAbsolutePath().toString()));
        }
        this.days.clear();
        try {
            Stream<String> lines = Files.lines(filepath);
            lines.forEach(line -> {
                if(WeatherRecord.isRecord(line)) {
                    this.days.add(WeatherRecord.getRecord(line));
                }
            });
            lines.close();
            return this;
        }
        catch (IOException e) {
            throw new RuntimeException("");
        }
    }

    public List<WeatherRecord> getDays() {
        return Collections.unmodifiableList(this.days);
    }

}
