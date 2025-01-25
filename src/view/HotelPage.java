package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The HotelPage class represents the view for displaying hotel information and managing hotel-related actions.
 * It extends the Page class and provides a graphical user interface for interacting with the hotel system.
 */
public class HotelPage extends Page {
    private JLabel hotelName, roomCnt, earnings;
    private JButton manageHotel, bookRoom, viewReservations, back;

    /**
     * Constructs a HotelPage instance and initializes the graphical components.
     * Sets up the layout and adds components to display hotel name, room count, earnings, and action buttons.
     */
    public HotelPage() {
        setLayout(new GridLayout(7, 1));

        hotelName = new JLabel("Hotel", SwingConstants.CENTER);
        add(hotelName);

        JPanel roomPane = new JPanel();
        roomPane.setLayout(new FlowLayout());
        roomPane.add(new JLabel("Number of Rooms:"));
        roomCnt = new JLabel("0");
        roomPane.add(roomCnt);
        add(roomPane);

        JPanel earningsPane = new JPanel();
        earningsPane.setLayout(new FlowLayout());
        earningsPane.add(new JLabel("Total Earnings:"));
        earnings = new JLabel("P0");
        earningsPane.add(earnings);
        add(earningsPane);

        manageHotel = new JButton("Manage Hotel");
        bookRoom = new JButton("Book Room");
        viewReservations = new JButton("View Reservations");
        back = new JButton("Back");

        add(manageHotel);
        add(bookRoom);
        add(viewReservations);
        add(back);
    }

    /**
     * Sets the text for hotel name, room count, and earnings labels.
     *
     * @param hotelName The name of the hotel.
     * @param roomCnt The number of rooms in the hotel.
     * @param earnings The total earnings of the hotel.
     */
    public void setText(String hotelName, String roomCnt, String earnings) {
        this.hotelName.setText(hotelName);
        this.roomCnt.setText(roomCnt);
        this.earnings.setText(earnings);
    }

    /**
     * Registers an ActionListener to the action buttons (manage hotel, book room, view reservations, and back).
     *
     * @param actionListener The ActionListener to be registered.
     */
    public void setActionListener(ActionListener actionListener) {
        manageHotel.addActionListener(actionListener);
        bookRoom.addActionListener(actionListener);
        viewReservations.addActionListener(actionListener);
        back.addActionListener(actionListener);
    }
}
