package service;

import database.BookingRepository;
import database.GuideRepository;
import database.TimePeriodRepository;
import database.TimemachineRepository;
import model.Guide;
import model.TimePeriod;
import model.Timemachine;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class BookingService {
    private final GuideRepository guideRepo = new GuideRepository();
    private final TimemachineRepository tmRepo = new TimemachineRepository();
    private final TimePeriodRepository timeRepo = new TimePeriodRepository();
    private final BookingRepository bookingRepo = new BookingRepository();

    public List<Guide> getGuides() {
        try {
            return guideRepo.initializeGuide();
        } catch (SQLException e) {
            throw new RuntimeException("could not load guide", e);
        }
    }

    public List<Timemachine> getAvailableTimeMachines() {
        try {
            return tmRepo.initializeTimeMachine();
        } catch (SQLException e) {
            throw new RuntimeException("could not load timeMachine", e);
        }
    }

    public List<TimePeriod> getTimePeriods() {
        try {
            return timeRepo.initializeTimeperiod();
        } catch (SQLException e) {
            throw new RuntimeException("could not load timePeriod", e);
        }
    }

    public void CreateBooking(
            int customerid,
            int timeMachineid,
            int timePeriodid,
            int guideid
    ) {
        if (customerid < 0 || timeMachineid < 0 || timePeriodid < 0 || guideid < 0) {
            throw new IllegalStateException("all fields must be filled");
        }
        try {
            Timemachine tm = tmRepo.findById(timeMachineid);

            if (tm == null) {
                throw new IllegalStateException("Selected time machine does not exist");
            }

            if (!tm.getStatus().equalsIgnoreCase("Available")) {
                throw new IllegalStateException("Time machine is not available");
            }
            bookingRepo.saveBooking(customerid, timeMachineid, timePeriodid, guideid);
        } catch (SQLException e) {
            throw new RuntimeException("could not create booking", e);
        }
    }
}
