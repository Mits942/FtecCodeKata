package hu.ftec.kata.munging;

import hu.ftec.kata.munging.reader.FileLineReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class WeatherDataReader {

    private final List<WeatherRecord> days = new ArrayList<>();

    public static WeatherDataReader getWeatherData(FileLineReader reader) {
        return new WeatherDataReader().read(reader.getLines());
    }

    //@Test
    public WeatherDataReader read(Stream<String> lines) {
        this.days.clear();
        lines.forEach(line -> {
            if(WeatherRecord.isWeatherRecord(line)) {
                this.days.add(WeatherRecord.getWeatherRecord(line));
            }
        });
        lines.close();
        return this;
    }

    public List<WeatherRecord> getDays() {
        return Collections.unmodifiableList(this.days);
    }

}
