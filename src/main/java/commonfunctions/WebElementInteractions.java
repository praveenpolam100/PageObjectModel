package commonfunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class WebElementInteractions {

    protected WebDriver driver;

    public WebElementInteractions(WebDriver driver){
        this.driver = driver;
    }


    public void click(By locator){

        driver.findElement(locator).click();

    }

    public void sendKeys(By locator, String text){

        driver.findElement(locator).sendKeys(text);
    }

    public void getURL(String str){

        driver.get(str);
    }

    public boolean isDisplayed(By locator){

        return driver.findElement(locator).isDisplayed();
    }

    public WebElement locatorToWebElement(By locator){

       WebElement element = driver.findElement(locator);
       return element;
    }
}
