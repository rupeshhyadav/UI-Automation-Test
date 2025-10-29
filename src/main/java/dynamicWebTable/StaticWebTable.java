package dynamicWebTable;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class StaticWebTable {

	public static void main(String[] args) {
		WebDriver wd = new ChromeDriver();
		wd.get("https://rahulshettyacademy.com/AutomationPractice/");
		By webTableRowLocator = By.xpath("//table[@class='table-display']/tbody/tr[position()>1]");
		By webTableHeaderLocator = By.xpath("//table[@class='table-display']/tbody/tr/th");
		List<WebElement> webTableHeaders = wd.findElements(webTableHeaderLocator);
		List<WebElement> webTableRows = wd.findElements(webTableRowLocator);
		int priceColIndex = -1;
		int courseColIndex = -1;
		for (int i = 0; i < webTableHeaders.size(); i++) {
			String headerText = webTableHeaders.get(i).getText();
			if (headerText.equalsIgnoreCase("Price")) {
				priceColIndex = i + 1;
			} else if (headerText.equalsIgnoreCase("Course")) {
				courseColIndex = i + 1;
			}
		}

		String lowestCourse = " ";
		int lowestPrice = Integer.MAX_VALUE;

		for (WebElement row : webTableRows) {
			String priceText = row.findElement(By.xpath(".//td[" + priceColIndex + "]")).getText();
			String courseText = row.findElement(By.xpath(".//td[" + courseColIndex + "]")).getText();
			int price = Integer.parseInt(priceText);
			if (price < lowestPrice) {
				lowestPrice = price;
				lowestCourse = courseText;
			}
		}

		System.out.println(lowestPrice + "-----" + lowestCourse);
		wd.quit();

	}

}
