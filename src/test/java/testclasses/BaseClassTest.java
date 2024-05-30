package testclasses;

import base.AppConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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

    private ChromeOptions chromeOptions;
    private EdgeOptions edgeOptions;
    private FirefoxOptions firefoxOptions;


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
                chromeOptions = new ChromeOptions();
                chromeOptions.setPlatformName("linux");
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                URL url;
                try {
                    //url = new URL("http://localhost:4444/wd/hub");
                    url = new URL("http://localhost:4444/wd/hub");
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                driver = new RemoteWebDriver(url, chromeOptions);

            }else if (AppConstants.platform.equalsIgnoreCase("remote_git")){
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);

            }
        }else if (browserName.equalsIgnoreCase("Edge")) {
                if (AppConstants.platform.equalsIgnoreCase("local")) {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                }
                else if (AppConstants.platform.equalsIgnoreCase("remote")){
                    edgeOptions = new EdgeOptions();
                    edgeOptions.setPlatformName("linux");
                    edgeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                URL url;
                try {
                    //url = new URL("http://localhost:4445/wd/hub");
                    url = new URL("http://localhost:4444/wd/hub");
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                driver = new RemoteWebDriver(url, edgeOptions);
                loginPage = new LoginPage(driver);
                }
                else if (AppConstants.platform.equalsIgnoreCase("remote_git")) {
                    edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--start-maximized");
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("--disable-gpu");
                    edgeOptions.addArguments("--no-sandbox");
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(edgeOptions);

                }
        }else if (browserName.equalsIgnoreCase("Firefox")){
            if (AppConstants.platform.equalsIgnoreCase("local")) {
                FirefoxDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            }
            else if (AppConstants.platform.equalsIgnoreCase("remote")){
                firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPlatformName("linux");
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                URL url;
                try {
                    //url = new URL("http://localhost:4445/wd/hub");
                    url = new URL("http://localhost:4444/wd/hub");
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                driver = new RemoteWebDriver(url, firefoxOptions);
                loginPage = new LoginPage(driver);
            }
            else if (AppConstants.platform.equalsIgnoreCase("remote_git")) {
                firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--disable-gpu");
                firefoxOptions.addArguments("--no-sandbox");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);

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

