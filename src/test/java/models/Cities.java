package models;

public enum Cities {

    LONDON("London", "GB", 2643743, -0.1257f, 51.5085f),
    RZESZOW("Rzeszów", "PL", 759734,21.999f, 50.0413f),
    MIAMI("Miami", "US", 4164138, -80.1937f, 25.7743f);

    private final String cityName;
    private final String country;
    private final int cityId;
    private final float longitude;
    private final float latitude;

    Cities(String cityName, String country, int cityId, float longitude, float latitude) {
        this.cityName = cityName;
        this.country = country;
        this.cityId = cityId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public int getCityId() {
        return cityId;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
}
