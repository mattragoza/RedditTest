Feature:
	As a returning user
	I want to log in to my account
	So that I can waste more of my time on reddit

	Scenario:
		Given that I am not logged in
		When I click the log in link
		Then I should see a login form

	Scenario:
		Given that I am not logged in
		When I click the login link
		And then enter valid user information
		Then I should be logged in as that user

	Scenario:
		Given that I am not logged in
		When I click the login link
	 	And then enter an invalid password
		Then I should see an error message

	Scenario:
		Given that I am logged in
		When I click the submit link button
		Then I should see a form to submit a link

	Scenario:
		Given that I am logged in
		When I click the mail button
		Then I should see my message inbox
