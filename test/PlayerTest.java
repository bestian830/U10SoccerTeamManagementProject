import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import org.junit.Test;
import part.Player;
import part.Position;

/**
 * Test class for the Player class.
 */
public class PlayerTest {
  @Test
  public void testPlayerCreation() {
    Player player =
        new Player("David", "Beckham", LocalDate.of(2015, 5, 2), Position.Midfielders, 5);
    assertEquals("David Beckham", player.getFullName());
    assertEquals("Beckham", player.getLastName());
    assertTrue(player.isEligible());
    assertEquals(5, player.getSkillLevel());
    assertEquals(Position.Midfielders, player.getPreferredPosition());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSkillLevelLow() {
    new Player("Christiano", "Ronaldo", LocalDate.of(2015, 5, 2), Position.Forward, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSkillLevelHigh() {
    new Player("Leo", "Messi", LocalDate.of(2015, 5, 2), Position.Forward, 6);
  }

  @Test
  public void testSetAndGetJerseyNumber() {
    Player player = new Player("Kylian", "Mbappe", LocalDate.of(2015, 5, 2), Position.Forward, 5);
    player.setJerseyNumber(10);
    assertEquals(10, player.getJerseyNumber());
  }

  @Test
  public void testIsEligible() {
    Player eligiblePlayer =
        new Player("Neymar", "Jr", LocalDate.of(2015, 5, 2), Position.Forward, 5);
    Player ineligiblePlayer =
        new Player("Diego", "Maradona", LocalDate.of(1960, 5, 2), Position.Forward, 5);
    assertTrue(eligiblePlayer.isEligible());
    assertFalse(ineligiblePlayer.isEligible());
  }
}
