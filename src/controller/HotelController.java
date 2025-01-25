package controller;

import view.HotelSystemGUI;
import model.Hotel;
import model.HotelSystem;
import view.BookRoomDialog;
import view.HotelPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles the business logic for managing a hotel and its rooms.
 * Acts as an intermediary between the view (HotelPage) and the model (HotelSystem).
 */
public class HotelController extends Controller implements ActionListener {
    private HotelPage page;

    /**
     * Constructs a HotelController with the specified HotelPage.
     * Initializes the controller by setting itself as the action listener for the page.
     *
     * @param page The HotelPage view associated with this controller.
     */
    public HotelController(HotelPage page) {
        this.page = page;

        page.setController(this);
        page.setActionListener(this);
    }

    /**
     * Updates the HotelPage view with the current hotel information.
     * Sets the hotel name, number of rooms, and estimated earnings.
     */
    @Override
    public void updateView() {
        Hotel hotel = HotelSystem.getCurrentHotel();
        page.setText(hotel.getName(),
                "" + hotel.getRooms().size(),
                String.format("P%.2f", hotel.estimateEarnings()));
    }

    /**
     * Handles action events triggered by user interactions in the HotelPage view.
     * Processes commands such as navigating back, managing the hotel, viewing reservations,
     * and booking a room.
     *
     * @param e The action event triggered by the user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            HotelSystemGUI.getInstance().changeLayout("view hotels");
        } else if (e.getActionCommand().equals("Manage Hotel")) {
            HotelSystemGUI.getInstance().changeLayout("manage hotel");
        } else if (e.getActionCommand().equals("View Reservations")) {
            HotelSystemGUI.getInstance().changeLayout("view reservations");
        } else {
            BookRoomDialog dialog = new BookRoomDialog();

            int result = JOptionPane.showConfirmDialog(null, dialog, "Please Enter Your Details",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String name = dialog.getGuest();
                String startDate = dialog.getStart();
                String endDate = dialog.getEnd();

                try {
                    if (name.isEmpty() || name.isBlank())
                        throw new Exception();

                    int start = Integer.parseInt(startDate);
                    int end = Integer.parseInt(endDate);
                    if (start < 1 || start > 30 || end < 2 || end > 31 || start >= end)
                        throw new Exception();

                    boolean valid = HotelSystem.findAvailableRooms(start, end);
                    if (valid) {
                        AvailableRoomsController controller = (AvailableRoomsController) HotelSystemGUI.getInstance()
                                .getCard("available rooms").getController();
                        controller.setDetails(name, start, end);

                        HotelSystemGUI.getInstance().changeLayout("available rooms");
                    } else {
                        JOptionPane.showMessageDialog(null, "No rooms available!");
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        }
    }
}
