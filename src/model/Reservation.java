package model;

/**
 * A reservation for a room in a hotel.
 */
public class Reservation {
    private String guestName; // Name of the guest making the reservation
    private int checkInDate; // Check-in date for the reservation (1-31)
    private int checkOutDate; // Check-out date for the reservation (1-31)
    private Room room; // Room reserved
    private double totalPrice = 0; // Total price for the reservation
    private double discount = 1;

    /**
     * Creates a new reservation with the guest name, check-in and check-out dates,
     * the room being reserved, and the discounted rate. Also calculates the total price
     * based on the number of nights, the room's price per night, and the discounted rate.
     *
     * @param guestName Name of the guest making the reservation
     * @param checkInDate Check-in date for the reservation (1-31)
     * @param checkOutDate Check-out date for the reservation (1-31)
     * @param room Room reserved for the guest
     * @param discount The discounted rate
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room room, double discount) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.discount = discount;

        for (int i = checkInDate; i < checkOutDate; i++) {
            this.totalPrice += room.getPricePerNight() * room.getHotel().getPriceRate(i);
        }

        if (discount != -1) {
            totalPrice *= discount;
        } else {
            totalPrice -= room.getPricePerNight() * room.getHotel().getPriceRate(checkInDate);
        }
    }

    /**
     * Returns the name of the guest making the reservation.
     *
     * @return Name of the guest
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * Returns the check-in date for the reservation.
     *
     * @return Check-in date (Day 1-31)
     */
    public int getCheckInDate() {
        return checkInDate;
    }

    /**
     * Returns the check-out date for the reservation.
     *
     * @return Check-out date (Day 1-31)
     */
    public int getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Returns the room reserved for the reservation.
     *
     * @return Room reserved (R1, R2, R3...)
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Returns the total price for the reservation.
     *
     * @return Total price for the reservation
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Returns the price per night for the given night of the reservation.
     *
     * @param date The given night of the reservationr
     * @return String representation of the price breakdown
     */
    public double getPriceBreakdown(int date) {
        if (discount != -1)
            return discount * room.getPricePerNight() * room.getHotel().getPriceRate(date);
        if (date == checkInDate)
            return 0;
        return room.getPricePerNight() * room.getHotel().getPriceRate(date);
    }

    /**
     * Checks whether the reservation has a conflict with the given
     * check-in and check-out dates.
     *
     * @param start The check-in date.
     * @param end The check-out date.
     * @return True if there is a conflict and false otherwise.
     */
    public boolean hasConflict(int start, int end) {
        return (start >= checkInDate && start < checkOutDate) ||
                (checkInDate >= start && checkInDate < end);
    }
}
