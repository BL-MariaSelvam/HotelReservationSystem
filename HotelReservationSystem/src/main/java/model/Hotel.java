package main.java.model;

public class Hotel {

    private final String name;
    private final int rating;
    private final int weekdayRegularRate;
    private final int weekendRegularRate;
    private int weekdayRewardRate;
    private int weekendRewardRate;


    public Hotel(String name, int rating,
                 int weekdayRegularRate, int weekendRegularRate,
                 int weekdayRewardRate, int weekendRewardRate) {
        this.name = name;
        this.rating = rating;
        this.weekdayRegularRate = weekdayRegularRate;
        this.weekendRegularRate = weekendRegularRate;
        this.weekdayRewardRate = weekdayRewardRate;
        this.weekendRewardRate = weekendRewardRate;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getWeekdayRate(CustomerType customerType) {
        return customerType == CustomerType.REGULAR ?
                weekdayRegularRate : weekdayRewardRate;
    }

    public int getWeekendRate(CustomerType customerType) {
        return customerType == CustomerType.REGULAR ?
                weekendRegularRate : weekendRewardRate;
    }
    
    public void setRewardRates(int weekdayRewardRate, int weekendRewardRate) {
        this.weekdayRewardRate = weekdayRewardRate;
        this.weekendRewardRate = weekendRewardRate;
    }
}

