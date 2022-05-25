package testing.CSV;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
public class DeserialBNM
{
    public static void main(String[] args)
    {
        try {
            File file = new File("Excel.xlsx");
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            for (int i = 0; i < 10; i++) {
                XSSFSheet sheet = wb.getSheetAt(i);
                Iterator<Row> itr = sheet.iterator();
                while (itr.hasNext()) {
                    Row row = itr.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cell.getCellType()) {
                            case STRING:
                                System.out.print(cell.getStringCellValue() + "\t\t\t");
                                break;
                            case NUMERIC:
                                System.out.print(cell.getNumericCellValue() + "\t\t\t");
                                break;
                            default:
                        }
                    }
                    System.out.println("\t");
                }
                System.out.println("\n");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}