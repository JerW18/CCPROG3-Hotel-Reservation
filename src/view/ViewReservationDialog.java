package view;

import model.Reservation;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog panel for viewing details of a reservation.
 * Extends {@link JPanel} to present detailed information about a reservation.
 */
public class ViewReservationDialog extends JPanel {

    /**
     * Constructs a {@code ViewReservationDialog} with details of a given reservation.
     * Initializes the panel with a grid layout and adds labels and a text area
     * to display the reservation information and price breakdown.
     *
     * @param reservation the {@link Reservation} object containing reservation details
     */
    public ViewReservationDialog(Reservation reservation) {
        setLayout(new GridLayout(7, 1));

        // Adding labels for reservation details
        add(new JLabel(reservation.getGuestName() + "'s Reservation"));
        add(new JLabel("Room: " + reservation.getRoom().getName()));
        add(new JLabel("Room Type: " + reservation.getRoom().getRoomType()));
        add(new JLabel("Check-in Date: " + reservation.getCheckInDate()));
        add(new JLabel("Check-out Date: " + reservation.getCheckOutDate()));
        add(new JLabel("Total Price: " + String.format("P%.2f", reservation.getTotalPrice())));

        // Creating a breakdown of the price for each night
        StringBuilder breakdown = new StringBuilder();
        for (int i = reservation.getCheckInDate(); i < reservation.getCheckOutDate(); i++) {
            breakdown.append("Night ").append(i).append(": P")
                    .append(String.format("%.2f", reservation.getPriceBreakdown(i)));
            if (i + 1 < reservation.getCheckOutDate())
                breakdown.append("\n");
        }

        // Adding a text area to display the price breakdown
        JTextArea area = new JTextArea(breakdown.toString());
        area.setEnabled(false);
        add(area);
    }
}
