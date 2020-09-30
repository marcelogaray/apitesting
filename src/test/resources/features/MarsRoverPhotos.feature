Feature: Mars Rover Photos
  Challenge for retrieves different body responses and validate them

  @SmokeTesting
  Scenario Outline: Validate that the amounts of pictures that each "Curiosity" camera took on 1000 Mars sol and Earth date
  Given I get ApiKey
  When I Get the first <Number Photos> Mars photos made by <Rover Camera> Rover Cameras on <Attribute> Martian sol
    And I Get the first <Number Photos> Mars photos made by <Rover Camera> Rover Cameras on <Earth date> Earth date equal to <Attribute> Martian sol
  Then I compare the first Martial sol list with Earth date list
    And verify the amounts of picture Mars Sol are not greater than 10 times the amount taken by other cameras
    Examples:
      | Number Photos | Rover Camera | Attribute | Earth date |
      | 10            | Curiosity    | 1000      | 2015-05-30 |