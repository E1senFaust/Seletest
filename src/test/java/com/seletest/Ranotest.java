package com.seletest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class Ranotest {
    private WebDriver driver;

    @BeforeTest
    public void Test(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ranorex.com/web-testing-examples/vip/");
    }

    @Test
    public void DbDisconnect(){
        String mainWindow = driver.getWindowHandle();
        System.out.println("==============="+mainWindow);
        WebElement disconnectButton = driver.findElement(By.id("connect"));
        disconnectButton.click();
        WebElement connectionStatus = driver.findElement(By.id("connection"));
        assertTrue (connectionStatus.getText().toString().equals("Offline"));
    }

    @Test
    public void AddPerson(){
        WebElement firstNameInput = driver.findElement(By.id("FirstName"));
        firstNameInput.click();
        firstNameInput.sendKeys("Roboute");
        WebElement lastNameInput = driver.findElement((By.id("LastName")));
        lastNameInput.click();
        lastNameInput.sendKeys("Guiliman");
        WebElement politicsCategory = driver.findElement(By.xpath("(//option[text()='Politics'])"));
        politicsCategory.click();
        WebElement addButton = driver.findElement(By.id("Add"));
        addButton.click();
        WebElement firstNameEntered = driver.findElement(By.xpath("(//td[text()='Roboute'])"));
        WebElement lastNameEntered = driver.findElement((By.xpath("(//td[text()='Guiliman'])")));
        assertEquals(firstNameEntered.getText().toString(), "Roboute");
        assertEquals(lastNameEntered.getText().toString(), "Guiliman");
    }

    @AfterTest
    public void Endtest(){
        driver.quit();
    }
}
