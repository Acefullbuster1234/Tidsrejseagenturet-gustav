package database;

import model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    public List<Booking> initializeBooking() throws SQLException {
        List<Booking> Bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";


        Booking booking = null;
        try (Connection conn = databasecon.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                booking = createBookingFromResult(rs);
                if (booking != null) {
                    Bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("failed to initialize booking");
        }
        return Bookings;
    }

    private Booking createBookingFromResult(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int customerId = rs.getInt("customer_id");
        int timeMachineid = rs.getInt("time_machine_id");
        int timePeriodid = rs.getInt("time_period_id");
        int guideid = rs.getInt("guide_id");

        return new Booking(id, customerId, timeMachineid, timePeriodid, guideid);
    }

    public void saveBooking(int customerid, int timeMachineid, int timePeriodid, int guideid) throws SQLException {
        String sql = "INSERT INTO bookings (customer_id, time_machine_id, time_period_id, guide_id) VALUES (?,?,?,?)";
        try(Connection conn = databasecon.getConnection()){
            conn.setAutoCommit(false);
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, customerid);
                pstmt.setInt(2, timeMachineid);
                pstmt.setInt(3, timePeriodid);
                pstmt.setInt(4, guideid);
                pstmt.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("save failed");
        }
    }
    public void deleteBooking(int id) throws SQLException {
        String sql = "DELETE FROM bookings WHERE id = (SELECT id FROM bookings where id = ?)";
        try(Connection conn = databasecon.getConnection()){
            conn.setAutoCommit(false);
            try(PreparedStatement deleteStmt = conn.prepareStatement(sql)){
                deleteStmt.setInt(1, id);
                int rowsAffected = deleteStmt.executeUpdate();
                if(rowsAffected == 0){
                    throw new SQLException("could not delete booking");
                }
            }

        }
    }
}

