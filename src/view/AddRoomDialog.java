package view;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog panel for adding a new room to the hotel.
 * Extends {@link JPanel} to provide a user interface for specifying room type.
 */
public class AddRoomDialog extends JPanel {
    private JTextField roomType;

    /**
     * Constructs an {@code AddRoomDialog} with a grid layout and predefined room types.
     * Initializes the panel with labels for different room types and an input field
     * for entering the new room type.
     */
    public AddRoomDialog() {
        setLayout(new GridLayout(4, 2));

        // Adding predefined room types
        add(new JLabel("0 - "));
        add(new JLabel("Standard"));
        add(new JLabel("1 - "));
        add(new JLabel("Deluxe"));
        add(new JLabel("2 - "));
        add(new JLabel("Executive"));

        // Adding input field for new room type
        add(new JLabel("Enter room type:"));
        roomType = new JTextField(10);
        add(roomType);
    }

    /**
     * Retrieves the room type entered by the user.
     *
     * @return the text from the room type input field
     */
    public String getRoomType() {
        return roomType.getText();
    }
}
