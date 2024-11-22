package part;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Represents a team of players.
 */
public class Team implements Iteam {
  private List<Player> players = new ArrayList<>();
  private List<Player> startingLineup = new ArrayList<>();
  private List<Player> benchPlayers = new ArrayList<>();
  private Random random = new Random();
  private boolean teamBuilt = false;

  /**
   * Adds a player to the team.
   *
   * @param player the player to add
   */
  public void addPlayer(Player player) {
    if (!player.isEligible()) {
      throw new IllegalArgumentException("Player must be under 10 years old.");
    }
    players.add(player);
  }

  private void assignJerseyNumbers() {
    Set<Integer> usedJerseyNumbers = new HashSet<>();
    for (Player player : players) {
      int jerseyNumber;
      do {
        jerseyNumber = random.nextInt(20) + 1;
      } while (usedJerseyNumbers.contains(jerseyNumber));
      usedJerseyNumbers.add(jerseyNumber);
      player.setJerseyNumber(jerseyNumber);
    }
  }

  /**
   * Builds the team by selecting the starting lineup and bench players.
   */
  public void buildTeam() {
    if (players.size() < 10) {
      throw new IllegalStateException("Team cannot be created with less than 10 players.");
    }

    players.sort(Comparator.comparing(Player::getSkillLevel).reversed());

    if (players.size() > 20) {
      players = players.subList(0, 20);
    }
    assignJerseyNumbers();
    startingLineup.clear();
    benchPlayers.clear();

    for (Player player : players) {
      if (startingLineup.size() < 7) {
        startingLineup.add(player);
      } else {
        benchPlayers.add(player);
      }
    }

    int requireGoalies = 1;
    int requireDefenders = 2;
    int requireMidfielders = 3;
    int requireForward = 1;

    int goalies = 0;
    int defenders = 0;
    int midfielders = 0;
    int forward = 0;

    List<Player> reassignList = new ArrayList<>();

    for (Player player : startingLineup) {
      switch (player.getPreferredPosition()) {
        case Goalie:
          if (goalies < requireGoalies) {
            player.setAssignedPosition(Position.Goalie);
            goalies++;
          } else {
            reassignList.add(player);
          }
          break;
        case Defenders:
          if (defenders < requireDefenders) {
            player.setAssignedPosition(Position.Defenders);
            defenders++;
          } else {
            reassignList.add(player);
          }
          break;
        case Midfielders:
          if (midfielders < requireMidfielders) {
            player.setAssignedPosition(Position.Midfielders);
            midfielders++;
          } else {
            reassignList.add(player);
          }
          break;
        case Forward:
          if (forward < requireForward) {
            player.setAssignedPosition(Position.Forward);
            forward++;
          } else {
            reassignList.add(player);
          }
          break;
        default:
          reassignList.add(player);
          break;
      }
    }

    for (Player player : reassignList) {
      if (goalies < requireGoalies) {
        player.setAssignedPosition(Position.Goalie);
        goalies++;
      } else if (defenders < requireDefenders) {
        player.setAssignedPosition(Position.Defenders);
        defenders++;
      } else if (midfielders < requireMidfielders) {
        player.setAssignedPosition(Position.Midfielders);
        midfielders++;
      } else if (forward < requireForward) {
        player.setAssignedPosition(Position.Forward);
        forward++;
      }
    }
    teamBuilt = true;
  }

  /**
   * Returns all players in the team.
   *
   * @return sb.toString()
   */
  public String getAllPlayers() {
    StringBuilder sb = new StringBuilder();
    players.stream()
        .sorted(Comparator.comparing(Player::getLastName))
        .forEach(player -> sb.append(player.getFullName())
            .append(", Skill Level: ").append(player.getSkillLevel())
            .append(", Position: ").append(player.getPreferredPosition()).append("\n"));
    return sb.toString();
  }

  /**
   * Returns the starting lineup of the team.、
   *
   * @return sb.toString()
   */
  public String getStartingLineup() {
    if (!teamBuilt) {
      throw new IllegalStateException("Please build the team first.");
    }
    StringBuilder sb = new StringBuilder();
    startingLineup.stream()
        .sorted(Comparator.comparing(Player::getAssignedPosition))
        .forEach(player -> sb.append(player.getFullName()).append(", Position: ")
            .append(player.getAssignedPosition()).append(", Jersey Number: ")
            .append(player.getJerseyNumber()).append(", Skill Level: ")
            .append(player.getSkillLevel()).append("\n"));
    return sb.toString();
  }
}
