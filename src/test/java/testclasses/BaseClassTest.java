package testclasses;

import base.AppConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import pageobjects.LoginPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static java.lang.System.exit;

public class BaseClassTest {


    protected String browserName;
    protected RemoteWebDriver driver;

    protected LoginPage loginPage;


    @BeforeTest
    @Parameters({"browserName"})
    public void setupTest(@Optional String xmlbrowserName) {


        browserName = (xmlbrowserName != null) ? xmlbrowserName : AppConstants.browserName;
        if (browserName.equalsIgnoreCase("Chrome")) {
            if (AppConstants.platform.equalsIgnoreCase("local")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            }else if (AppConstants.platform.equalsIgnoreCase("remote")){
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPlatformName("linux");
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                URL url = null;
                try {
                    //url = new URL("http://localhost:4444/wd/hub");
                    url = new URL("http://localhost:4444/wd/hub");
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                driver = new RemoteWebDriver(url, chromeOptions);



            }
        }else if (browserName.equalsIgnoreCase("Edge")) {
                if (AppConstants.platform.equalsIgnoreCase("local")) {
                    WebDriverManager.edgedriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                }
                else if (AppConstants.platform.equalsIgnoreCase("remote")){
                EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setPlatformName("linux");
                    edgeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                URL url = null;
                try {
                    //url = new URL("http://localhost:4445/wd/hub");
                    url = new URL("http://localhost:4444/wd/hub");
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                driver = new RemoteWebDriver(url, edgeOptions);
                loginPage = new LoginPage(driver);


             }
        }
        else {
                System.out.println("The supplied browsername is not supported");
                exit(0);
            }

            loginPage = new LoginPage(driver);

        }


        @AfterTest
        public void tearDown() {
            driver.quit();
        }

        //@Parameters({"URL"})
        public void getURL(String url){
            driver.get(url);
        }

        @AfterMethod
        public WebDriver getDriver(){
            return driver;

        }

    }

