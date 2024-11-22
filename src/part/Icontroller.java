package part;

import java.time.LocalDate;

/**
 * Interface for the Team Controller.
 */
public interface Icontroller {
  /**
   * Add a player to the team.
   *
   * @param firstName         the first name of the player
   * @param lastName          the last name of the player
   * @param birthDate         the birth date of the player
   * @param preferredPosition the preferred position of the player
   * @param skillLevel        the skill level of the player
   */
  void addPlayer(String firstName, String lastName, LocalDate birthDate, Position preferredPosition,
                 int skillLevel);

  void buildTeam();

  void showAllPlayers();

  void showStartingLineup();
}