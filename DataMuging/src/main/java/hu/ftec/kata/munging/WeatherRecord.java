package hu.ftec.kata.munging;

public class WeatherRecord {
    private String line;
    private int day;
    private int min;
    private int max;

    public WeatherRecord(String line) {
        this.line = line;
    }

//01234567890123456789
//   1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5
    public static boolean isRecord(String line) {
        if(line == null || line.length() < 14) {
            return false;
        }
        try {
            String val = line.substring(0, 4);
            Integer.parseInt(val.trim());
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    public static WeatherRecord getRecord(String line) {
        if(line == null || line.length() < 14) {
            throw new RuntimeException("Nem megfelelő rekord tartalom! (%s)" + line);
        }
        WeatherRecord record = new WeatherRecord(line);
        record.day = record.getValue(0,4);
        record.max = record.getValue(5,8);
        record.min = record.getValue(9,14);
        return record;
    }

    public int getDay() {
        return day;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getSpread() {
        return max - min;
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
