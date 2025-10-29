package yatraAutomation;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class yatraCalendarAutomation {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver wd = new ChromeDriver(options);
		wd.get("https://www.yatra.com/");
		wd.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
		By popUpLocator = By.xpath("//div[contains(@class,'style_popup__a7PrI MuiBox-root css-0')]");
		WebElement popUpLocatorWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(popUpLocator));
		By crossIconLocator = By.xpath("//img[@alt='cross']");
		WebElement crossIconLocatorWebElement = popUpLocatorWebElement.findElement(crossIconLocator);
		crossIconLocatorWebElement.click();
		By DepartureDateLocator = By.xpath("//div[@aria-label='Departure Date inputbox']");
		WebElement DepartureDateElement = wait.until(ExpectedConditions.elementToBeClickable(DepartureDateLocator));
		DepartureDateElement.click();
		WebElement currentMonthElement = selectTheMonth(wait, 0);
		WebElement nextMonthElement = selectTheMonth(wait, 1);
		Thread.sleep(3000);
		String lowestPriceCurrentMonth = getLowestPrice(currentMonthElement);
		String lowestPriceNextMonth = getLowestPrice(nextMonthElement);
		priceCompare(lowestPriceCurrentMonth, lowestPriceNextMonth);
		wd.quit();

	}

	public static WebElement selectTheMonth(WebDriverWait wait, int index) {
		By calendarMonthLocator = By.xpath("//div[@class='react-datepicker__month-container']");
		List<WebElement> calendarMonthList = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calendarMonthLocator));
		WebElement monthElement = calendarMonthList.get(index);
		return monthElement;
	}

	public static String getLowestPrice(WebElement monthWebElement) {
		By priceLocator = By.xpath(".//span[contains(@class,'custom-day-content')]");

		List<WebElement> currentMonthDateList = monthWebElement.findElements(priceLocator);
		int lowestPrice = Integer.MAX_VALUE;
		WebElement datePriceElement = null;
		for (WebElement price : currentMonthDateList) {
			String priceValue = price.getText();
			if (priceValue.length() > 0) {
				priceValue = priceValue.replace("₹", "");
				priceValue = priceValue.replace(",", "");
				int priceInt = Integer.parseInt(priceValue);
				if (priceInt < lowestPrice) {
					lowestPrice = priceInt;
					datePriceElement = price;
				}
			}
		}
		WebElement lowestdateElement = datePriceElement.findElement(By.xpath(".//../.."));
		String result = lowestdateElement.getAttribute("aria-label") + ":" + lowestPrice;
		return result;
	}

	public static void priceCompare(String lowestPriceCurrentMonth, String lowestPriceNextMonth) {
		int colonindexcurrentmonth = lowestPriceCurrentMonth.indexOf(":");
		int colonindexnextmonth = lowestPriceNextMonth.indexOf(":");
		String currentMonthPrice = lowestPriceCurrentMonth.substring(colonindexcurrentmonth + 1);
		String nextMonthPrice = lowestPriceNextMonth.substring(colonindexnextmonth + 1);
		if (Integer.parseInt(nextMonthPrice) > Integer.parseInt(currentMonthPrice)) {
			System.out.println("Lowest price of two months is :" + currentMonthPrice);
		} else {
			System.out.println("Lowest price of two months is :" + nextMonthPrice);
		}

	}

}
