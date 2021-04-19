package mar;

import java.io.FileInputStream;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PropertiesFiles {
	Properties p;
	FileInputStream fi;
	WebDriver driver;

	@BeforeTest
	public void setup() throws Throwable {

		p = new Properties();
		fi = new FileInputStream("OR.properties");
		p.load(fi);
		if (p.getProperty("Browser").equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();
			driver.navigate().to(p.getProperty("Url"));
			driver.manage().window().maximize();
			Thread.sleep(5000);
		} else if (p.getProperty("Browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			driver.get(p.getProperty("Url"));

		}

		else {
			System.out.println("Browser value is not matching");
		}
	}

	@Test
	public void verifyLogin() {

		driver.findElement(By.xpath(p.getProperty("Objresetbtn"))).click();

		driver.findElement(By.xpath(p.getProperty("objuser"))).sendKeys("admin");
		driver.findElement(By.xpath(p.getProperty("objpass"))).sendKeys("master");
		driver.findElement(By.xpath(p.getProperty("objloginbtn"))).submit();
		String expected = "Dashboard « Stock";
		String actual = driver.getTitle();
		if (actual.contains(expected)) {
			Reporter.log("Login sucess::" + expected + "   " + actual);
		}
	}
}
