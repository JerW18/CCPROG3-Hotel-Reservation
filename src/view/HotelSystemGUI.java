package view;

import controller.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Main graphical user interface (GUI) class for the Hotel Reservation System.
 * Extends {@link JFrame} to provide a window for the entire application.
 * Manages different pages and their controllers using a {@link CardLayout}.
 */
public class HotelSystemGUI extends JFrame {
    private static HotelSystemGUI gui;
    private HashMap<String, Page> cardMap = new HashMap<>();

    /**
     * Constructs a {@code HotelSystemGUI} and initializes the GUI components.
     * Sets up the layout and adds pages with their corresponding controllers to the GUI.
     */
    public HotelSystemGUI() {
        super("Hotel Reservation System");
        getContentPane().setLayout(new CardLayout());

        // Initialize and add pages and their controllers
        MainPage mainPage = new MainPage();
        new MainController(mainPage);
        addCard(mainPage, "main");

        ViewHotelsPage viewHotelsPage = new ViewHotelsPage();
        new ViewHotelsController(viewHotelsPage);
        addCard(viewHotelsPage, "view hotels");

        HotelPage hotelPage = new HotelPage();
        new HotelController(hotelPage);
        addCard(hotelPage, "hotel");

        ManageHotelPage manageHotelPage = new ManageHotelPage();
        new ManageHotelController(manageHotelPage);
        addCard(manageHotelPage, "manage hotel");

        AvailableRoomsPage availableRoomsPage = new AvailableRoomsPage();
        new AvailableRoomsController(availableRoomsPage);
        addCard(availableRoomsPage, "available rooms");

        RemoveRoomPage removeRoomPage = new RemoveRoomPage();
        new RemoveRoomController(removeRoomPage);
        addCard(removeRoomPage, "remove room");

        RemoveReservationPage1 removeReservationPage1 = new RemoveReservationPage1();
        new RemoveReservationController1(removeReservationPage1);
        addCard(removeReservationPage1, "remove reservation 1");

        RemoveReservationPage2 removeReservationPage2 = new RemoveReservationPage2();
        new RemoveReservationController2(removeReservationPage2);
        addCard(removeReservationPage2, "remove reservation 2");

        ViewReservationsPage viewReservationsPage = new ViewReservationsPage();
        new ViewReservationsController(viewReservationsPage);
        addCard(viewReservationsPage, "view reservations");

        // Set up the frame
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        gui = this;
    }

    /**
     * Adds a page to the card layout with a specified name.
     *
     * @param page the {@link Page} to add
     * @param name the name to associate with the page
     */
    public void addCard(Page page, String name) {
        this.add(page, name);
        cardMap.put(name, page);
    }

    /**
     * Retrieves a page associated with the specified name.
     *
     * @param name the name of the page to retrieve
     * @return the {@link Page} associated with the specified name, or {@code null} if not found
     */
    public Page getCard(String name) {
        return cardMap.get(name);
    }

    /**
     * Changes the currently visible layout to the page associated with the specified name.
     * Updates the view of the page's controller.
     *
     * @param name the name of the page to display
     */
    public void changeLayout(String name) {
        CardLayout layout = (CardLayout) this.getContentPane().getLayout();
        layout.show(this.getContentPane(), name);

        getCard(name).getController().updateView();
    }

    /**
     * Provides access to the singleton instance of {@code HotelSystemGUI}.
     *
     * @return the singleton instance of {@code HotelSystemGUI}
     */
    public static HotelSystemGUI getInstance() {
        return gui;
    }
}