package part;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * View class for the U10 Soccer Team application.
 */
public class View implements Iview {
  /**
   * The controller.
   */
  private Controller controller;
  private JFrame frame;
  private JTextField firstNameField;
  private JTextField lastNameField;
  private JComboBox<Integer> yearComboBox;
  private JComboBox<Integer> monthComboBox;
  private JComboBox<Integer> dayComboBox;
  private JComboBox<Position> positionComboBox;
  private JComboBox<Integer> skillLevelComboBox;
  private JTextArea displayArea;

  public View(Controller controller) {
    this.controller = controller;
    initialize();
  }

  @Override
  public void setController(Controller controller) {
    this.controller = controller;
  }

  private void initialize() {
    frame = new JFrame("U10 Soccer Team Management");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1200, 600);

    Container container = frame.getContentPane();
    container.setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel(new GridLayout());
    container.add(inputPanel, BorderLayout.NORTH);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.WEST;

    // First Name
    gbc.gridx = 0;
    gbc.gridy = 0;
    inputPanel.add(new JLabel("First Name:"), gbc);

    firstNameField = new JTextField(10);
    gbc.gridx = 1;
    inputPanel.add(firstNameField, gbc);

    // Last Name
    gbc.gridx = 0;
    gbc.gridy = 1;
    inputPanel.add(new JLabel("Last Name:"), gbc);

    lastNameField = new JTextField(10);
    gbc.gridx = 1;
    inputPanel.add(lastNameField, gbc);

    // Birth Year
    gbc.gridx = 0;
    gbc.gridy = 2;
    inputPanel.add(new JLabel("Birth Year:"), gbc);

    Integer[] years = {2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024};
    yearComboBox = new JComboBox<>(years);
    yearComboBox.setSelectedIndex(-1);
    gbc.gridx = 1;
    inputPanel.add(yearComboBox, gbc);

    // Birth Month
    gbc.gridx = 0;
    gbc.gridy = 3;
    inputPanel.add(new JLabel("Birth Month:"), gbc);

    Integer[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    monthComboBox = new JComboBox<>(months);
    monthComboBox.setSelectedIndex(-1);
    gbc.gridx = 1;
    inputPanel.add(monthComboBox, gbc);

    // Birth Day
    gbc.gridx = 0;
    gbc.gridy = 4;
    inputPanel.add(new JLabel("Birth Day:"), gbc);

    dayComboBox = new JComboBox<>();
    gbc.gridx = 1;
    inputPanel.add(dayComboBox, gbc);

    yearComboBox.addActionListener(e -> updateDays());
    monthComboBox.addActionListener(e -> updateDays());

    // Preferred Position
    gbc.gridx = 0;
    gbc.gridy = 5;
    inputPanel.add(new JLabel("Preferred Position:"), gbc);

    positionComboBox = new JComboBox<>(Position.values());
    positionComboBox.insertItemAt(null, 0);
    positionComboBox.setSelectedIndex(0);
    gbc.gridx = 1;
    inputPanel.add(positionComboBox, gbc);

    // Skill Level
    gbc.gridx = 0;
    gbc.gridy = 6;
    inputPanel.add(new JLabel("Skill Level (1-5):"), gbc);
    skillLevelComboBox = new JComboBox<>(new Integer[] {1, 2, 3, 4, 5});
    skillLevelComboBox.setSelectedIndex(-1);
    inputPanel.add(skillLevelComboBox);

    JPanel buttonPanel = new JPanel(new FlowLayout());
    container.add(buttonPanel, BorderLayout.CENTER);

    JButton addButton = new JButton("Add Player");
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          String firstName = firstNameField.getText();
          String lastName = lastNameField.getText();
          Integer selectedYear = (Integer) yearComboBox.getSelectedItem();
          Integer selectedMonth = (Integer) monthComboBox.getSelectedItem();
          Integer selectedDay = (Integer) dayComboBox.getSelectedItem();
          if (selectedYear == null || selectedMonth == null || selectedDay == null) {
            throw new IllegalArgumentException("Please select valid date.");
          }
          LocalDate birthDate =
              LocalDate.of(selectedYear, selectedMonth, selectedDay);
          Position preferredPosition = (Position) positionComboBox.getSelectedItem();
          int skillLevel = (int) skillLevelComboBox.getSelectedItem();
          if (!birthDate.isAfter(LocalDate.now().minusYears(10))) {
            throw new IllegalArgumentException("Player must be under 10 years old.");
          }
          controller.addPlayer(firstName, lastName, birthDate, preferredPosition, skillLevel);
          clearInputFields();
        } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(frame,
              "Please enter a player under 10 years old.",
              "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
          JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    buttonPanel.add(addButton);

    JButton buildTeamButton = new JButton("Build Team");
    buildTeamButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.buildTeam();
      }
    });
    buttonPanel.add(buildTeamButton);

    JButton allPlayersButton = new JButton("Show All Players");
    allPlayersButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.showAllPlayers();
      }
    });
    buttonPanel.add(allPlayersButton);

    JButton startingLineupButton = new JButton("Show Starting Lineup");
    startingLineupButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          controller.showStartingLineup();
        } catch (IllegalStateException ex) {
          JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    buttonPanel.add(startingLineupButton);

    displayArea = new JTextArea();
    displayArea.setEditable(false);
    displayArea.setRows(30);
    container.add(new JScrollPane(displayArea), BorderLayout.SOUTH);

    frame.setVisible(true);
  }

  @Override
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(frame, message);
  }

  @Override
  public void displayErrorMessage(String errorMessage) {
    JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void displayPlayerList(String title, String playerList) {
    displayArea.setText(title + ":\n" + playerList);
  }

  private void clearInputFields() {
    firstNameField.setText("");
    lastNameField.setText("");
    yearComboBox.setSelectedIndex(-1);
    monthComboBox.setSelectedIndex(-1);
    dayComboBox.removeAllItems();
    positionComboBox.setSelectedIndex(0);
    skillLevelComboBox.setSelectedIndex(-1);
  }

  private void updateDays() {
    dayComboBox.removeAllItems();
    Integer selectedYear = (Integer) yearComboBox.getSelectedItem();
    Integer selectedMonth = (Integer) monthComboBox.getSelectedItem();
    if (selectedYear != null && selectedMonth != null) {
      int year = selectedYear;
      int month = selectedMonth;
      int daysInMonth = getDaysInMonth(year, month);
      for (int i = 1; i <= daysInMonth; i++) {
        dayComboBox.addItem(i);
      }
    }
  }

  private int getDaysInMonth(int year, int month) {
    switch (month) {
      case 2:
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
      case 4:
      case 6:
      case 9:
      case 11:
        return 30;
      default:
        return 31;
    }
  }
}
