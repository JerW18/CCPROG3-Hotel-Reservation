package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Represents the second step in the reservation removal process, where the user can select and remove a reservation.
 */
public class RemoveReservationPage2 extends Page {
    private JList<String> reservationList;
    private JTextField reservationTf;
    private JButton enter, back;

    /**
     * Constructs a RemoveReservationPage2 and initializes its layout and components.
     */
    public RemoveReservationPage2() {
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
     * Clears the text field used for entering the reservation index.
     */
    public void clearText() {
        reservationTf.setText("");
    }

    /**
     * Retrieves the text from the text field used for entering the reservation index.
     *
     * @return The text from the text field.
     */
    public String getText() {
        return reservationTf.getText();
    }

    /**
     * Updates the list of reservations displayed on the page.
     *
     * @param reservations The list model containing the reservations to be displayed.
     */
    public void updateReservations(DefaultListModel<String> reservations) {
        reservationList.setModel(reservations);
    }
}
