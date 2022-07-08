package hu.ftec.kata.munging;

import hu.ftec.kata.munging.reader.FileLineReader;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;

public class DataMungingReader {
    public static void main(String[] args) {
        FileLineReader reader = FileLineReader.getReader(Path.of("src/main/weather.dat"));
        WeatherDataReader weatherData = WeatherDataReader.getWeatherData(reader);
        Optional<WeatherRecord> minSpreadDayOpt = weatherData.getDays()
                .stream()
//                .sorted((rec1, rec2) -> Integer.compare(rec1.getSpread(), rec2.getSpread()))
//                .sorted(Comparator.comparingInt(WeatherRecord::getSpread))
                .min(Comparator.comparingInt(WeatherRecord::getSpread));
        if(minSpreadDayOpt.isPresent()) {
            WeatherRecord day = minSpreadDayOpt.get();
            System.out.println(String.format("A(z) %d napon legkisebb a különbség: %d (%d, %d)", day.getDay(), day.getSpread(), day.getMin(), day.getMax()));
        }
    }
}
