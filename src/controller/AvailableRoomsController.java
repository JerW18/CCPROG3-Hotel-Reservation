package controller;

import model.HotelSystem;
import model.Room;
import view.AvailableRoomsPage;
import view.HotelSystemGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller class responsible for managing interactions in the AvailableRoomsPage.
 * Handles the display of available rooms and processes booking requests based on user input.
 */
public class AvailableRoomsController extends Controller implements ActionListener {
    private AvailableRoomsPage page;
    private String name;
    private int start, end;

    /**
     * Constructs an AvailableRoomsController and initializes the page and action listener.
     *
     * @param page The page associated with this controller.
     */
    public AvailableRoomsController(AvailableRoomsPage page) {
        this.page = page;

        page.setController(this);
        page.setActionListener(this);
    }

    /**
     * Updates the view with the current list of available rooms.
     * Clears existing text and populates the room list with available rooms.
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
     * Handles button click events on the AvailableRoomsPage.
     * Processes room booking requests and validates discount codes.
     *
     * @param e The ActionEvent triggered by a button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            HotelSystemGUI.getInstance().changeLayout("hotel");
        } else {
            try {
                int index = Integer.parseInt(page.getRoom());
                String discount = page.getDiscount();
                double rate = 0;
                switch (discount) {
                    case "I_WORK_HERE" -> rate = 0.9;
                    case "STAY4_GET1" -> {
                        if (end - start >= 5)
                            rate = -1;
                    }
                    case "PAYDAY" -> {
                        if ((15 >= start && 15 < end) || (30 >= start && 30 < end))
                            rate = 0.93;
                    }
                }
                if (!discount.isEmpty() && !discount.isBlank() && rate == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid discount code");
                    return;
                }
                if (rate == 0)
                    rate = 1;

                boolean booked = HotelSystem.bookRoom(index, name, start, end, rate);
                if (!booked)
                    throw new Exception();

                JOptionPane.showMessageDialog(null, "Room successfully booked!");
                HotelSystemGUI.getInstance().changeLayout("hotel");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Invalid input!");
            }
        }
    }

    /**
     * Sets the details for the booking request including customer name and booking dates.
     *
     * @param name  The name of the customer booking the room.
     * @param start The start date of the booking.
     * @param end   The end date of the booking.
     */
    public void setDetails(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}
