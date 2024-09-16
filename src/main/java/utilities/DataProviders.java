package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	String path = System.getProperty("user.dir") + "/src/test/resources/ProgData.xlsx";
	ExcelUtils XL = new ExcelUtils(path);

	@DataProvider(name = "ProgData")
	String[][] get_prog_data() throws IOException {

		int rownum = XL.getRowCount("Sheet1");
		int colnum = XL.getCellCount("Sheet1", 1);
		String progdata[][] = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {
				progdata[i - 1][j] = XL.getCellData("Sheet1", i, j);
			}
		}
		return progdata;
	}

}
