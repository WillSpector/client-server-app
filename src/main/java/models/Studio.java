package models;

/**
 * Класс Студия, содержит её название и адрес.
 */

public class Studio {
    private final String studioName;
    private final String address;

    public Studio(String studioName, String address) {
        this.studioName = studioName;
        this.address = address;
    }


    /**
     * Возвращает название студии.
     */

    public String getStudioName() {
        return studioName;
    }

    /**
     * Возвращает адрес студии.
     */
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Studio name = " + studioName + "  " +
                "Studio address = " + address;
    }

}
