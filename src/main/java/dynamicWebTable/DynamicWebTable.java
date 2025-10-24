package dynamicWebTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DynamicWebTable {

	public static void main(String[] args) {
		WebDriver wd = new ChromeDriver();
		wd.get("https://practice.expandtesting.com/dynamic-table#google_vignettte");
		By tableLocator = By.xpath("//table[contains(@class,\"table table-striped\")]");
		WebElement tableElement = wd.findElement(tableLocator);
		By tableHeaderLocator = By.xpath(".//thead/tr/th");
		List<WebElement> tableHeaderList = wd.findElements(tableHeaderLocator);
		Map<String, Integer> headerMap = new HashMap<String, Integer>();
		for (int i = 0; i < tableHeaderList.size(); i++) {
			headerMap.put(tableHeaderList.get(i).getText().toLowerCase(), i);
		}
		By tableRowLocator = By.xpath(".//tbody/tr");
		List<WebElement> tableRowList = wd.findElements(tableRowLocator);
		List<TaskPojo> taskList = new ArrayList<TaskPojo>();
		for (WebElement row : tableRowList) {
			List<WebElement> dataList = row.findElements(By.xpath(".//td"));
			taskList.add(new TaskPojo(dataList.get(headerMap.get("name")).getText(),
					dataList.get(headerMap.get("network")).getText(), dataList.get(headerMap.get("memory")).getText(),
					dataList.get(headerMap.get("cpu")).getText(), dataList.get(headerMap.get("disk")).getText()));
		}
		List<TaskPojo> data = taskList.stream().filter(x->x.getName().contains("Chrome")).collect(Collectors.toList());
		System.out.println(data);
		wd.quit();

	}

}
