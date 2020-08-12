package utilities;

import jdk.internal.org.xml.sax.SAXException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class XmlHelper {

    public static List compareXML(Reader source, Reader target) throws
            SAXException, IOException, org.xml.sax.SAXException {

        //creating Diff instance to compare two XML files
        Diff xmlDiff = new Diff(source, target);

        //for getting detailed differences between two xml files
        DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff);

        return detailXmlDiff.getAllDifferences();
    }

    public static void printDifferences(List differences) throws IOException {
        //Create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet spreadsheet = workbook.createSheet(Constants.WorkSheetOne);

        //Create row object
        XSSFRow row;
        int totalDifferences = differences.size();
        System.out.println("===============================");
        System.out.println("Total differences : " + totalDifferences);
        System.out.println("================================");
        int rowId = 0;
        int cellId = 0;
        for (Object difference : differences) {
            row = spreadsheet.createRow(rowId++);

            Cell cell = row.createCell(cellId);
            cell.setCellValue(difference.toString());
        }

        //Write the workbook in file system
        FileOutputStream out = new FileOutputStream(
                new File(Constants.WorkBookName));

        workbook.write(out);
        out.close();
        System.out.println(Constants.WorkBookName + " written successfully" );

    }
}