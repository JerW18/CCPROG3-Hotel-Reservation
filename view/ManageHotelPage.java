package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Represents the user interface for managing hotel operations.
 * Provides buttons for various actions such as changing the hotel name,
 * adding or removing rooms, updating room prices, updating price rates,
 * removing reservations, and removing the hotel.
 */
public class ManageHotelPage extends Page {
    private JButton changeName, addRoom, removeRoom, updatePrice,
        updatePriceRate, removeReservation, removeHotel, back;

    /**
     * Constructs a ManageHotelPage instance and initializes its components.
     * Sets the layout and adds buttons for different management functions.
     */
    public ManageHotelPage() {
        setLayout(new GridLayout(9, 1));

        add(new JLabel("Manage Hotel", SwingConstants.CENTER));

        changeName = new JButton("Change Hotel Name");
        addRoom = new JButton("Add Room");
        removeRoom = new JButton("Remove Room");
        updatePrice = new JButton("Update Room Price");
        updatePriceRate = new JButton("Update Price Rate");
        removeReservation = new JButton("Remove Reservation");
        removeHotel = new JButton("Remove Hotel");
        back = new JButton("Back");

        add(changeName);
        add(addRoom);
        add(removeRoom);
        add(updatePrice);
        add(updatePriceRate);
        add(removeReservation);
        add(removeHotel);
        add(back);
    }

    /**
     * Sets the action listener for all buttons on the page.
     * This listener handles button click events and performs the appropriate actions.
     *
     * @param actionListener The ActionListener to be set for the buttons.
     */
    public void setActionListener(ActionListener actionListener) {
        changeName.addActionListener(actionListener);
        addRoom.addActionListener(actionListener);
        removeRoom.addActionListener(actionListener);
        updatePrice.addActionListener(actionListener);
        updatePriceRate.addActionListener(actionListener);
        removeReservation.addActionListener(actionListener);
        removeHotel.addActionListener(actionListener);
        back.addActionListener(actionListener);
    }
}
