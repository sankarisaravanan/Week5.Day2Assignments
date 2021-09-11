package leaftapsTestcasesWithDataProvider;


import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLead extends BaseClass{

	public XSSFWorkbook wb;
	public XSSFSheet ws;
	
	@Test(dataProvider = "sendData")
	public void runCreateLead(String company, String fName, String lName, String ph) {
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(company);
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(fName);
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lName);
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(ph);
		driver.findElement(By.name("submitButton")).click();
	}	
		@DataProvider
		public String[][] sendData() throws IOException{
			String[][] data;
			
			/*data[0][0] = "TestLeaf";
			data[0][1] = "Hari";
			data[0][2] = "R";
			data[0][3] = "999";
			
			data[1][0] = "Infy";
			data[1][1] = "Sheriba";
			data[1][2] = "P";
			data[1][3] = "9999";
			
		return data;*/
			wb = new XSSFWorkbook("./data/LeaftapsInputFile.xlsx");
			ws = wb.getSheet("CreateLead");
			int rowCount = ws.getLastRowNum();
			int cellCount = ws.getRow(0).getLastCellNum();
			System.out.println("Rows = "+rowCount+" and Columns = "+cellCount);
			data = new String[rowCount][cellCount];
			//List<String> createData= new ArrayList<String>();
			for(int i=1; i<=rowCount; i++) {
				for(int j=0; j<cellCount;j++) {
					String text = ws.getRow(i).getCell(j).getStringCellValue();
					System.out.println(text);
					data[i-1][j]=text;
					System.out.println(data[i-1][j]);
				}
				
			}
			
			wb.close();
			return data;
			
		}	
		
}






