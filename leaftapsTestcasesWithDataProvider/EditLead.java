package leaftapsTestcasesWithDataProvider;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditLead extends BaseClass {

	public XSSFWorkbook wb;
	public XSSFSheet ws;
	
	@Test(dataProvider = "sendData")
	public void runEditLead(String ph) throws InterruptedException {
		driver.findElement(By.linkText("Find Leads")).click();
		driver.findElement(By.xpath("//span[text()='Phone']")).click();
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(ph);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.findElement(By.linkText("Edit")).click();
		driver.findElement(By.id("updateLeadForm_companyName")).sendKeys("TCS");
		driver.findElement(By.name("submitButton")).click();
	}
	
	@DataProvider
	public String[][] sendData() throws IOException{
		String[][] data;
		wb = new XSSFWorkbook("./data/LeaftapsInputFile.xlsx");
		ws = wb.getSheet("EditLead");
		int rowCount = ws.getLastRowNum();
		int cellCount = ws.getRow(0).getLastCellNum();
		System.out.println("Rows = "+rowCount);
		data = new String[rowCount][cellCount];
		//List<String> createData= new ArrayList<String>();
		for(int i=1; i<=rowCount; i++) {
			for(int j=0; j<cellCount; j++) {
				String phone = ws.getRow(i).getCell(j).getStringCellValue();
				System.out.println(phone);
				data[i-1][j]=phone;
				System.out.println(data[i-1][j]);
			}			
		}		
		wb.close();
		return data;
	}
}






