package controller;

import model.HotelSystem;
import model.Reservation;
import model.Room;
import view.HotelSystemGUI;
import view.ViewReservationDialog;
import view.ViewReservationsPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for managing the view of reservations. Handles user interactions
 * and updates the view with reservation data.
 */
public class ViewReservationsController extends Controller implements ActionListener {
    private ViewReservationsPage page;

    /**
     * Constructs a ViewReservationsController and initializes it with the specified page.
     *
     * @param page The ViewReservationsPage that this controller will manage.
     */
    public ViewReservationsController(ViewReservationsPage page) {
        this.page = page;

        page.setController(this);
        page.setActionListener(this);
    }

    /**
     * Updates the view by populating the reservations list with current reservation data
     * from the HotelSystem.
     */
    @Override
    public void updateView() {
        page.clearText();

        DefaultListModel<String> reservations = new DefaultListModel<>();
        int index = 0;
        for (Room room: HotelSystem.getCurrentHotel().getRooms()) {
            for (Reservation reservation: room.getReservations()) {
                reservations.addElement(index + " - " + reservation.getGuestName() + "'s reservation | " +
                        reservation.getCheckInDate() + " - " + reservation.getCheckOutDate());
                index++;
            }
        }
        if (reservations.isEmpty()) {
            reservations.addElement("No reservations found");
        }

        page.updateReservations(reservations);
    }

    /**
     * Finds a reservation based on the provided index. The index corresponds to the
     * position of the reservation in the overall list of reservations.
     *
     * @param index The index of the reservation to find.
     * @return The Reservation object if found; otherwise, null.
     */
    private Reservation findReservation(int index) {
        if (index < 0)
            return null;
        for (Room room: HotelSystem.getCurrentHotel().getRooms()) {
            for (Reservation reservation: room.getReservations()) {
                if (index == 0)
                    return reservation;
                index--;
            }
        }
        return null;
    }

    /**
     * Handles action events for the ViewReservationsPage. Responds to user actions
     * such as selecting a reservation or navigating back.
     *
     * @param e The ActionEvent triggered by user interaction.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            HotelSystemGUI.getInstance().changeLayout("hotel");
        } else {
            try {
                int index = Integer.parseInt(page.getText());
                Reservation reservation = findReservation(index);
                if (reservation == null)
                    throw new Exception();

                ViewReservationDialog dialog = new ViewReservationDialog(reservation);
                JOptionPane.showMessageDialog(null, dialog, "View Reservation", JOptionPane.PLAIN_MESSAGE);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Invalid input!");
            }
        }
    }
}
