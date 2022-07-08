package hu.ftec.kata.munging.soccer;

import hu.ftec.kata.munging.reader.FileLineReader;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;

public class SoccerLeagueMain {
    public static void main(String[] args) {
        FileLineReader reader = FileLineReader.getReader(Path.of("src/main/football.dat"));
        SoccerTable table = SoccerTable.getSoccerTable(reader);
        Optional<SoccerTableRecord> minSpreadDayOpt = table.getMatchs()
                .stream()
//                .sorted((rec1, rec2) -> Integer.compare(rec1.getDiff(), rec2.getDiff()))
//                .findFirst();
//                .sorted(Comparator.comparingInt(WeatherRecord::getSpread))
                .min(Comparator.comparingInt(SoccerTableRecord::getDiff));
        if(minSpreadDayOpt.isPresent()) {
            SoccerTableRecord team = minSpreadDayOpt.get();
            System.out.println(String.format("A(z) %s csapatnál legkisebb a különbség: %d (%d, %d)", team.getTeam(), team.getDiff(), team.getRug(), team.getKap()));
        }
    }
}
