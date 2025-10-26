package ResponsiveTest;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ResponsiveTestClass {

	public static void main(String[] args) {
		WebDriver wd = new ChromeDriver();
		wd.get("https://practice.expandtesting.com/dynamic-table");
		Map<String, Dimension> deviceMap = new HashMap<String, Dimension>() {
			{
				put("Pixel 7", new Dimension(412, 915));
				put("iPad Mini", new Dimension(768, 1024));
			}
		};
		//wd.manage().window().setSize(deviceMap);
		for (Map.Entry<String, Dimension> device : deviceMap.entrySet()) {
			wd.manage().window().setSize(device.getValue());
		}
		wd.quit();

	}

}
