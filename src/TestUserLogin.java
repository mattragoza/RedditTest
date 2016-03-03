import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/*
	As a returning user
	I want to log in to my account
	So that I can waste more of my time on Reddit
 */
public class TestUserLogin
{
	static WebDriver driver;

	/*
		Given that you are not logged in,
		When you click the log in link,
		Then a log in form should apear.
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
		Given that you are not logged in,
		When you click the log in link,
		And then enter valid user info,
		Then you should be logged in as that user.
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
		Given that you are not logged in,
		When you click the log in link,
	 	And then enter invalid password,
		Then there should be an error saying wrong password.
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
			String error = driver.findElement(By.className("error")).getText();
			assertEquals(error, "wrong password");
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the error message");
		}
	}
	
	/*
		Given that you are logged in,
		When you click the submit link button,
		Then there should be a form to submit a new link.
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
		Given that you are logged in,
		When you click the mail button,
		Then there should be able to see your message inbox.
	 */
	@Test
	public void testUserLoginMessages()
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
