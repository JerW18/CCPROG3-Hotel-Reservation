package model;

import java.util.ArrayList;

/**
 * The HotelSystem class provides methods to manage a list of hotels,
 * check input validity, and handle various hotel-related operations.
 */
public class HotelSystem {

    private static ArrayList<Hotel> hotels = new ArrayList<Hotel>(); // A list to store hotel objects.
    private static Hotel hotel; // The current hotel viewed/managed by the user.
    private static Room room; // The current room viewed/managed by the user.
    private static ArrayList<Room> rooms; // The current rooms viewed/managed by the user.

    /**
     * Gets the list of hotels.
     *
     * @return An ArrayList containing all the hotels.
     */
    public static ArrayList<Hotel> getHotelList() {
        return hotels;
    }

    /**
     * Gets a hotel from the list by utilizing the index.
     *
     * @param i The index of the hotel to retrieve.
     * @return The Hotel object at the given index.
     */
    public static Hotel getHotel(int i) {
        return hotels.get(i);
    }

    /**
     * Returns the current hotel viewed/managed by the user.
     *
     * @return The current hotel viewed/managed by the user.
     */
    public static Hotel getCurrentHotel() {
        return hotel;
    }

    /**
     * Returns the current room viewed/managed by the user.
     *
     * @return The current room viewed/managed by the user.
     */
    public static Room getCurrentRoom() {
        return room;
    }

    /**
     * Returns the current rooms viewed/managed by the user.
     *
     * @return The current rooms viewed/managed by the user.
     */
    public static ArrayList<Room> getCurrentRooms() {
        return rooms;
    }

    /**
     * Checks if the input hotel name is a duplicate.
     *
     * @param name The input hotel name.
     * @return True if the name is a duplicate and false otherwise.
     */
    public static boolean checkDuplicate(String name) {
        for (Hotel hotel: hotels) {
            if (hotel.getName().equals(name))
                return true;
        }
        return false;
    }

    /**
     * Creates a new hotel with the given name. The hotel is not created
     * if the given name is a duplicate.
     *
     * @param name The name of the hotel.
     * @return True if the hotel is created and false otherwise.
     */
    public static boolean addHotel(String name) {
        if (checkDuplicate(name))
            return false;
        hotels.add(new Hotel(name));
        return true;
    }

    /**
     * Sets the current hotel based on the given index.
     *
     * @param index The index of the current hotel.
     * @return True if the index is valid and false otherwise.
     */
    public static boolean selectHotel(int index) {
        if (index < 0 || index >= hotels.size())
            return false;
        hotel = hotels.get(index);
        return true;
    }

    /**
     * Finds the available rooms based on the given check-in and check-out dates.
     *
     * @param start The check-in date.
     * @param end The check-out date.
     * @return True if there are available rooms and false otherwise.
     */
    public static boolean findAvailableRooms(int start, int end) {
        rooms = hotel.findAvailableRooms(start, end);
        return !rooms.isEmpty();
    }

    /**
     * Finds either rooms that are booked or not.
     *
     * @param booked Whether searched rooms are booked or not.
     * @return True if there are rooms found and false otherwise.
     */
    public static boolean findRooms(boolean booked) {
        rooms = hotel.findRooms(booked);
        return !rooms.isEmpty();
    }

    /**
     * Removes the current hotel from the list of hotels.
     */
    public static void removeHotel() {
        room = null;
        rooms = null;

        hotels.remove(hotel);
    }

    /**
     * Books a room based on the room index with the given guest name,
     * check-in and check-out dates, and discount.
     *
     * @param index The index of the room to be booked.
     * @param name The name of the guest.
     * @param start The check-in date.
     * @param end The check-out date.
     * @param discount The discounted rate.
     * @return True if the room is successfully booked and false otherwise.
     */
    public static boolean bookRoom(int index, String name, int start, int end, double discount) {
        if (index < 0 || index >= rooms.size())
            return false;
        rooms.get(index).bookRoom(name, start, end, discount);
        return true;
    }

    /**
     * Checks whether the given room index is valid.
     *
     * @param index The room index.
     * @return True if the room index is valid and false otherwise.
     */
    public static boolean checkRoomIndex(int index) {
        return index >= 0 && index < rooms.size();
    }

    /**
     * Removes a room based on the room index.
     *
     * @param index The index of the room to be removed.
     * @return True if the room is successfully removed and false otherwise.
     */
    public static boolean removeRoom(int index) {
        return hotel.removeRoom(rooms.get(index)); // should always be true
    }

    /**
     * Sets the current room based on the given index.
     *
     * @param index The index of the current room.
     * @return True if the index is valid and false otherwise.
     */
    public static boolean selectRoom(int index) {
        if (index < 0 || index >= rooms.size())
            return false;
        room = rooms.get(index);
        return true;
    }
}