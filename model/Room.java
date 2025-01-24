package model;

import java.util.ArrayList;
/**
 * A room in a hotel.
 */
public class Room {
    private String name; // Name of the room
    private String roomType; // Classification for room
    private Hotel hotel;
    private double pricePerNight = 1299; // Base room price per night
    private ArrayList<Reservation> reservations = new ArrayList <Reservation>(); // List of reservations in the room

    /**
     * Creates a room with a name and its price per night.
     *
     * @param name Name of the room
     */
    public Room(String name, String roomType, double basePrice, Hotel hotel) {
        this.name = name;
        this.roomType = roomType;
        this.pricePerNight = basePrice;
        this.hotel = hotel;
    }

    /**
     * Returns the name of the room.
     *
     * @return Name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the classification of the room.
     *
     * @return Type of room
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Return the Hotel of the room.
     *
     * @return Hotel of the room
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Sets the classification of the room.
     *
     * @param roomType New price per night for the room
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * Returns the price rate of the room
     *
     * @return Price rate
     */
    public double getPriceRate() {
        return switch (roomType) {
            case "Deluxe" -> 1.2;
            case "Executive" -> 1.35;
            default -> 1;
        };
    }

    /**
     * Returns the actual price per night for the room.
     *
     * @return Price per night for the room
     */
    public double getPricePerNight() {
        return getPriceRate() * pricePerNight;
    }

    /**
     * Sets the base price per night for the room.
     *
     * @param pricePerNight New price per night for the room
     */
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    /**
     * Returns whether the room is currently booked or not.
     *
     * @return True if the room is booked, false if it is available
     */
    public boolean isBooked() {
        return !reservations.isEmpty();
    }

    /**
     * Returns the list of reservations in the room.
     *
     * @return List of reservations in the room
     */
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Checks whether a room is available for given check-in and check-out dates.
     *
     * @param start The check-in date.
     * @param end The check-out date.
     * @return True if the room is available for the given dates and false otherwise.
     */
    public boolean isAvailable(int start, int end) {
        for (Reservation reservation: reservations) {
            if (reservation.hasConflict(start, end))
                return false;
        }
        return true;
    }

    /**
     * Books a room with the given guest name, check-in and check-out dates,
     * and discounted rate.
     *
     * @param name The name of the guest.
     * @param start The check-in date.
     * @param end The check-out date.
     * @param discount The discounted rate.
     */
    public void bookRoom(String name, int start, int end, double discount) {
        reservations.add(new Reservation(name, start, end, this, discount));
    }

    /**
     * Checks whether the given reservation index is valid.
     *
     * @param index The reservation index.
     * @return True if the reservation index is valid and false otherwise.
     */
    public boolean checkReservationIndex(int index) {
        return index >= 0 && index < reservations.size();
    }

    /**
     * Removes a reservation based on the given index.
     *
     * @param index The index of the reservation to be removed.
     */
    public void removeReservation(int index) {
        reservations.remove(index);
    }
}
