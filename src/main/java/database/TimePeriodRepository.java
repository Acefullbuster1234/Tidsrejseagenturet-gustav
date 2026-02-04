package database;

import model.TimePeriod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimePeriodRepository{
        public List<TimePeriod> initializeTimeperiod()throws SQLException {
        List<TimePeriod> timePeriods = new ArrayList<>();
        String sql = "SELECT * FROM timePeriods";

        try (Connection conn = databasecon.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TimePeriod timePeriod = createTimeperiodFromResultSet(rs);
                if (timePeriod != null) {
                    timePeriods.add(timePeriod);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("failed to initialize timeperiods");
        }
        return timePeriods;
    }
    private TimePeriod createTimeperiodFromResultSet(ResultSet rs) throws SQLException {
            Integer id = rs.getInt("id");
            String name = rs.getNString("name");
            String description = rs.getNString("description");

            return new TimePeriod(id, name, description);
    }
}

