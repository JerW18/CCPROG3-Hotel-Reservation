package controller;

import model.HotelSystem;
import model.Room;
import view.AvailableRoomsPage;
import view.HotelSystemGUI;
import view.RemoveRoomPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller for handling actions related to removing rooms from a hotel.
 * Manages user interactions and updates the view to reflect the current state of the hotel's rooms.
 */
public class RemoveRoomController extends Controller implements ActionListener {
    private RemoveRoomPage page;

    /**
     * Constructs a RemoveRoomController with the specified RemoveRoomPage.
     * Sets the controller for the page and assigns an action listener.
     *
     * @param page The RemoveRoomPage to be controlled.
     */
    public RemoveRoomController(RemoveRoomPage page) {
        this.page = page;

        page.setController(this);
        page.setActionListener(this);
    }

    /**
     * Updates the view with the current list of rooms in the hotel.
     * Retrieves the list of rooms from the HotelSystem, updates the page's list model,
     * and handles the case when no rooms are available.
     */
    @Override
    public void updateView() {
        page.clearText();

        ArrayList<Room> rooms = HotelSystem.getCurrentRooms();
        DefaultListModel<String> roomNames = new DefaultListModel<>();
        for (int i = 0; i < rooms.size(); i++) {
            roomNames.addElement(i + " - " + rooms.get(i).getName());
        }
        if (roomNames.isEmpty()) { // should never happen i.e. caught by previous controller
            roomNames.addElement("No rooms found");
        }

        page.updateRooms(roomNames);
    }

    /**
     * Handles actions triggered by user interactions.
     * Responds to button clicks to either navigate back to the previous page or remove a room.
     * Validates input, confirms actions, and updates the view based on the operation's success.
     *
     * @param e The ActionEvent representing the user interaction.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            HotelSystemGUI.getInstance().changeLayout("manage hotel");
        } else {
            try {
                int index = Integer.parseInt(page.getText());
                boolean valid = HotelSystem.checkRoomIndex(index);
                if (!valid)
                    throw new Exception();

                int response = JOptionPane.showConfirmDialog(null,
                        "Confirm action? (cannot be undone)",
                        "Confirm Action",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    HotelSystem.removeRoom(index);
                    JOptionPane.showMessageDialog(null, "Room successfully removed!");
                    HotelSystemGUI.getInstance().changeLayout("manage hotel");
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Invalid input!");
            }
        }
    }
}
