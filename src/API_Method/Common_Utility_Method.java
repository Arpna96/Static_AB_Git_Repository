package API_Method;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Common_Utility_Method {
	public static void EvidenceCreator(String FileName, String RequestBody, String ResponseBody, int StatusCode)
			throws IOException {
		File TextFile = new File("F:\\MSSG\\Rest\\" + FileName + ".txt");
		System.out.println("New Blank text File of name" + TextFile.getName());
		FileWriter DataWrite = new FileWriter(TextFile);
		DataWrite.write("Request body is " + RequestBody + "\n\n");
		DataWrite.write("Status Code is " + StatusCode + "\n\n");
		DataWrite.write("Response Body is " + ResponseBody);
		DataWrite.close();
		System.out.println("Req body and res body written in " + TextFile.getName());
	}

	public static ArrayList<String> DataReadExcel(String SheetName, String TestCaseName) throws IOException {
		ArrayList<String> ArrayData = new ArrayList<String>();
		// create aobject of file input stream to locate the file
		FileInputStream FIS = new FileInputStream("F:\\MSSG\\SEL\\RJ.xlsx");
		// Open the excel by craeting the object of XSSFWorkbook
		XSSFWorkbook Workbook = new XSSFWorkbook(FIS);
		// open the desired sheet
		int countofsheet = Workbook.getNumberOfSheets();
		for (int i = 0; i < countofsheet; i++) {
			String Sheetname = Workbook.getSheetName(i);
			// Access the desired Sheet
			if (Sheetname.equalsIgnoreCase(SheetName)) {
				// Use XSSFSheet to save the sheet into a variable
				XSSFSheet Sheet = Workbook.getSheetAt(i);
				// create iterator to rows and find out in which column the testcase are found
				Iterator<Row> Rows = Sheet.iterator();
				Row FirstRow = Rows.next();
				// create the iterator through the cell
				Iterator<Cell> CellsOfFirstRow = FirstRow.cellIterator();
				int k = 0;
				int TC_column = 0;
				while (CellsOfFirstRow.hasNext()) {
					Cell Cellvalue = CellsOfFirstRow.next();
					if (Cellvalue.getStringCellValue().equalsIgnoreCase("TestCase")) {
						TC_column = k;
						System.out.println("Expected coulumn for TestCaeName:" + k);
						break;
					}
					k++;
				}
				// verify row where the desired testcase is found and fetch the entire row
				while (Rows.hasNext()) {
					Row DataRow = Rows.next();
					String TCname = DataRow.getCell(TC_column).getStringCellValue();
					if (TCname.equalsIgnoreCase(TestCaseName))
						;
					{
						Iterator<Cell> Cellvalues = DataRow.cellIterator();
						while (Cellvalues.hasNext()) {
							String Data = Cellvalues.next().getStringCellValue();
							ArrayData.add(Data);
						}
						break;
					}
				}

			}
		}
		return ArrayData;

	}

}
