package es.udc.paproject.e2etests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.time.Duration;
import java.util.List;

class AppTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testLogin() {
        // Exercise
        driver.get("http://localhost:3000");
        String title = driver.getTitle();
        WebElement login = driver.findElement(By.className("nav-link"));
        login.click();
        WebElement userNameBox = driver.findElement(By.id("userName"));
        WebElement passwordBox = driver.findElement(By.id("password"));
        userNameBox.sendKeys("test");
        passwordBox.sendKeys("test");
        By authLocator = RelativeLocator.with(By.className("btn-primary")).below(passwordBox);
        WebElement authBtn = driver.findElement(authLocator);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        authBtn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebElement userHeader = driver.findElement(By.className("dropdown-toggle"));

        // Verify
        assertTrue(userHeader.getText().contains("test"));
    }

    @Test
    void testAnnounceProduct() {
        //Authentication
        testLogin();
        WebElement userHeader = driver.findElement(By.className("dropdown-toggle"));
        userHeader.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebElement announceProdLink = driver.findElement(By.id("announceProd"));
        announceProdLink.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        //AnnounceProduct
        WebElement nameBox = driver.findElement(By.id("name"));
        nameBox.sendKeys("MSI Stealth");
        WebElement descBox = driver.findElement(By.id("description"));
        descBox.sendKeys("The best laptop on the market.");
        WebElement minutesBox = driver.findElement(By.id("minutes"));
        minutesBox.sendKeys("150000");
        WebElement sPriceBox = driver.findElement(By.id("startPrice"));
        sPriceBox.sendKeys("15");
        WebElement shipInfoBox = driver.findElement(By.id("shipInfo"));
        shipInfoBox.sendKeys("c/ Pintor Corredoira n6A");
        WebElement categoryBox = driver.findElement(By.id("categorySelect"));
        categoryBox.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        categoryBox.sendKeys("po");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebElement announceBtn = driver.findElement(By.id("announceBtn"));
        announceBtn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        //UserProducts
        WebElement userProdBtn = driver.findElement(By.id("findUserProd"));
        userProdBtn.click();
        WebElement prodTable = driver.findElement(By.className("table"));

        //Verify
        assertTrue(prodTable.getText().contains("MSI Stealth"));

    }
    @Test
    void testPujar() {
        // Login
        testLogin();

        // Pujar
        driver.get("http://localhost:3000/products/product-details/3");
        WebElement priceInput = driver.findElement(By.id("price"));
        priceInput.clear();
        priceInput.sendKeys("560");
        By sendButtonLocator = RelativeLocator.with(By.className("btn-primary")).below(priceInput);
        WebElement pujarBtn = driver.findElement(sendButtonLocator);
        pujarBtn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement alerta = driver.findElement(By.className("alert"));
        assertTrue(alerta.isDisplayed());

        // Buscar pujas usuario
        driver.findElement(By.id("userBids")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebElement botonBuscar = driver.findElement(By.className("btn"));
        By pujaLocator = RelativeLocator.with(By.tagName("a")).below(botonBuscar);
        List<WebElement> pujasUser = driver.findElements(pujaLocator);
        for(WebElement puja : pujasUser) {
            assertTrue(puja.getText().contains("Pantalla 1"));
            break;
        }
    }

}