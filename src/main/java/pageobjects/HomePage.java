package pageobjects;

import commonfunctions.WebElementInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends WebElementInteractions {

   public HomePage(WebDriver driver){
       super(driver);

    }

   /* private By searchBox = By.xpath(("//input[@placeholder='Search']"));
    private By adminLink = By.xpath("//span[text() = 'Admin']");
    private By pimLink = By.xpath("//span[text() = 'PIM']");
    private By leaveLink = By.xpath("//span[text() = 'Leave']");
    private By timeLink = By.xpath("//span[text() = 'Time']");
    private By recruitmentLink = By.xpath("//span[text() = 'Recruitment']");
    private By myInfoLink = By.xpath("//span[text() = 'My Info']");
    private By perfLink = By.xpath("//span[text() = 'Performance']");
    private By dashboardLink = By.xpath("//span[text() = 'Dashboard']");
    private By directLink = By.xpath("//span[text() = 'Directory']");
    private By maintink = By.xpath("//span[text() = 'Maintenenance']");
    private By claimLink = By.xpath("//span[text() = 'Claim']");
    private By buzzLink = By.xpath("//span[text() = 'Buzz']");

    private By addUserButton = By.xpath("//button[text()=' Add ']");*/

    private By title = By.xpath("//span[@class='title' and @data-test='title']");

    private By inventoryitemName = By.xpath("//div[@class='inventory_item_name ']");
    private By inventoryitemName_on_description = By.xpath("//div[@data-test='inventory-item-name']");

    private By backtoproducts = By.id("back-to-products");

    public String titleofthePage(){
        return driver.findElement(title).getText();

    }

    public List<WebElement> getProductNamesonthePage(){

        return driver.findElements(inventoryitemName);

    }

    public int getProductsCount(){

        List <WebElement> products = getProductNamesonthePage();
        return products.size();
    }

    public void openProductDetails(int index){

        List<WebElement> prodDetails = getProductNamesonthePage();
        prodDetails.get(index).click();
    }

    public String getProductnameonDescription(){
        return driver.findElement(inventoryitemName_on_description).getText();

    }

    public void backtoProducts(){
        click(backtoproducts);
    }












}
