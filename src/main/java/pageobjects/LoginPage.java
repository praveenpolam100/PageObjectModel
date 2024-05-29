package pageobjects;

import commonfunctions.WebElementInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends WebElementInteractions {


    public LoginPage(WebDriver driver){

        super(driver);

    }


    private By nuserName = By.id("user-name");
    private By npassword = By.id("password");
    private By xlogin = By.id("login-button");



    public HomePage login(String userName, String passWord){
        sendKeys(nuserName, userName);
        sendKeys(npassword, passWord);
        click(xlogin);
        return new HomePage(driver);

    }

}
