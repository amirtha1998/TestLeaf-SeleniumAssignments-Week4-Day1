package week4.day1.assignment3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignmentFrame {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.xpath("//label//span"));
		// System.out.println(text);
		driver.switchTo().frame("frame1");

		driver.findElement(By.xpath("//input")).sendKeys("Not a friendly topic");

		driver.switchTo().frame("frame3");

		driver.findElement(By.xpath("//input[@Id='a']")).click();

		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame2");

		WebElement eleSource = driver.findElement(By.xpath("//select[@Class='col-lg-3']"));
		Select dd = new Select(eleSource);
		dd.selectByVisibleText("Avatar");

	}
}
