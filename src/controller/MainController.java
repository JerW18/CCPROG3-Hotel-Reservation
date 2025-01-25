package controller;

import view.HotelSystemGUI;
import model.HotelSystem;
import view.MainPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MainController class handles the user interactions for the main menu page.
 * It implements the ActionListener interface to respond to button clicks and other actions.
 */
public class MainController extends Controller implements ActionListener {
    /**
     * Constructs a MainController instance and initializes the controller for the main page.
     *
     * @param page The main page view that this controller manages.
     */
    public MainController(MainPage page) {
        page.setController(this);
        page.setActionListener(this);
    }

    /**
     * Handles action events triggered by user interactions on the main menu page.
     *
     * @param e The action event triggered by a user interaction.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Quit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Create Hotel")) {
            String name = JOptionPane.showInputDialog(null, "Enter hotel name:");
            if (name != null) {
                if (name.isEmpty() || name.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                } else {
                    boolean added = HotelSystem.addHotel(name);
                    if (!added) {
                        JOptionPane.showMessageDialog(null, "Duplicate name. Hotel not created.");
                    }
                }
            }
        } else { // View Hotels
            HotelSystemGUI.getInstance().changeLayout("view hotels");
        }
    }
}
