import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/*
	As an internet troll
	I want to view other user’s profiles
	So that I can make fun of their comment history
 */
public class TestUserProfile
{
	static WebDriver driver = new HtmlUnitDriver();

	public void setUp()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	/*
		Given another reddit user that exists
		When the user accesses that other user's profile
		The page name should be the other user's name
	 */
	@Test
	public void testUserProfileByURL()
	{
		driver.get("https://www.reddit.com/user/DepressedGhost");
		try
		{
			String name = driver.findElement(By.className("pagename")).getText();
			assertEquals(name, "DepressedGhost");
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the page name");
		}
	}
	
	/*
		Given another reddit user that exists
		And they have not blocked the user
		When the user accesses the other user's profile
		There should be a link to send them a private message
	 */
	@Test
	public void testUserProfileSendPM()
	{
		driver.get("https://www.reddit.com/user/DepressedGhost");
		try
		{
			WebElement pm_link = driver.findElement(By.partialLinkText("private message"));
			assertTrue(pm_link.isDisplayed());
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the pm link");
		}
	}
	
	/*
		Given another reddit user that exists
		When the user accesses the other user's profile
		There should say the amount of karma the other user has
	 */
	@Test
	public void testUserProfileKarma()
	{
		driver.get("https://www.reddit.com/user/DepressedGhost");
		try
		{
			WebElement karma = driver.findElement(By.className("karma"));
			assertTrue(karma.isDisplayed());
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the karma display");
		}
	}

	/*
		Given another reddit user that exists
		And this other user has commented
		When the user clicks on the context link for one of the other user's comments
		There should be the original comment in the thread it was posted in
	 */
	@Test
	public void testUserProfileCommentContext()
	{
		driver.get("https://www.reddit.com/user/DepressedGhost/comments");
		try
		{
			WebElement entry = driver.findElement(By.className("entry")); 
			String comment_id = entry.findElement(By.name("thing_id")).getAttribute("value");
			entry.findElement(By.partialLinkText("context")).click();
			WebElement original = driver.findElement(By.cssSelector("input[value=" + comment_id + "]"));
			assertTrue(original != null);
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find a comment entry, its ID, or the original comment");
		}
	}
	
	/*
		Given another reddit user that exists
		When the user accesses the other user's profile
		There should be a box with any trophies the other user has
	 */
	@Test
	public void testUserProfileTrophyBox()
	{
		driver.get("https://www.reddit.com/user/DepressedGhost");
		try
		{
			WebElement trophy_box = driver.findElement(By.className("trophy-table")); 
			assertTrue(trophy_box.isDisplayed());
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the trophy box");
		}
	}
	

}
