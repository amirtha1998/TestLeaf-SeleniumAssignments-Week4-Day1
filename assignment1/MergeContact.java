package week4.day1.assignment1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		// 1. Launch URL "http://leaftaps.com/opentaps/control/login"
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// 2. Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		// 3. Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();

		// 4. Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();

		// 5. Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();

		// 6. Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[contains(text(),'Merge')]")).click();
		// 7. Click on Widget of From Contact
		driver.findElement(By.xpath("//a/img[@Alt='Lookup']")).click();
		// 8. Click on First Resulting Contact
		Set<String> allHandles = driver.getWindowHandles();
		List<String> listHandles = new ArrayList<String>(allHandles);

		driver.switchTo().window(listHandles.get(1));

		driver.findElement(By.xpath("//div[@Class='x-grid3-cell-inner x-grid3-col-partyId']/a[1]")).click();

		driver.switchTo().window(listHandles.get(0));

		// 9. Click on Widget of To Contact
		driver.findElement(By.xpath("//table[@Widgetid='ComboBox_partyIdTo']/following-sibling::a")).click();

		// 10. Click on Second Resulting Contact

		Set<String> allHandle = driver.getWindowHandles();
		List<String> listHandle = new ArrayList<String>(allHandle);

		driver.switchTo().window(listHandle.get(1));
		driver.findElement(By.xpath("//div[@Class='x-grid3-row    x-grid3-row-alt']//a")).click();

		driver.switchTo().window(listHandle.get(0));
		// 11. Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("//a[@Class='buttonDangerous']")).click();
		Thread.sleep(1000);
		// 12. Accept the Alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		// 13. Verify the title of the page
		String title = driver.getTitle();
		System.out.println(title);
	}

}
