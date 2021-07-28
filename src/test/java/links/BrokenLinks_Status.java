package links;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLinks_Status {

    public static void main(String[] args) throws InterruptedException, IOException {

        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Drivers\\Driver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://pensive-darwin.theproteinworks.com/products/proteinshakes");
        Thread.sleep(4000);

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println(links.size());

        for (int i = 0; i < links.size(); i++)
        // By using href attribute, we can get URL of the required link
        {
            WebElement element = links.get(i);
            String url = element.getAttribute("href");

            URL link = new URL(url);

            // Create a connection using url link object 'link'
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            Thread.sleep(2000);

            //establish connection
            httpConn.connect();

            //// return response code. If resp code is above 404:broken
            int respcode = httpConn.getResponseCode();
            if (respcode >= 404) {
                System.out.println(url + " - " + " is broken link");
            } else {
                System.out.println(url + " - " + " is valid link");
            }

        }
    }

}