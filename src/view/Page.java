package view;

import controller.Controller;

import javax.swing.*;

/**
 * Base class for all pages in the application.
 * Extends {@link JPanel} to represent a view page with associated controller.
 */
public class Page extends JPanel {
    Controller controller;

    /**
     * Sets the controller for this page.
     *
     * @param controller the {@link Controller} to be set for this page
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Returns the controller associated with this page.
     *
     * @return the {@link Controller} associated with this page
     */
    public Controller getController() {
        return controller;
    }
}
