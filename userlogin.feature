Feature:
	As a returning user
	I want to log in to my account
	So that I can waste more of my time on Reddit

	Scenario:
		Given that you are not logged in,
		When you click the log in link,
		Then a log in form should apear.

	Scenario:
		Given that you are not logged in,
		When you click the log in link,
		And then enter valid user info,
		Then you should be logged in as that user.

	Scenario:
		Given that you are not logged in,
		When you click the log in link,
	 	And then enter invalid password,
		Then there should be an error saying wrong password.

	Scenario:
		Given that you are logged in,
		When you click the submit link button,
		Then there should be a form to submit a new link.

	Scenario:
		Given that you are logged in,
		When you click the mail button,
		Then there should be able to see your message inbox.
