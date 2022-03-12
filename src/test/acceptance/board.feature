# Created by santunioni at 3/12/22
Feature: # Basic board functionality
  # This test case ensures the board should be able to host a game in it.
  # Possible games are Chess and Checkers, but we should test the games as separate features.

  Scenario: # The game should hint what moves are allowed for a piece
    Given there are pieces in the board
    When I select a piece
    Then the game hints me where I can move this piece to

  Scenario: # We should be able to move pieces around the board
    Given there are pieces in the board
    When I move a piece
    Then I can see the piece in the new position
