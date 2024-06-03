package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import testclasses.BaseClassTest;





public class Setup implements ITestListener{

    ExtentReports extentReports;
    ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extentReports.createTest(result.getMethod().getMethodName());
        test.set(extentTest);



    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    public void onTestSuccess(ITestResult result) {

        ExtentTest extentTest = test.get();

       // Object currentClass = result.getInstance();

        List<String> messages = Reporter.getOutput(result);

        for (String message: messages){

            extentTest.log(Status.PASS, message);
        }


    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = test.get();

        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseClassTest)currentClass).getDriver();
        String filePath = null;

        if(ITestResult.FAILURE == result.getStatus()){

            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
            Date date = new Date(System.currentTimeMillis());
            long timeStamp = date.getTime();
            File destination = new File(System.getProperty("user.dir") + "//src//test//resources//screenshots//" +result.getName()+ timeStamp +".png");
            filePath = destination.getPath();

            try {
                FileHandler.copy(source, destination);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


        Throwable throwable = result.getThrowable();
        if (throwable!=null){
            extentTest.log(Status.FAIL, throwable.getMessage() + "Screenshot Below:" +extentTest.addScreenCaptureFromPath(filePath) );


        }else{
            extentTest.log(Status.FAIL, "Test Failed without an Exception. Screenshot below:" + extentTest.addScreenCaptureFromPath(filePath));
        }

    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    public void onTestSkipped(ITestResult result) {
        ExtentTest extentTest = test.get();
        extentTest.log(Status.SKIP, "Test Skipped");
    }


    /**
     * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
     * tag and calling all their Configuration methods.
     *
     * @param context The test context
     */
    public void onStart(ITestContext context) {

       String filePath =  System.getProperty("user.dir") + "//reports//" + ExtentReportManager.getReportNameWithTimeStamp();
        extentReports = ExtentReportManager.createInstance(filePath, "Automation Report", "Test Execution Report");

    }

    /**
     * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
     * run and all their Configuration methods have been called.
     *
     * @param context The test context
     */
    public void onFinish(ITestContext context) {
       if(extentReports!=null){
           extentReports.flush();
       }
    }





}
