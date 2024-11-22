import javax.swing.SwingUtilities;
import part.Controller;
import part.Team;
import part.View;

/**
 * Main class to run the U10 Soccer Team application.
 */
public class Main {
  /**
   * Main method to run the U10 Soccer Team application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        Team team = new Team();
        View view = new View(null);
        Controller controller = new Controller(team, view);
        view.setController(controller);
      }
    });
  }
}