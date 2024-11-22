package part;

/**
 * Interface for the Team View.
 */
public interface Iview {

  void setController(Controller controller);

  void displayMessage(String message);

  void displayErrorMessage(String errorMessage);

  void displayPlayerList(String title, String playerList);
}