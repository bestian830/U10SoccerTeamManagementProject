package part;

import java.time.LocalDate;

/**
 * Controller class for the U10 Soccer Team application.
 */
public class Controller implements Icontroller {
  /**
   * The team.
   */
  private final Team team;
  private final View view;

  public Controller(Team team, View view) {
    this.team = team;
    this.view = view;
  }

  @Override
  public void addPlayer(String firstName, String lastName, LocalDate birthDate,
                        Position preferredPosition, int skillLevel) {
    try {
      Player player = new Player(firstName, lastName, birthDate, preferredPosition, skillLevel);
      team.addPlayer(player);
      view.displayMessage("Player added: " + player.getFullName());
    } catch (IllegalArgumentException e) {
      view.displayErrorMessage("Error: " + e.getMessage());
    }
  }

  @Override
  public void buildTeam() {
    team.buildTeam();
    view.displayMessage("Team created successfully.");
  }

  @Override
  public void showAllPlayers() {
    view.displayPlayerList("All Players", team.getAllPlayers());
  }

  @Override
  public void showStartingLineup() {
    try {
      String startingLineup = team.getStartingLineup();
      view.displayPlayerList("Starting Lineup", startingLineup);
    } catch (IllegalStateException ex) {
      view.displayErrorMessage(ex.getMessage());
    }
  }
}
