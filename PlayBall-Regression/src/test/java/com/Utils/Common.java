package com.Utils;

import com.base.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class Common extends BaseClass {

    public String getScreenshotPath(String testCaseName) throws IOException {
        TakesScreenshot scrShot =((TakesScreenshot) driver);
        File source = scrShot.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }

    /**
     * To verify actual result given as True/False
     * If result found false, then it will message replace "is " with "is not " to log in the report
     */
    public void assertionTrue(boolean condition, String message){
        Assert.assertTrue(condition, message.replace("is ", "is not "));
        log.info(message);
    }

    /**
     * To verify actual result given as True/False
     * If result found True, then it will message replace "is not" with "is " to log in the report
     */
    public void assertionFalse(boolean condition, String message){
        Assert.assertFalse(condition, message.replace("is not", "is"));
        log.info(message);
    }

    /**
     * To verify actual result given as Equality with True/False
     * If result found false, then it will message replace "is " with "is not " to log in the report
     */
    public void assertionEquals(Object expected, Object actual, String message){
        Assert.assertEquals(expected, actual, message.replace("is ", "is not "));
        log.info(message.replace("is ", "is not "));
    }
}
