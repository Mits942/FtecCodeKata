package hu.ftec.kata.munging.soccer;

public class SoccerTableRecord {
    private final String line;
    private String team;
    private int rug;
    private int kap;

    public SoccerTableRecord(String line) {
        this.line = line;
    }

//012345678901234567890123456789012345678901234567890123456789
//    1. Arsenal         38    26   9   3    79  -  36    87
    public static boolean isMatchRecord(String line) {
        if(line == null || line.length() < 14) {
            return false;
        }
        String val = line.substring(5, 6);
        return ".".equals(val);
    }

    public static SoccerTableRecord getTableRecord(String line) {
        if(line == null || line.length() < 14) {
            throw new RuntimeException("Nem megfelelő rekord tartalom! (%s)" + line);
        }
        SoccerTableRecord record = new SoccerTableRecord(line);
        record.team = record.getLine().substring(7, 22).trim();
        record.rug = record.getValue(40,45);
        record.kap = record.getValue(48,52);
        return record;
    }

    public String getTeam() {
        return team;
    }

    public int getRug() {
        return rug;
    }

    public int getKap() {
        return kap;
    }

    public int getDiff() {
        return Math.abs(rug - kap);
    }

    private String getLine() {
        return line;
    }

    private int getValue(int start, int end) {
        String val = this.line.substring(start, end);
        try {
            return Integer.parseInt(val.trim());
        }
        catch(NumberFormatException e) {
            throw new RuntimeException(String.format("Nem numerikus érték! (%s)", val.trim()));
        }
    }

}
