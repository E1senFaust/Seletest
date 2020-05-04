//region Imports
package com.seletest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
//endregion

public class Ranotest {
    private WebDriver driver;

    //region Misc
    @BeforeTest
    public void Test(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ranorex.com/web-testing-examples/vip/");
    }

    @AfterTest
    public void Endtest(){
        driver.quit();
    }
//endregion

    //region Tests
    @Test
    public void DbDisconnect(){
        //FInd and click Disconnect button
        WebElement disconnectButton = driver.findElement(By.id("connect"));
        disconnectButton.click();

        //Check connection status indicator
        WebElement connectionStatus = driver.findElement(By.id("connection"));
        assertEquals(connectionStatus.getText().toString(), "Offline", "Was not Offline");
    }

    @Test
    public void AddPerson(){
        //Found the First name textbox, click on it  and fill it in with valid data
        WebElement firstNameInput = driver.findElement(By.id("FirstName"));
        firstNameInput.click();
        firstNameInput.sendKeys("Roboute");

        //Found the Last name textbox, click on it  and fill it in with valid data
        WebElement lastNameInput = driver.findElement((By.id("LastName")));
        lastNameInput.click();
        lastNameInput.sendKeys("Guiliman");

        //Find Category "Politics and click on it"
        WebElement politicsCategory = driver.findElement(By.xpath("(//option[text()='Politics'])"));
        politicsCategory.click();

        //Find radiobutton Male and click on it
        WebElement maleRadiobutton = driver.findElement(By.xpath("(//*[@id=\"Gender\"])[2]"));
        WebElement femeleRadiobutton = driver.findElement(By.xpath("(//*[@id=\"Gender\"])[1]"));
        maleRadiobutton.click();

        //Check that Female radiobutton became inactive after click on Male radio
        assertFalse(femeleRadiobutton.isSelected(), "Female radio is activated");

        //Check that Male radiobutton became active after click on it
        assertTrue(maleRadiobutton.isSelected(), "Male radio is not active");

        //Find Add button and click on it
        WebElement addButton = driver.findElement(By.id("Add"));
        addButton.click();

        //Checking the added record
        WebElement firstNameEntered = driver.findElement(By.xpath("(//td[text()='Roboute'])"));
        WebElement lastNameEntered = driver.findElement((By.xpath("(//td[text()='Guiliman'])")));
        WebElement vipCount = driver.findElement(By.xpath("//*[@id=\"count\"]"));
        assertEquals(firstNameEntered.getText().toString(), "Roboute");
        assertEquals(lastNameEntered.getText().toString(), "Guiliman");
        assertEquals(vipCount.getText().toString(), "VIP count: 0", "VIP count is not equal 1");
    }
//endregion

}
