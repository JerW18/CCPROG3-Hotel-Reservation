package view;

import model.Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The ViewHotelsPage class represents the view for displaying the list of hotels.
 * It provides a user interface for selecting a hotel by index.
 */
public class ViewHotelsPage extends Page {
    private JList<String> hotelList;
    private JTextField hotelTf;
    private JButton enter, back;

    /**
     * Constructs a ViewHotelsPage instance and initializes the user interface components.
     * The layout is set to a grid layout with 5 rows and 1 column.
     */
    public ViewHotelsPage() {
        setLayout(new GridLayout(5, 1));
        add(new JLabel("Select Hotel", SwingConstants.CENTER));

        hotelList = new JList<>();
        hotelList.setEnabled(false);
        add(new JScrollPane(hotelList));

        JPanel inputPane = new JPanel();
        inputPane.setLayout(new FlowLayout());
        inputPane.add(new JLabel("Enter hotel index:"));
        hotelTf = new JTextField(20);
        inputPane.add(hotelTf);
        add(inputPane);

        enter = new JButton("Enter");
        back = new JButton("Back");
        add(enter);
        add(back);
    }

    /**
     * Sets the ActionListener for the enter and back buttons.
     *
     * @param actionListener The ActionListener to set for the buttons.
     */
    public void setActionListener(ActionListener actionListener) {
        enter.addActionListener(actionListener);
        back.addActionListener(actionListener);
    }

    /**
     * Updates the list of hotels displayed in the view.
     *
     * @param hotels The DefaultListModel containing the hotel names to display.
     */
    public void updateHotels(DefaultListModel<String> hotels) {
        hotelList.setModel(hotels);
    }

    /**
     * Clears the text field where the user enters the hotel index.
     */
    public void clearText() {
        hotelTf.setText("");
    }

    /**
     * Returns the text entered in the hotel index text field.
     *
     * @return The text entered in the hotel index text field.
     */
    public String getText() {
        return hotelTf.getText();
    }
}
