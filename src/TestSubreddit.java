
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/*
	As a user with niche interests
 	I want to access specific subreddits
 	So that I can see more specific content
 */
public class TestSubreddit
{
	static WebDriver driver = new HtmlUnitDriver();

	public void setUp()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	/*
 		Given a particular subreddit that exists
	 	When the user accesses that subreddit's URL
	 	Then the page should have the subreddit's name in the title bar
	 */
	@Test
	public void testSubredditAccessByURL()
	{
		driver.get("https://www.reddit.com/r/youtubehaiku/");
		String title = driver.getTitle();
		assertEquals(title, "Youtube Haiku");
	}

	/*
	 	Given the user is on the front page
		And a particular subreddit that exists
		When the user enters the subreddit name in the search bar
		And they click the first search result
		Then the page should have the subreddit's name in the title bar
	 */
	@Test
	public void testSubredditAccessBySearch()
	{
		driver.get("https://www.reddit.com/");
		
		try
		{
			WebElement search = driver.findElement(By.id("search"));
			search.findElement(By.name("q")).sendKeys("Youtube Haiku\n");
		
			WebElement result = driver.findElement(By.className("search-result"));
			result.findElement(By.tagName("a")).click();
		
			String title = driver.getTitle();
			assertEquals(title, "Youtube Haiku");
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the search bar or search result");
		}
	}

	/*
		Given the user is in a subreddit
		When they click the wiki tab
		Then a wiki should be displayed for the subreddit
	 */
	@Test
	public void testSubredditWiki()
	{
		driver.get("https://www.reddit.com/r/youtubehaiku/");
		try
		{
			driver.findElement(By.linkText("wiki")).click();
			WebElement wiki = driver.findElement(By.className("wiki"));	
			assertTrue(wiki.isDisplayed());
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the wiki tab or wiki content");
		}
	}
	
	/*
		Given the user is in a subreddit
		When they click the search bar
		Then there should be an option to restrict the search to that subreddit
	 */
	@Test
	public void testSubredditSearchSubreddit()
	{
		driver.get("https://www.reddit.com/r/youtubehaiku/");
		try
		{
			WebElement search = driver.findElement(By.id("search"));
			search.click();
			WebElement restrict = search.findElement(By.name("restrict_sr"));
			assertTrue(restrict.isEnabled());
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the search bar or checkbox");
		}
	}

	/*
		Given the user is in a subreddit
		Then there should be a link to send a message to the moderators
	 */
	@Test
	public void testSubredditMessageMods()
	{
		driver.get("https://www.reddit.com/r/youtubehaiku/");
		try
		{
			WebElement msg_link = driver.findElement(By.linkText("message the moderators"));
			assertTrue(msg_link.isDisplayed());
		}
		catch (NoSuchElementException e)
		{
			fail("couldn't find the link to message the mods");
		}
	}
}