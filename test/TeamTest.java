import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import part.Player;
import part.Position;
import part.Team;

/**
 * Test class for the Team class.
 */
public class TeamTest {
  private Team team;

  @Before
  public void setUp() {
    team = new Team();
  }

  @Test
  public void testAddPlayer() {
    Player player = new Player("Wayne", "Rooney", LocalDate.of(2015, 5, 2), Position.Forward, 5);
    team.addPlayer(player);
    assertEquals(1, team.getAllPlayers().split("\n").length);
  }

  @Test(expected = IllegalStateException.class)
  public void testBuildTeamWithLessThanTenPlayers() {
    for (int i = 0; i < 9; i++) {
      team.addPlayer(
          new Player("Player" + i, "Test", LocalDate.of(2015, 5, 2), Position.Forward, 3));
    }
    team.buildTeam();
  }

  @Test
  public void testBuildTeamWithMoreThanTwentyPlayers() {
    for (int i = 0; i < 25; i++) {
      team.addPlayer(
          new Player("Player" + i, "Test", LocalDate.of(2015, 5, 2), Position.Forward,
              (i % 5) + 1));
    }
    assertEquals(25, team.getAllPlayers().split("\n").length);
    team.buildTeam();
    assertEquals(20, team.getAllPlayers().split("\n").length);
  }

  @Test
  public void testAssignJerseyNumbers() {
    for (int i = 0; i < 10; i++) {
      team.addPlayer(
          new Player("Player" + i, "Test", LocalDate.of(2015, 5, 2), Position.Forward, 3));
    }
    team.buildTeam();
    String[] allPlayers = team.getAllPlayers().split("\n");
    assertEquals(10, allPlayers.length);
    for (String playerInfo : allPlayers) {
      assertTrue(playerInfo.contains("Jersey Number: "));
    }
  }

  @Test
  public void testGetStaringLineup() {
    for (int i = 0; i < 15; i++) {
      team.addPlayer(
          new Player("Player" + i, "Test", LocalDate.of(2015, 5, 2), Position.Forward, i % 5 + 1));
    }
    team.buildTeam();
    String[] startingLineup = team.getStartingLineup().split("\n");
    assertEquals(7, startingLineup.length);
  }

  @Test
  public void testGetAllPlayersSorted() {
    team.addPlayer(new Player("John", "Smith", LocalDate.of(2015, 5, 10), Position.Forward, 4));
    team.addPlayer(new Player("Jane", "Doe", LocalDate.of(2016, 8, 20), Position.Midfielders, 3));
    team.addPlayer(new Player("Alice", "Brown", LocalDate.of(2015, 9, 15), Position.Defenders, 5));

    String allPlayers = team.getAllPlayers();
    String[] lines = allPlayers.split("\n");
    assertEquals("Alice Brown", lines[0].split(",")[0]);
    assertEquals("Jane Doe", lines[1].split(",")[0]);
    assertEquals("John Smith", lines[2].split(",")[0]);
  }

  @Test
  public void testGetStartingLineupSortedByPosition() {
    team.addPlayer(new Player("John", "Smith", LocalDate.of(2015, 5, 10), Position.Forward, 4));
    team.addPlayer(new Player("Jane", "Doe", LocalDate.of(2016, 8, 20), Position.Goalie, 3));
    team.addPlayer(
        new Player("Alice", "Brown", LocalDate.of(2015, 9, 15), Position.Midfielders, 5));
    team.addPlayer(new Player("Bob", "Taylor", LocalDate.of(2015, 7, 25), Position.Defenders, 2));
    team.addPlayer(new Player("Charlie", "White", LocalDate.of(2015, 6, 30), Position.Forward, 1));
    team.addPlayer(
        new Player("David", "Johnson", LocalDate.of(2015, 4, 18), Position.Defenders, 3));
    team.addPlayer(
        new Player("Eve", "Williams", LocalDate.of(2015, 3, 11), Position.Midfielders, 4));
    team.addPlayer(new Player("Frank", "Brown", LocalDate.of(2015, 2, 5), Position.Goalie, 5));
    team.addPlayer(new Player("George", "Smith", LocalDate.of(2015, 1, 1), Position.Defenders, 2));
    team.addPlayer(new Player("Helen", "Johnson", LocalDate.of(2015, 12, 25), Position.Forward, 1));

    team.buildTeam();
    String startingLineup = team.getStartingLineup();
    String[] lines = startingLineup.split("\n");

    assertEquals("Frank Brown", lines[0].split(",")[0]);
    assertEquals("Jane Doe", lines[1].split(",")[0]);
    assertEquals("David Johnson", lines[2].split(",")[0]);
    assertEquals("Bob Taylor", lines[3].split(",")[0]);
    assertEquals("Alice Brown", lines[4].split(",")[0]);
    assertEquals("Eve Williams", lines[5].split(",")[0]);
    assertEquals("John Smith", lines[6].split(",")[0]);
  }

}
