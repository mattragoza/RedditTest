Feature:
	As a newbie user
	I want to check out the front page
	So that I can see what reddit is like in general

	Scenario:
		Given the user is on the front page
		Then the title should say that reddit is the front page of the internet

	Scenario:
		Given the user is on the front page
		Then there should be multiple content links to click on

	Scenario:
		Given the user is on the front page
		When they click the sign up link
		Then a registration form should appear

	Scenario:
		Given the user is on the front page
		And there is at least one link
		When they go to the comments section of the link
		Then there should be comments there

	Scenario:
		When the user clicks on the alien image in the header
		Then they should be taken back to the front page
