package week4.day1.assignment4;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafgroundFrame {

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		/*
		 * 1.Take the the screenshot of the click me button of first frame 2.Find the
		 * number of frames - find the Elements by tagname - iframe - Store it in a list
		 * - Get the size of the list. (This gives the count of the frames visible to
		 * the main page)
		 */
		driver.switchTo().frame(0);
		WebElement element = driver.findElement(By.id("Click"));

		File source = element.getScreenshotAs(OutputType.FILE);
		File destination = new File("./snaps/screenshot.png");
		FileUtils.copyFile(source, destination);

		driver.switchTo().defaultContent();

		List<WebElement> elements = driver.findElements(By.tagName("iframe"));
		System.out.println(elements.size());

	}

}
