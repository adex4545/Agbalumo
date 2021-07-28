package links;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLinks {

    public static void main(String[] args) throws InterruptedException, IOException {

        System.setProperty("webdriver.edge.driver","C:\\Program Files\\Drivers\\Driver\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        driver.get("https://pensive-darwin.theproteinworks.com/products/proteinshakes");
        Thread.sleep(4000);

      /*  List<WebElement> links = driver.findElements(By.tagName("a"));
        int count = links.size();
        System.out.println("Total number of links " +  count);

        for( WebElement ele: links)
            System.out.println( ele.getAttribute("href"));    */

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println( links.size());

        for(int i =0; i<links.size(); i++)
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

            //// return response code. If res code is above 301:broken
            int rescode=httpConn.getResponseCode();
            if(rescode>=301){
                System.out.println( url + " - " + " is broken link");
            }
            else{
                System.out.println( url + " - " + " is valid link");
            }

        }
    }
}
