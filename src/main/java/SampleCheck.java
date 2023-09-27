import Pages.webPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;


import java.util.concurrent.TimeUnit;

public class SampleCheck {
    public static WebDriver driver=null;

    @BeforeClass
    public void firstRun()
    {
        System.out.println("<<<<<<<<<<<<First Execution Started>>>>>>>>>");
    }

    @BeforeTest
    public void launhBrowser() {
        System.out.println("<<<<<<<BeforeTest Annotation being Executed>>>>>>>");
        //System.setProperty("webdriver.chrome.driver", "//Users//user//Documents//Drivers//chromedriver_mac64//chromedriver");
        //driver = new ChromeDriver();
        driver = new SafariDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void checkForPageLoad()
    {
        System.out.println("<<<<<<< Before Method Annotation being Executed>>>>>>>");
        if(driver.getTitle().equalsIgnoreCase("Web form"))
        {
            System.out.println(driver.getTitle());
        }
        else {
            driver.quit();
        }
    }

    @Test
    public void checkFormElements() {
        webPage web =new webPage(driver);
        System.out.println("<<<<<<< checkFormElements: Test Annotation being Executed>>>>>>>");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        web.enterName("Karthick");
        web.enterPassword("Kartic@123");
        web.enterText("The Implicit Wait in Selenium is used to tell the web driver to wait for a certain amount of time before it throws a “No Such Element Exception”. The default setting is 0. Once we set the time, the web driver will wait for the element for that time before throwing an exception.");
        if (web.checkDisabled() == false) {
            System.out.println("Yes!! The field is disabled");
            if (web.isReadOnly().equalsIgnoreCase("Readonly input")) {
                System.out.println("Yes!! The field is readOnly"+ "the value of readOnly field is");
                Select sel = new Select(driver.findElement(By.name("my-select")));
                sel.selectByValue("2");
                if (sel.getFirstSelectedOption().getText().equalsIgnoreCase("Two")) {
                    System.out.println("The selected value is" + sel.getFirstSelectedOption().getText());
                    //driver.findElement(By.name("my-datalist")).click();
                    WebDriverWait wait = new WebDriverWait(driver, 20);
                    if (driver.findElement(By.xpath("//input[@id='my-check-1']")).isSelected()) {
                        //Unselect
                        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='my-check-1']")))).click();
                        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='my-check-2']")))).click();
                        if (driver.findElement(By.xpath("//input[@id='my-radio-1']")).isSelected()) {
                            //Unselect
                            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='my-radio-1']")))).click();
                            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='my-radio-2']")))).click();
                        } else {
                            driver.quit();
                        }
                    } else {
                        driver.quit();
                    }

                } else {
                    driver.quit();
                }
            } else {

                driver.quit();
            }
        } else {

            Reporter.log("<<< Execution Terminated>>>>");
        }
    }

    @Test
    public void checkLink() {
        System.out.println("<<<<<<< checkLink : Test Annotation being Executed>>>>>>>");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a")).click();
        System.out.println("The redirected link is"+ driver.getTitle());
    }

    @AfterMethod
    public void logCheck() {
        System.out.println("<<<<<<< After Method Annotation being Executed>>>>>>>");
        System.out.println("--------------------Test case Executed Successfully----------------------");
    }

    @AfterTest
    public void closeTest() {
        System.out.println("<<<<<<< After Test Annotation being Executed>>>>>>>");
        Reporter.log("<<< Execution Terminated>>>>");
        driver.quit();
        System.out.println("--------------------All Tests Executed Successfully----------------------");
    }

    @AfterClass
    public void finalRun()
    {
        System.out.println("<<<<<<<<<<<<Final Execution Completed>>>>>>>>>");
    }

}
