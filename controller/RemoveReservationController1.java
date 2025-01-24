package controller;

import model.HotelSystem;
import model.Room;
import view.HotelSystemGUI;
import view.RemoveReservationPage1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller for handling the removal of reservations in the first step.
 * Manages the interactions and logic between the RemoveReservationPage1 view and the HotelSystem model.
 */
public class RemoveReservationController1 extends Controller implements ActionListener {
    private RemoveReservationPage1 page;

    /**
     * Constructs a RemoveReservationController1 with the specified RemoveReservationPage1.
     * Initializes the page and sets the action listener for the page's components.
     *
     * @param page The RemoveReservationPage1 instance to be controlled.
     */
    public RemoveReservationController1(RemoveReservationPage1 page) {
        this.page = page;

        page.setController(this);
        page.setActionListener(this);
    }

    /**
     * Updates the view by populating the list of rooms available for reservation removal.
     * Clears previous text and retrieves the list of current rooms from the HotelSystem.
     * Updates the view with the current room information.
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
     * Handles action events from the RemoveReservationPage1 components.
     * Processes input to select a room for reservation removal and navigates to the next page.
     *
     * @param e The ActionEvent triggered by user actions.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            HotelSystemGUI.getInstance().changeLayout("manage hotel");
        } else {
            try {
                int index = Integer.parseInt(page.getText());
                boolean valid = HotelSystem.selectRoom(index);
                if (!valid)
                    throw new Exception();

                HotelSystemGUI.getInstance().changeLayout("remove reservation 2");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Invalid input!");
            }
        }
    }
}
