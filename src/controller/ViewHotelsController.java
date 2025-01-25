package controller;

import view.HotelSystemGUI;
import model.Hotel;
import model.HotelSystem;
import view.ViewHotelsPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The ViewHotelsController class handles the interactions and logic for the view that displays the list of hotels.
 * It updates the view with the current list of hotels and processes user actions such as selecting a hotel or navigating back to the main menu.
 */
public class ViewHotelsController extends Controller implements ActionListener {
    ViewHotelsPage page;

    /**
     * Constructs a ViewHotelsController instance and initializes the associated ViewHotelsPage.
     *
     * @param page The ViewHotelsPage instance to be controlled.
     */
    public ViewHotelsController(ViewHotelsPage page) {
        this.page = page;
        page.setController(this);
        page.setActionListener(this);
    }

    /**
     * Updates the view with the current list of hotels. Clears the current text and adds the hotel names to the list.
     */
    @Override
    public void updateView() {
        page.clearText();

        ArrayList<Hotel> hotels = HotelSystem.getHotelList();
        DefaultListModel<String> hotelNames = new DefaultListModel<>();
        for (int i = 0; i < hotels.size(); i++) {
            hotelNames.addElement(i + " - " + hotels.get(i).getName());
        }
        if (hotelNames.isEmpty()) {
            hotelNames.addElement("No hotels created");
        }

        page.updateHotels(hotelNames);
    }

    /**
     * Handles user actions such as selecting a hotel or navigating back to the main menu.
     *
     * @param e The ActionEvent triggered by the user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            HotelSystemGUI.getInstance().changeLayout("main");
        } else {
            try {
                int index = Integer.parseInt(page.getText());
                boolean valid = HotelSystem.selectHotel(index);
                if (!valid)
                    throw new Exception();

                HotelSystemGUI.getInstance().changeLayout("hotel");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Invalid input!");
            }
        }
    }
}
