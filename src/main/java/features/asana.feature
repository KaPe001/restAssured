Feature: Testing new asana project

  @Get
  Scenario: get workspace details
    Given i have workspace object
    When user performs GET workspace operation
    Then user is able to see valid response with workspace details

  @Post
  Scenario Outline: create new project
    Given i have a new project object
    When user performs asana POST project
    Then User is able to see response with new project

    Examples:
    |params|
    |workspaceID=,