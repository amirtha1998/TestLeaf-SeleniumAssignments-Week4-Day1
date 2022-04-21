package week4.day1.assignment2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException {
		// Step1: Load ServiceNow application URL
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev121343.service-now.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.switchTo().frame(0);

		// Step2: Enter username (Check for frame before entering the username)
		driver.findElement(By.xpath("//input[@Id='user_name']")).sendKeys("admin");

		// Step3: Enter password
		driver.findElement(By.xpath("//input[@Id='user_password']")).sendKeys("India@123");

		// Step4: Click Login
		driver.findElement(By.xpath("//button[@Id='sysverb_login']")).click();

		driver.switchTo().defaultContent();

		// Step5: Search “incident “ Filter Navigator
		driver.findElement(By.xpath("//input[@Id='filter']")).sendKeys("incident");
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-content']/div[text()='Incidents'])[1]")).click();

		WebElement frame = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame);

		// Step6: Click “All”
		driver.findElement(By.xpath("//span[@id='incident_breadcrumb']//b[text()='All']")).click();

		driver.switchTo().defaultContent();
		driver.switchTo().frame(frame);

		// Step7: Click New button
		driver.findElement(By.xpath("//button[@Class='selected_action action_context btn btn-primary']")).click();

		// Step8: Select a value for Caller and Enter value for short_description

		driver.switchTo().defaultContent();

		driver.switchTo().frame(frame);
		driver.findElement(By.id("lookup.incident.caller_id")).click();

		Set<String> listwindowHandles = driver.getWindowHandles();
		List<String> listWindow = new ArrayList<String>(listwindowHandles);
		driver.switchTo().window(listWindow.get(1));

		driver.findElement(By.xpath("//tbody[@class='list2_body']//td[3]/a[1]")).click();
		driver.switchTo().window(listWindow.get(0));
		driver.switchTo().frame(frame);

		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys("ticket for the issue");

		// Step9: Read the incident number and save it a variable
		String incNo = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		// System.out.println(incNo);

		// Step10: Click on Submit button
		driver.findElement(By.xpath("//button[@Value='sysverb_insert']")).click();

		// Step 11: Search the same incident number in the next search screen as below
		driver.findElement(By.xpath("//div[@class='input-group']/input[@placeholder='Search']")).sendKeys(incNo);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='input-group']/input[@placeholder='Search']")).sendKeys(Keys.ENTER);

		// Step12: Verify the incident is created successful.
		String incident = driver
				.findElement(By.xpath("//tbody[@class='list2_body']/tr[1]/td/a[@class='linked formlink']")).getText();
		if (incident.equals(incNo)) {
			System.out.println("Incident " + incNo + " is Found");
		} else {
			System.out.println("Incident " + incNo + " is not Found");
		}
	}

}
