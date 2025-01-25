package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View class for removing rooms from a hotel.
 * Provides the user interface to display the list of rooms, input a room index,
 * and interact with buttons to perform actions.
 */
public class RemoveRoomPage extends Page {
    private JList<String> roomList;
    private JTextField roomTf;
    private JButton enter, back;

    /**
     * Constructs a RemoveRoomPage and initializes its components.
     * Sets up the layout, creates UI elements, and adds them to the page.
     */
    public RemoveRoomPage() {
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
     * @param actionListener The ActionListener to be added to the buttons.
     */
    public void setActionListener(ActionListener actionListener) {
        enter.addActionListener(actionListener);
        back.addActionListener(actionListener);
    }

    /**
     * Clears the text field where the user inputs the room index.
     */
    public void clearText() {
        roomTf.setText("");
    }

    /**
     * Retrieves the text entered in the text field.
     *
     * @return The text entered by the user.
     */
    public String getText() {
        return roomTf.getText();
    }

    /**
     * Updates the JList with the provided list of rooms.
     *
     * @param rooms The list model containing room details to be displayed.
     */
    public void updateRooms(DefaultListModel<String> rooms) {
        roomList.setModel(rooms);
    }
}
