package testing.CSV;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class BNMExecP2 {
    public static void main(String[] args) throws Exception {
        createWorkBook();
        List<ValCurs> valCurs = new ArrayList<>();
        List<String> listOfDates = CSV.parserCSV();

        for (String str : listOfDates) {
            valCurs.add(BNMGet.sendGet(str));
        }
        for (ValCurs temp : valCurs) {
            openWorkBook(temp);
        }
    }
    private static void createWorkBook() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        FileOutputStream out = new FileOutputStream("Excel2.xlsx");

        workbook.write(out);
        out.close();
    }
    private static void openWorkBook(ValCurs valCurs) throws Exception {
        File file = new File("Excel2.xlsx");
        FileInputStream fIP = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fIP);
        XSSFSheet spreadsheet = workbook.createSheet(valCurs.getDate());

        XSSFCellStyle style = workbook.createCellStyle();
        XSSFRow row;

        Map<String, Object[]> valutes = new TreeMap<>();
        Map<String, Object[]> miss = new TreeMap<>();

        if (valCurs.getDate() == "01.01.1994") {

            valutes.put("*", new Object[]{"Name", "Date", "CharCode", "Nominal", "Valute Name", "Value"});
            valutes.put("+", new Object[]{valCurs.getName(), valCurs.getDate()});
            valutes.put("/", new Object[]{"ID", "NumCode"});

            for (Valute valute : valCurs.getList()) {
                valutes.put(valute.getId(), new Object[]{
                        valute.getId(),
                        valute.getNumCode(),
                        valute.getСharCode(),
                        valute.getNominal() + "",
                        valute.getName(),
                        Double.toString(valute.getValue())});
            }

            Set<String> keyid = valutes.keySet();
            int rowid = 0;

            for (String key : keyid) {
                row = spreadsheet.createRow(rowid++);

                Object[] objectArr = valutes.get(key);
                int cellid = 0;

                for (Object obj : objectArr) {
                    Cell cell = row.createCell(cellid++);
                    spreadsheet.autoSizeColumn(cellid);

                    if (rowid == 1 || rowid == 3) {
                        cell.setCellStyle(style);
                    }
                    cell.setCellValue((String) obj);
                }
            }
            FileOutputStream out = new FileOutputStream("Excel2.xlsx");
            workbook.write(out);
            out.close();
            System.out.println("Writting into excel...");
        }
        else{
            miss.put("*", new Object[]{" ","< 01.01.1994", "> 01.01.1994", "data de azi", "data din viitor", "invalid data format"});
            miss.put("/", new Object[]{"Missing rates for date\n "  + valCurs.getDate()});
            Set<String> keyid = miss.keySet();
            int rowid = 0;

            for (String key : keyid) {
                row = spreadsheet.createRow(rowid++);
                Object[] objectArr = miss.get(key);
                int cellid = 0;

                for (Object obj : objectArr) {
                    Cell cell = row.createCell(cellid++);
                    spreadsheet.autoSizeColumn(cellid);

                    if (rowid == 1 || rowid == 3) {
                        cell.setCellStyle(style);
                    }
                    cell.setCellValue((String) obj);
                }
            }
            FileOutputStream out = new FileOutputStream("Excel2.xlsx");
            workbook.write(out);
            out.close();
            System.out.println("Writting into excel...");
        }
    }
}
