package testing.CSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSV {
    private static final String SAMPLE_CSV_FILE_PATH = "data.csv";

    public static List<String> parserCSV () throws IOException {
        List<String> arrayarstring=new ArrayList<>();
        try (Reader reader=new BufferedReader(new FileReader(SAMPLE_CSV_FILE_PATH))) {
            try (CSVParser csvParser=new CSVParser(reader, CSVFormat.DEFAULT)) {
                for (CSVRecord csvRecord : csvParser) {
                    String date=csvRecord.get(0);
                    arrayarstring.add(date);
                }
            }
        }
        for(String ar : arrayarstring){
            System.out.println(ar);
        }
        return arrayarstring;
    }
}
