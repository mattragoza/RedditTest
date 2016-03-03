Feature:
	As a user with niche interests
 	I want to access specific subreddits
 	So that I can see more specific content
 	
 	Scenario:
 		Given a particular subreddit that exists
	 	When I access that subreddit's URL
	 	Then I should see the subreddit's name in the title bar
	 	
	 Scenario:
	 	Given I am on the front page
		And a particular subreddit exists
		When I enter that subreddit name in the search bar
		And then click the first search result
		Then I should see the subreddit's name in the title bar
		
	Scenario:
		Given I am in a subreddit
		When I click the wiki tab
		Then I should see a wiki about the subreddit
		
	Scenario:
		Given I am in a subreddit
		When I click the search bar
		Then I should have the option to restrict the search to that subreddit
		
	Scenario:
		Given I am in a subreddit
		Then there should be a link to send a message to the moderators