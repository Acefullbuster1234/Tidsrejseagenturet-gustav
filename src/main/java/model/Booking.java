package model;

import database.BookingRepository;

public class Booking {

    private final int id;
    private int customerId;
    private int timemachineId;
    private int timePeriodId;
    private int guideId;
    private Customer customer;
    private Timemachine timemachine;
    private TimePeriod timePeriod;
    private Guide guide;

    public Booking (int id, int customerId, int timemachineId, int timePeriodId, int guideId){
        this.id = id;
        this.customerId = customer.getId();
        this.timemachineId = timemachine.getId();
        this.timePeriodId = timePeriod.getId();
        this.guideId = guide.getId();
    }

    public int getId(){
        return id;
    }



    public int getCustomerId(){
        return customerId;
    }

    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }

    public int getTimemachineId(){
        return timemachineId;
    }

    public void setTimemachineId(int timemachineId){
        this.timePeriodId = timePeriodId;
    }

    public int getTimePeriodId(){
        return timePeriodId;
    }

    public void setTimePeriodId(int timePeriodId){
        this.timePeriodId = timePeriodId;
    }

    public int getGuideId(){
        return guideId;
    }

    public void setGuideId(int guideId){
        this.guideId = guideId;
    }
}
