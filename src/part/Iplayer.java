package part;

/**
 * Interface for the Player class.
 */
public interface Iplayer {

  String getFullName();

  boolean isEligible();

  int getSkillLevel();

  Position getPreferredPosition();

  Position getAssignedPosition();

  void setAssignedPosition(Position assignedPosition);

  int getJerseyNumber();

  void setJerseyNumber(int jerseyNumber);
}
