package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Represents the user interface for selecting and booking available rooms.
 * Provides input fields for room index and discount code, and buttons to submit or go back.
 */
public class AvailableRoomsPage extends Page {
    private JList<String> roomList;
    private JTextField roomTf, discountTf;
    private JButton enter, back;

    /**
     * Constructs an AvailableRoomsPage and initializes the UI components.
     * Sets up the layout and adds labels, text fields, and buttons to the page.
     */
    public AvailableRoomsPage() {
        setLayout(new GridLayout(6, 1));

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

        JPanel inputPane2 = new JPanel();
        inputPane2.setLayout(new FlowLayout());
        inputPane2.add(new JLabel("Enter discount code (if applicable):"));
        discountTf = new JTextField(20);
        inputPane2.add(discountTf);
        add(inputPane2);

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
     * Clears the text fields for room index and discount code.
     */
    public void clearText() {
        roomTf.setText("");
        discountTf.setText("");
    }

    /**
     * Gets the text from the room index text field.
     *
     * @return The room index as a string.
     */
    public String getRoom() {
        return roomTf.getText();
    }

    /**
     * Gets the text from the discount code text field.
     *
     * @return The discount code as a string.
     */
    public String getDiscount() {
        return discountTf.getText();
    }

    /**
     * Updates the JList displaying available rooms.
     *
     * @param rooms The DefaultListModel containing room information.
     */
    public void updateRooms(DefaultListModel<String> rooms) {
        roomList.setModel(rooms);
    }
}
