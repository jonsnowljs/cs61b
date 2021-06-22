package model;

public class Room implements IRoom {

    private String roomNumber;
    private Double price;
    private RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }


    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "RoomNumber: " + roomNumber + "    " +
                  enumeration  + " bed Room" + "    " +
                "Price: " + price;
                }

    //override equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        IRoom room = (IRoom) o;
        return roomNumber.equals(room.getRoomNumber())
                && (price == room.getRoomPrice())
                && enumeration.equals(room.getRoomType());

    }

    @Override
    public int hashCode() {
        int result = (int) (price * 32);
        result = 31 * result + roomNumber.hashCode();
        result = 31 * result + enumeration.hashCode();
        return result;
    }



}

