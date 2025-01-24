package controller;

import model.Hotel;
import view.AddRoomDialog;
import view.HotelSystemGUI;
import model.HotelSystem;
import view.ManageHotelPage;
import view.UpdatePriceRateDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ManageHotelController class handles user actions in the ManageHotelPage.
 * It allows for various operations on the current hotel, including renaming,
 * adding or removing rooms, updating prices, and removing the hotel.
 */
public class ManageHotelController extends Controller implements ActionListener {
    /**
     * Constructs a ManageHotelController instance and initializes it with the provided page.
     *
     * @param page The ManageHotelPage instance associated with this controller.
     */
    public ManageHotelController(ManageHotelPage page) {
        page.setController(this);
        page.setActionListener(this);
    }

    /**
     * Displays a confirmation dialog to the user for irreversible actions.
     *
     * @return True if the user confirms the action, false otherwise.
     */
    public boolean getConfirmation() {
        int response = JOptionPane.showConfirmDialog(null,
                "Confirm action? (cannot be undone)",
                "Confirm Action",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return response == JOptionPane.YES_OPTION;
    }

    /**
     * Handles action events for buttons in the ManageHotelPage.
     *
     * @param e The ActionEvent that triggered this method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            HotelSystemGUI.getInstance().changeLayout("hotel");
        } else if (e.getActionCommand().equals("Change Hotel Name")) {
            String name = JOptionPane.showInputDialog(null, "Enter hotel name:");
            if (name != null) {
                if (name.isEmpty() || name.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                    return;
                }

                boolean isDuplicate = HotelSystem.checkDuplicate(name);
                if (isDuplicate) {
                    JOptionPane.showMessageDialog(null, "Duplicate name. Hotel name not modified.");
                    return;
                }

                if (getConfirmation()) {
                    HotelSystem.getCurrentHotel().setName(name);
                    JOptionPane.showMessageDialog(null, "Hotel name successfully modified.");
                }
            }
        } else if (e.getActionCommand().equals("Add Room")) {
            boolean valid = HotelSystem.getCurrentHotel().checkRoomCount();
            if (!valid) {
                JOptionPane.showMessageDialog(null, "50 room limit reached.");
                return;
            }

            AddRoomDialog dialog = new AddRoomDialog();
            int result = JOptionPane.showConfirmDialog(null, dialog, "Please Enter Room Type",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String input = dialog.getRoomType();

                try {
                    int roomType = Integer.parseInt(input);
                    if (roomType < 0 || roomType > 2)
                        throw new Exception();

                    if (getConfirmation()) {
                        HotelSystem.getCurrentHotel().addRoom(roomType);
                        JOptionPane.showMessageDialog(null, "New room added.");
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        } else if (e.getActionCommand().equals("Remove Room")) {
            boolean valid = HotelSystem.findRooms(false);
            if (valid) {
                HotelSystemGUI.getInstance().changeLayout("remove room");
            } else {
                JOptionPane.showMessageDialog(null, "No rooms to remove.");
            }
        } else if (e.getActionCommand().equals("Update Room Price")) {
            if (HotelSystem.getCurrentHotel().hasBooking()) {
                JOptionPane.showMessageDialog(null, "Hotel has booking. Cannot change room price.");
                return;
            }

            try {
                double price = Double.parseDouble(JOptionPane.showInputDialog(
                        null, "Enter new room price:")
                );
                if (price < 100)
                    throw new Exception();

                if (getConfirmation()) {
                    HotelSystem.getCurrentHotel().setBasePrice(price);
                    JOptionPane.showMessageDialog(null, "Price modified!");
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Invalid input!");
            }
        } else if (e.getActionCommand().equals("Update Price Rate")) {
            UpdatePriceRateDialog dialog = new UpdatePriceRateDialog();
            int result = JOptionPane.showConfirmDialog(null, dialog, "Please Enter Details",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String input1 = dialog.getDate();
                String input2 = dialog.getPriceRate();

                try {
                    int date = Integer.parseInt(input1);
                    int priceRate = Integer.parseInt(input2);
                    if (date < 1 || date > 30 || priceRate < 50 || priceRate > 150)
                        throw new Exception();

                    if (getConfirmation()) {
                        HotelSystem.getCurrentHotel().setPriceRate(date, priceRate / 100.0);
                        JOptionPane.showMessageDialog(null, "Price rate changed.");
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        } else if (e.getActionCommand().equals("Remove Reservation")) {
            boolean valid = HotelSystem.findRooms(true);
            if (valid) {
                HotelSystemGUI.getInstance().changeLayout("remove reservation 1");
            } else {
                JOptionPane.showMessageDialog(null, "No reservations to remove.");
            }
        } else {
            if (getConfirmation()) {
                HotelSystem.removeHotel();
                JOptionPane.showMessageDialog(null, "Hotel removed.");
                HotelSystemGUI.getInstance().changeLayout("main");
            }
        }
    }
}
