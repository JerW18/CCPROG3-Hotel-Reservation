package view;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog panel for updating the price rate of a room on a specific date.
 * Extends {@link JPanel} to provide a user interface for entering the date
 * and the new price rate.
 */
public class UpdatePriceRateDialog extends JPanel {
    private JTextField dateTf, priceRate;

    /**
     * Constructs an {@code UpdatePriceRateDialog} with a grid layout.
     * Initializes the panel with labels and text fields for entering the date
     * and the new price rate.
     */
    public UpdatePriceRateDialog() {
        setLayout(new GridLayout(2, 2));

        // Adding label and input field for date
        add(new JLabel("Enter date (1-30):"));
        dateTf = new JTextField(10);
        add(dateTf);

        // Adding label and input field for price rate
        add(new JLabel("Enter price rate (50-150):"));
        priceRate = new JTextField(10);
        add(priceRate);
    }

    /**
     * Retrieves the date entered by the user.
     *
     * @return the text from the date input field
     */
    public String getDate() {
        return dateTf.getText();
    }

    /**
     * Retrieves the new price rate entered by the user.
     *
     * @return the text from the price rate input field
     */
    public String getPriceRate() {
        return priceRate.getText();
    }
}
