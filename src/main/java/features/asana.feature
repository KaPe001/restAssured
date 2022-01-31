Feature: Testing new asana project

  @Get
  Scenario: Get workspace details
    Given i have workspace object
    When user performs GET workspace operation
    Then user is able to see valid response with workspace details

  @Post
  Scenario Outline: Create new project
    Given I save name data "<name>"
    And I save title data "<title>"
    When User performs asana POST project
    Then User is able to see response with new project name "<name>"

    Examples:
      | name        | title                                    |
      | SoupUI      | Nice title for SoupUI project            |
      | RestAssured | Even nicer title for RestAssured project |

  @Put
  Scenario: Update my project
    Given I have new project named DemoRestAssured
    When User performs PUT request to update project
    Then User is able to see the change in a project named RestAssured