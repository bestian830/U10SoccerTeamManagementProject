package part;

import java.time.LocalDate;

/**
 * Instance of Player of Interface of Iplayer.
 */
public class Player implements Iplayer {
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private Position preferredPosition;
  private Position assignedPosition;
  private int skillLevel;
  private int jerseyNumber;

  /**
   * Constructs a new Player with the specified details.
   *
   * @param firstName the first name of the player
   * @param lastName the last name of the player
   * @param birthDate the birth date of the player
   * @param preferredPosition the preferred position of the player
   * @param skillLevel the skill level of the player, must be between 1 and 5
   * @throws IllegalArgumentException if the skill level is not between 1 and 5
   */
  public Player(String firstName, String lastName, LocalDate birthDate, Position preferredPosition,
                int skillLevel) {
    if (skillLevel <= 0 || skillLevel >= 6) {
      throw new IllegalArgumentException("Skill level must be between 1 and 5.");
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.preferredPosition = preferredPosition;
    this.skillLevel = skillLevel;
  }

  @Override
  public String getFullName() {
    return firstName + " " + lastName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public boolean isEligible() {
    return birthDate.isAfter(LocalDate.now().minusYears(10));
  }

  @Override
  public int getSkillLevel() {
    return skillLevel;
  }

  @Override
  public Position getPreferredPosition() {
    return preferredPosition;
  }

  @Override
  public void setJerseyNumber(int jerseyNumber) {
    this.jerseyNumber = jerseyNumber;
  }

  @Override
  public int getJerseyNumber() {
    return jerseyNumber;
  }

  @Override
  public Position getAssignedPosition() {
    return assignedPosition;
  }

  @Override
  public void setAssignedPosition(Position assignedPosition) {
    this.assignedPosition = assignedPosition;
  }
}
