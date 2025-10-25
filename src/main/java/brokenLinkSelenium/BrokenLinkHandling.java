package brokenLinkSelenium;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinkHandling {

	public static void main(String[] args) {
		WebDriver wd = new ChromeDriver();
		wd.get("https://practice.expandtesting.com/dynamic-table#google_vignettte");
		By linkLocator = By.xpath("//a");
		List<WebElement> links = wd.findElements(linkLocator);
		for (WebElement link : links) {
			String linkUrl = link.getAttribute("href");
			checkBrokenLink(linkUrl);
		}

		wd.quit();

	}

	private static void checkBrokenLink(String linkUrl) {
		try {
			URL url = new URL(linkUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
			int responseCode = connection.getResponseCode();
			if(responseCode>=400) {
				System.out.println("broken link -"+ linkUrl);
			}else {
				System.out.println("Valid link -"+ linkUrl);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
