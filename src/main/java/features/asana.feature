Feature: Testing new asana project

  @Get
  Scenario: get workspace details
    Given i have workspace object
    When user performs GET workspace operation
    Then user is able to see valid response with workspace details

  @Post
  Scenario Outline: create new project
    Given I save name data "<name>"
    And I save title data "<title>"
    When User performs asana POST project
    Then User is able to see response with new project "<name>"

    Examples:
      | name        | title                                    |
      | SoupUI      | Nice title for SoupUI project            |
      | RestAssured | Even nicer title for RestAssured project |