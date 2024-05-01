package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "AllData")
    public Object[][] allUserDataProvider() {
        try {
            String fileName = System.getProperty("user.dir") + "\\TestData\\TestData.xlsx";
            int ttlRowCount = ReadExcelFile.getRowCount(fileName, "Sheet1");
            int ttlColCount = ReadExcelFile.getColCount(fileName, "Sheet1");
            
            Object[][] userData =  new String[ttlRowCount-1][ttlColCount];
    		
            
            for(int rowNo = 1; rowNo<ttlRowCount; rowNo++)
    		{
    			for(int colNo=0; colNo<ttlColCount; colNo++)
    			{
    				userData[rowNo-1][colNo] = ReadExcelFile.getCellValue(fileName, "Sheet1", rowNo, colNo);
    			}
    			
    		}
            return userData;
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    @DataProvider(name = "UserNamesData")
    public Object[] userNamesDataProvider() {
        try {
            String fileName = System.getProperty("user.dir") + "\\TestData\\TestData.xlsx";
            int ttlRowCount = ReadExcelFile.getRowCount(fileName, "Sheet1");
            Object[] userNamesData = new Object[ttlRowCount-1];
            
            for (int rowNum = 1; rowNum < ttlRowCount; rowNum++) {
                userNamesData[rowNum - 1] = ReadExcelFile.getCellValue(fileName, "Sheet1", rowNum, 1);
            }
            return userNamesData;
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[0];
        }
    }
}
