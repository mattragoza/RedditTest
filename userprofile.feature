Feature:
	As an internet troll
	I want to view other user’s profiles
	So that I can make fun of their comment history
 
	Scenario:
		Given another reddit user that exists
		When the user accesses that other user's profile
		The page name should be the other user's name

	Scenario:
		Given another reddit user that exists
		And they have not blocked this user
		When the user accesses the other user's profile
		There should be a link to send them a private message

	Scenario:
		Given another reddit user that exists
		When the user accesses the other user's profile
		There should say the amount of karma the other user has

	Scenario:
		Given another reddit user that exists
		And this other user has commented
		When the user clicks on the context link for one of the other user's comments
		There should be the original comment in the thread it was posted in

	Scenario:
		Given another reddit user that exists
		When the user accesses the other user's profile
		There should be a box with any trophies the other user has

