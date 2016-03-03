import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/*
	As a newbie user
	I want to check out the front page
	So that I can see what reddit is like before signing up
 */

public class TestFrontPage
{
	static WebDriver driver = new HtmlUnitDriver();

	public void setUp()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	/*
		Given I am on the front page
		Then the title should say that reddit is the front page of the internet
	 */
	@Test
	public void testFrontPageTitle()
	{
		driver.get("https://www.reddit.com/");
		String title = driver.getTitle();
		assertEquals(title, "reddit: the front page of the internet");
	}

	/*
		Given I am on the front page
		Then there should be multiple content links to click on
	 */
	@Test
	public void testFrontPageLinks()
	{
		driver.get("https://www.reddit.com/");
		try
		{
			Integer link_count = driver.findElements(By.className("thing")).size();
			assertTrue(link_count > 1);
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find any links");
		}
	}

	/*
		Given I am on the front page
		When I click the sign up link
		Then I should see a registration form
	 */
	@Test
	public void testFrontPageSignUpForm()
	{
		driver.get("https://www.reddit.com/");
		try
		{
			WebElement signup_link = driver.findElement(By.partialLinkText("sign up"));
			signup_link.click();
			WebElement form = driver.findElement(By.id("register-form"));
			assertTrue(form.isDisplayed());
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the sign up link or register form");
		}
	}
	
	/*
		Given I am on the front page
		And there is at least one link
		When I go to the comments section of the link
		Then there should be comments
	 */
	@Test
	public void testFrontPageComments()
	{
		driver.get("https://www.reddit.com/");
		try
		{
			driver.findElement(By.partialLinkText("comments")).click();
			int num_comments = driver.findElements(By.className("usertext-body")).size();
			assertTrue(num_comments > 0);
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the comments link or any comments");
		}
	}
	
	/*
		When I click on the alien image in the header
		Then I should be taken back to the front page
	 */
	@Test
	public void testFrontPageAlienLink()
	{
		driver.get("https://www.reddit.com/subreddits");
		try
		{
			driver.findElement(By.id("header-img")).click();
			String url = driver.getCurrentUrl();
			assertEquals(url, "https://www.reddit.com/");
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the alien image");
		}
	}
}
