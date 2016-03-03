import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/*
	As a returning user
	I want to log in to my account
	So that I can waste more of my time on reddit
 */
public class TestUserLogin
{
	static WebDriver driver;

	/*
		Given that I am not logged in
		When I click the log in link
		Then I should see a login form
	 */
	@Test
	public void testUserLoginForm()
	{
		driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.reddit.com/");
		try
		{
			WebElement login_link = driver.findElement(By.linkText("Log in or sign up"));
			login_link.click();
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the log in link");
		}
		try
		{
			WebElement form = driver.findElement(By.id("login-form"));
			assertTrue(form.isDisplayed());
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the log in form");
		}
	}
	
	/*
		Given that I am not logged in
		When I click the login link
		And then enter valid user information
		Then I should be logged in as that user
	 */
	@Test
	public void testUserLoginSuccess()
	{
		driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.reddit.com/");
		try
		{
			WebElement form = driver.findElement(By.id("login_login-main"));
			WebElement username_input = form.findElement(By.name("user"));
			WebElement password_input = form.findElement(By.name("passwd"));
			username_input.sendKeys("cats_for_rent");
			password_input.sendKeys("rentacat");
			form.submit();
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the log in form");
		}
		try
		{
			WebElement user = driver.findElement(By.className("user"));
			String username = user.findElement(By.tagName("a")).getText();
			assertEquals(username, "cats_for_rent");
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the current username");
		}
		finally
		{
			driver.findElement(By.linkText("logout")).click();
		}
	}
	
	/*
		Given that I am not logged in
		When I click the login link
	 	And then enter an invalid password
		Then I should see an error message
	 */
	@Test
	public void testUserLoginFailure()
	{
		driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.reddit.com/");
		try
		{
			WebElement form = driver.findElement(By.id("login_login-main"));
			WebElement username_input = form.findElement(By.name("user"));
			WebElement password_input = form.findElement(By.name("passwd"));
			username_input.sendKeys("cats_for_rent");
			password_input.sendKeys("rentadog");
			form.submit();
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the log in form");
		}
		try
		{
			WebElement form = driver.findElement(By.id("login_login-main"));
			System.out.println(form.getText());
			//String status = form.findElement(By.className("status")).getText();
			//assertEquals(status, "wrong password");
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the error message");
		}
	}
	
	/*
		Given that I am logged in
		When I click the submit link button
		Then I should see a form to submit a link
	 */
	@Test
	public void testUserLoginSubmitLink()
	{
		driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.reddit.com/");
		WebElement form = driver.findElement(By.id("login_login-main"));
		form.findElement(By.name("user")).sendKeys("cats_for_rent");
		form.findElement(By.name("passwd")).sendKeys("rentacat");
		form.submit();

		try
		{
			WebElement submit = driver.findElement(By.linkText("Submit a new link"));
			submit.click();
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the submit link button");
		}
		try
		{
			WebElement newlink = driver.findElement(By.id("newlink"));
			assertTrue(newlink.isDisplayed());
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the new link form");
		}
	}
	
	/*
		Given that I am logged in
		When I click the mail button
		Then I should see my message inbox
	 */
	@Test
	public void testUserLoginInbox()
	{
		driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.reddit.com/");
		WebElement form = driver.findElement(By.id("login_login-main"));
		form.findElement(By.name("user")).sendKeys("cats_for_rent");
		form.findElement(By.name("passwd")).sendKeys("rentacat");
		form.submit();

		try
		{
			WebElement mail = driver.findElement(By.id("mail"));
			mail.click();
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the mail button");
		}
		try
		{
			WebElement inbox = driver.findElement(By.id("siteTable"));
			assertTrue(inbox.isDisplayed());
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the message inbox");
		}
	}
}
