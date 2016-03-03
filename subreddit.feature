Feature:
	As a user with niche interests
 	I want to access specific subreddits
 	So that I can see more specific content
 	
 	Scenario:
 		Given a particular subreddit that exists
	 	When the user accesses that subreddit's URL
	 	Then the page should have the subreddit's name in the title bar
	 	
	 Scenario:
	 	Given the user is on the front page
		And a particular subreddit that exists
		When the user enters the subreddit name in the search bar
		And they click the first search result
		Then the page should have the subreddit's name in the title bar
		
	Scenario:
		Given the user is in a subreddit
		When they click the wiki tab
		Then a wiki should be displayed for the subreddit
		
	Scenario:
		Given the user is in a subreddit
		When they click the search bar
		Then there should be an option to restrict the search to that subreddit
		
	Scenario:
		Given the user is in a subreddit
		Then there should be a link to send a message to the moderators