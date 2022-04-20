package org.example.HomeWork7;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UsefulUtils {

        public static File makeScreenshot(WebDriver driver, String filename) {
            File temp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("./target/" + filename);
            try {
                FileUtils.copyFile(temp, destination);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return destination;
        }

        public static String getLogs(WebDriver driver) throws IOException {
            String fileName = "./target/browserLogs-org.example.com.automationpractice.test-" + System.currentTimeMillis() + ".txt";
            PrintWriter logFile = new PrintWriter( fileName, "UTF-8");
            LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
            List<LogEntry> allLogRows = browserLogs.getAll();
            if (allLogRows.size() > 0 ) {
                allLogRows.forEach(logEntry -> {
                    String message = logEntry.getMessage();
                    System.out.println(message);
                    logFile.println(message);
                });
            }
            logFile.close();
            return fileName;
        }

}
