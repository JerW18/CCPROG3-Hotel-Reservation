package view;

import controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The MainPage class represents the main menu view of the hotel system application.
 * It provides buttons for creating a hotel, viewing hotels, and quitting the application.
 */
public class MainPage extends Page {
    private MainController controller;
    private JButton createHotel, viewHotels, quit;

    /**
     * Constructs a MainPage instance and initializes the main menu layout and buttons.
     */
    public MainPage() {
        setLayout(new GridLayout(4, 1));
        add(new JLabel("Home Page", SwingConstants.CENTER));

        createHotel = new JButton("Create Hotel");
        viewHotels = new JButton("View Hotels");
        quit = new JButton("Quit");

        add(createHotel);
        add(viewHotels);
        add(quit);
    }

    /**
     * Sets the controller for this main page view.
     *
     * @param controller The MainController instance to be set.
     */
    public void setController(MainController controller) {
        this.controller = controller;
    }

    /**
     * Gets the controller for this main page view.
     *
     * @return The MainController instance.
     */
    public MainController getController() {
        return controller;
    }

    /**
     * Sets the action listener for the buttons on this main page view.
     *
     * @param actionListener The ActionListener instance to be set for the buttons.
     */
    public void setActionListener(ActionListener actionListener) {
        createHotel.addActionListener(actionListener);
        viewHotels.addActionListener(actionListener);
        quit.addActionListener(actionListener);
    }
}
