package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View for displaying and selecting reservations. Allows users to view a list of reservations
 * and enter an index to select a specific reservation.
 */
public class ViewReservationsPage extends Page {
    private JList<String> reservationList;
    private JTextField reservationTf;
    private JButton enter, back;

    /**
     * Constructs a ViewReservationsPage and initializes its components.
     * Sets the layout and adds all necessary UI elements.
     */
    public ViewReservationsPage() {
        setLayout(new GridLayout(5, 1));

        add(new JLabel("Select Reservation", SwingConstants.CENTER));

        reservationList = new JList<>();
        reservationList.setEnabled(false);
        add(new JScrollPane(reservationList));

        JPanel inputPane = new JPanel();
        inputPane.setLayout(new FlowLayout());
        inputPane.add(new JLabel("Enter reservation index:"));
        reservationTf = new JTextField(20);
        inputPane.add(reservationTf);
        add(inputPane);

        enter = new JButton("Enter");
        back = new JButton("Back");
        add(enter);
        add(back);
    }

    /**
     * Sets the action listener for the Enter and Back buttons.
     *
     * @param actionListener The ActionListener to be set for the buttons.
     */
    public void setActionListener(ActionListener actionListener) {
        enter.addActionListener(actionListener);
        back.addActionListener(actionListener);
    }

    /**
     * Clears the text field used for entering reservation index.
     */
    public void clearText() {
        reservationTf.setText("");
    }

    /**
     * Retrieves the text from the reservation index text field.
     *
     * @return The text from the reservation index text field.
     */
    public String getText() {
        return reservationTf.getText();
    }

    /**
     * Updates the reservation list with the provided data model.
     *
     * @param reservations The data model containing reservation information to display.
     */
    public void updateReservations(DefaultListModel<String> reservations) {
        reservationList.setModel(reservations);
    }
}
