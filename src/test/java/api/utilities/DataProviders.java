package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="AllData")
	public String[][] allDataProvider(){
		
		String fname= System.getProperty("user.dir")+"//TestData//TestData.xlsx";
		int ttlrowcount=ReadExcelFile.getRowCount(fname, "sheet1");
		int ttlcolcount=ReadExcelFile.getColCount(fname, "sheet1");
		
		String userData[][]=new String[ttlrowcount-1][ttlcolcount];
		for(int rowNo=1;rowNo<ttlrowcount;rowNo++) {
			for(int colNo=0;colNo<ttlcolcount;colNo++) {
				userData[rowNo-1][colNo]=ReadExcelFile.getCellValue(fname, "sheet1", rowNo, colNo);
			}
		}
		return userData;
	}
	
	
	@DataProvider(name="userNameData")
	public String[] userNameProvider() {
		String fname= System.getProperty("user.dir")+"//TestData//TestData.xlsx";
		int ttlrowcount=ReadExcelFile.getRowCount(fname, "sheet1");
		
		String userData[]=new String[ttlrowcount-1];
		for(int rowNo=1;rowNo<ttlrowcount;rowNo++) {
			userData[rowNo-1]=ReadExcelFile.getCellValue(fname, "sheet1", rowNo, 1);
		}
		return userData;
	}
}
