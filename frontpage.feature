Feature:
	As a newbie user
	I want to check out the front page
	So that I can see what reddit is like before signing up

	Scenario:
		Given I am on the front page
		Then the title should say that reddit is the front page of the internet

	Scenario:
		Given I am on the front page
		Then there should be multiple content links to click on

	Scenario:
		Given I am on the front page
		When I click the sign up link
		Then I should see a registration form

	Scenario:
		Given I am on the front page
		And there is at least one link
		When I go to the comments section of the link
		Then there should be comments

	Scenario:
		When I click on the alien image in the header
		Then I should be taken back to the front page
