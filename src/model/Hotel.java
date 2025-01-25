package model;

import java.util.ArrayList;

//test change
/**
 * Depicts a hotel with rooms.
 */
public class Hotel {
    private String name; // Name of the hotel
    private ArrayList<Room> rooms = new ArrayList<Room>(); // List of rooms in the hotel
    private double basePrice; // Base price per night for rooms
    private int roomCnt = 1; // Counter for naming rooms
    private double[] priceRate = new double[30];

    /**
     * Creates a new hotel with a number of rooms.
     * Initializes the hotel with a default name and creates rooms accordingly.
     */

    public Hotel(String name){
        this.name = name;
        this.basePrice = 1299.0;
        for (int i = 0; i < 30; i++) priceRate[i] = 1;
    }

    /**
     * Returns the name of the hotel.
     *
     * @return Name of the hotel
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the hotel.
     *
     * @param name New name for the hotel
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the list of rooms in the hotel.
     *
     * @return List of rooms in the hotel
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Returns the base price per night for rooms in the hotel.
     *
     * @return Base price per night for rooms
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Sets the base price per night for rooms in the hotel.
     *
     * @param basePrice New base price per night for rooms
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
        for (Room room: rooms) {
            room.setPricePerNight(basePrice);
        }
    }

    /**
     * Sets the price rate for a given date.
     *
     * @param date The given date.
     * @param rate The modified price rate.
     */
    public void setPriceRate(int date, double rate) {
        priceRate[date - 1] = rate;
    }

    /**
     * Returns the price rate for a given date.
     *
     * @param date The given date.
     * @return The price rate for the given date.
     */
    public double getPriceRate(int date) {
        return priceRate[date - 1];
    }

    /**
     * Checks whether a room can still be added.
     *
     * @return True if a room can still be added and false otherwise.
     */
    public boolean checkRoomCount() {
        return rooms.size() < 50;
    }

    /**
     * Adds a new room to the hotel with name based on room counter and default base price.
     *
     */
    public void addRoom(int roomType) {
        String[] roomTypes = {"Standard", "Deluxe", "Executive"};
        Hotel hotel = HotelSystem.getCurrentHotel();
        rooms.add(new Room("R" + roomCnt, roomTypes[roomType], hotel.getBasePrice(), hotel));
        roomCnt++;
    }

    /**
     * Removes a room from the hotel if it is not currently booked.
     *
     * @param room The room to remove.
     * @return True if the room was successfully removed, false otherwise.
     */
    public boolean removeRoom(Room room) {
        int index = rooms.indexOf(room);
        if (index == -1 || rooms.get(index).isBooked())
            return false;
        rooms.remove(index);
        return true;
    }

    /**
     * Estimates the total earnings of the hotel based on reservations made.
     *
     * @return Estimated total earnings of the hotel
     */
    public double estimateEarnings() {
        double totalEarnings = 0.0;
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = 0; j < rooms.get(i).getReservations().size(); j++)
                totalEarnings += rooms.get(i).getReservations().get(j).getTotalPrice();
        }
        return totalEarnings;
    }

    /**
     * Finds the available rooms based on the given check-in and check-out dates.
     *
     * @param start The check-in date.
     * @param end The check-out date.
     * @return The list of available rooms.
     */
    public ArrayList<Room> findAvailableRooms(int start, int end) {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room: rooms) {
            if (room.isAvailable(start, end))
                availableRooms.add(room);
        }
        return availableRooms;
    }

    /**
     * Finds either rooms that are booked or not.
     *
     * @param booked Whether searched rooms are booked or not.
     * @return The list of rooms that can be removed.
     */
    public ArrayList<Room> findRooms(boolean booked) {
        ArrayList<Room> newRooms = new ArrayList<>();
        for (Room room: rooms) {
            if (room.isBooked() == booked)
                newRooms.add(room);
        }
        return newRooms;
    }

    /**
     * Checks if the hotel has any booking in any room.
     *
     * @return True if there is a booking and false otherwise.
     */
    public boolean hasBooking() {
        for (Room room: rooms) {
            if (room.isBooked())
                return true;
        }
        return false;
    }
}
