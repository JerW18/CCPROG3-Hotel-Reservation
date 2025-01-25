package view;

import javax.swing.*;
import java.awt.*;


/**
 * Dialog panel for booking a room in the hotel.
 * Extends {@link JPanel} to provide a user interface for entering guest details
 * and booking dates.
 */
public class BookRoomDialog extends JPanel {
    private JTextField nameTf, startDate, endDate;

    /**
     * Constructs a {@code BookRoomDialog} with a grid layout.
     * Initializes the panel with labels and text fields for entering guest name
     * and booking start and end dates.
     */
    public BookRoomDialog() {
        setLayout(new GridLayout(3, 2));

        // Adding label and input field for guest name
        add(new JLabel("Enter guest name:"));
        nameTf = new JTextField(10);
        add(nameTf);

        // Adding label and input field for start date
        add(new JLabel("Enter start date (1-30):"));
        startDate = new JTextField(10);
        add(startDate);

        // Adding label and input field for end date
        add(new JLabel("Enter end date (2-31):"));
        endDate = new JTextField(10);
        add(endDate);
    }

    /**
     * Retrieves the guest name entered by the user.
     *
     * @return the text from the guest name input field
     */
    public String getGuest() {
        return nameTf.getText();
    }

    /**
     * Retrieves the start date entered by the user.
     *
     * @return the text from the start date input field
     */
    public String getStart() {
        return startDate.getText();
    }

    /**
     * Retrieves the end date entered by the user.
     *
     * @return the text from the end date input field
     */
    public String getEnd() {
        return endDate.getText();
    }
}
