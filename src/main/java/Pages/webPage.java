package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class webPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath= "//input[@name='my-text']")
    WebElement name;

    @FindBy(xpath="//input[@name='my-password']")
    WebElement password;

    @FindBy(xpath = "//textarea[@name='my-textarea']")
    WebElement text_area;

    @FindBy(xpath = "//input[@name='my-disabled']")
    WebElement disabled_field;

    @FindBy(xpath = "//input[@name='my-readonly']")
    WebElement read_only;



    public webPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        wait= new WebDriverWait(driver,10);
    }

    public void enterName(String val)
    {wait.until(ExpectedConditions.elementToBeClickable(name)).sendKeys(val); }

    public void enterPassword(String val)
    {wait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys(val); }

    public void enterText(String val)
    {wait.until(ExpectedConditions.elementToBeClickable(text_area)).sendKeys("val");}

    public boolean checkDisabled()
    {
        boolean flag= wait.until(ExpectedConditions.visibilityOf(disabled_field)).isEnabled();
        return flag;
    }

    public String isReadOnly()
    {
        String s= wait.until(ExpectedConditions.visibilityOf(read_only)).getAttribute("value");
        return s;
    }

}
