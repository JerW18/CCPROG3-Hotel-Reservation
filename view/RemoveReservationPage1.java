package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View for the first step in removing a reservation.
 * Displays a list of rooms and allows the user to input the index of the room they wish to select for removal.
 */
public class RemoveReservationPage1 extends Page {
    private JList<String> roomList;
    private JTextField roomTf;
    private JButton enter, back;

    /**
     * Constructs a RemoveReservationPage1 and initializes its components.
     * Sets up the layout with a label, a list of rooms, input field for room index, and action buttons.
     */
    public RemoveReservationPage1() {
        setLayout(new GridLayout(5, 1));

        add(new JLabel("Select Room", SwingConstants.CENTER));

        roomList = new JList<>();
        roomList.setEnabled(false);
        add(new JScrollPane(roomList));

        JPanel inputPane = new JPanel();
        inputPane.setLayout(new FlowLayout());
        inputPane.add(new JLabel("Enter room index:"));
        roomTf = new JTextField(20);
        inputPane.add(roomTf);
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
     * Clears the text field used for entering the room index.
     */
    public void clearText() {
        roomTf.setText("");
    }

    /**
     * Retrieves the text from the room index input field.
     *
     * @return The text from the room index input field.
     */
    public String getText() {
        return roomTf.getText();
    }

    /**
     * Updates the list of rooms displayed in the JList component.
     *
     * @param rooms The DefaultListModel containing the list of rooms to be displayed.
     */
    public void updateRooms(DefaultListModel<String> rooms) {
        roomList.setModel(rooms);
    }
}
