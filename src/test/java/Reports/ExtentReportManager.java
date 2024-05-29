package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {

    public static ExtentReports createInstance(String fileName, String reportName, String documentTitle){

        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);
        extentSparkReporter.config().setReportName(reportName);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setDocumentTitle(documentTitle);
        extentSparkReporter.config().setEncoding("utf-8");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        return extentReports;

    }

    public static String getReportNameWithTimeStamp(){


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd-HH-mm-ss");
        LocalDateTime localDateTime = LocalDateTime.now();

        String fileName = "Test" + dateTimeFormatter.format(localDateTime) + ".html";
        return fileName;


    }



}
