import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;


import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.testng.Assert.assertTrue;
import static pages.PageObject_Elements.*;

public class End2end extends WDsettings {


    @Test
    public void orderEn() {
        // assertTrue(driver.getCurrentUrl().equalsIgnoreCase(site)); //TestNG
        assertThat(driver.getCurrentUrl(), is(equalTo(site))); //Hamcrest

        assertThat(driver.findElement(By.xpath(PRODUCT)), notNullValue()); //hamcrest check if product is visible
        driver.findElement(By.xpath(PRODUCT)).click();

        //assertTrue(driver.findElement(By.xpath("//*[@id=\"add\"]")).isDisplayed()); //add to basket button is shown
        assertThat(driver.findElement(By.xpath(ADD_TO_BASKET)), notNullValue()); //add to basket button is shown
        driver.findElement(By.xpath(ADD_TO_BASKET)).click(); //click on Add to basket

        //assertTrue(driver.findElement(By.xpath("//*[@aria-label=\"Continue to Basket\"]")).isDisplayed()); //add to basket button is shown
        //assertTrue(driver.findElement(By.id("\'//*[@id=\"continue\"]\'")).isDisplayed());
        driver.findElement(By.xpath(CONTINUE)).click(); //click on Continue button

        driver.findElement(By.xpath(PHONE_FIELD)).sendKeys(phone);
        driver.findElement(By.xpath(NAME_FIELD)).sendKeys(name);

        driver.findElement(By.xpath(ORDER_BUTTON)).click(); //click on ORDER

        assertThat(driver.findElement(By.xpath("//*[@class=\"order-confirmation__title\"]")), notNullValue());//hamcrest

        driver.findElement(By.xpath("//*[@id=\"cancel-order\"]")).click();

        driver.findElement(By.id("yes-modal")).click();

        assertThat(driver.findElement(By.xpath("//*[@class=\"order-cancelled__title\"]")), notNullValue());//hamcrest
    }

    @Test(enabled = true)
    public void orderFr() {

        //assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://digitalchit-stg.canadiantire.ca/store/0966/groups/150/products/"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label=\"passer au français\"]"))); //explicit waiter
        
        driver.findElement(By.xpath("//*[@aria-label=\"passer au français\"]")).click();

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/div[1]/a/div[2]")).click();
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://digitalchit-stg.canadiantire.ca/store/0966/groups/150/products/0304220"));

        assertTrue(driver.findElement(By.xpath("//*[@id=\"add\"]")).isDisplayed()); //add to basket button is shown
        driver.findElement(By.xpath("//*[@id=\"add\"]")).click(); //click on Add to basket

        //assertTrue(driver.findElement(By.xpath("//*[@aria-label=\"Continue to Basket\"]")).isDisplayed()); //add to basket button is shown
        //assertTrue(driver.findElement(By.id("\'//*[@id=\"continue\"]\'")).isDisplayed());
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click(); //click on Add to basket

        driver.findElement(By.xpath("//*[@data-testid=\"phone\"]")).sendKeys("9111111111");
        driver.findElement(By.xpath("//*[@data-testid=\"name\"]")).sendKeys("Alex M");

        driver.findElement(By.xpath("//*[@id=\"order\"]")).click(); //click on Add to basket

        assertTrue(driver.findElement(By.xpath("//*[@class=\"order-confirmation__title\"]")).isDisplayed());

        driver.findElement(By.xpath("//*[@id=\"cancel-order\"]")).click();

        driver.findElement(By.id("yes-modal")).click();

        assertTrue(driver.findElement(By.xpath("//*[@class=\"order-cancelled__title\"]")).isDisplayed());
        driver.quit(); //quit browser
        //driver.close(); //quit browser tab
    }
}