package testclasses;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;


import java.util.List;

public class OrangeAdminFunctionTest extends BaseClassTest {

    HomePage homePage;




    @Test (priority = 0)
    public void logintoCRM(){
        String URL = "https://www.saucedemo.com/";
        getURL(URL);
        homePage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(homePage.titleofthePage(), "Products");

    }

    @Test (priority = 1)
    public void itemsCount(){

        int products_count = homePage.getProductsCount();
        Assert.assertEquals(products_count, 6);
    }


    @Test(priority = 2)
    public void compareproductDetails(){
    String prodNameonHomePage;
    String prodNameonDescription;
    List<WebElement> prodDetails = homePage.getProductNamesonthePage();

    for (int i=0; i< prodDetails.size(); i++){
        WebElement element = prodDetails.get(i);
        prodNameonHomePage = element.getText();
        System.out.println(prodNameonHomePage);

        element.click();
        prodNameonDescription = homePage.getProductnameonDescription();
        System.out.println(prodNameonDescription);
        Assert.assertEquals(prodNameonDescription, prodNameonHomePage, "Product Name not matched with home page and details page");
        homePage.backtoProducts();
        prodDetails = homePage.getProductNamesonthePage();


    }


}


}
