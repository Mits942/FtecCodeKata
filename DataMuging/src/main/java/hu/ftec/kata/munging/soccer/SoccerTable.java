package hu.ftec.kata.munging.soccer;

import hu.ftec.kata.munging.reader.FileLineReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class SoccerTable {

    private final List<SoccerTableRecord> matchs = new ArrayList<>();

    public static SoccerTable getSoccerTable(FileLineReader reader) {
        return new SoccerTable().read(reader.getLines());
    }

    //@Test
    public SoccerTable read(Stream<String> lines) {
        this.matchs.clear();
        lines.forEach(line -> {
            if(SoccerTableRecord.isMatchRecord(line)) {
                this.matchs.add(SoccerTableRecord.getTableRecord(line));
            }
        });
        lines.close();
        return this;
    }

    public List<SoccerTableRecord> getMatchs() {
        return Collections.unmodifiableList(this.matchs);
    }

}
