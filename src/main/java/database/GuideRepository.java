package database;

import model.Guide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuideRepository {
    public List<Guide> initializeGuide() throws SQLException {
        List<Guide> Guides = new ArrayList<>();
        String sql = "SELECT * FROM guides";


        try(Connection conn = databasecon.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Guide guide = createGuideFromResultset(rs);
                if (guide != null) {
                    Guides.add(guide);
                }
            }
        }catch(SQLException e) {
            throw new RuntimeException("failed to initialize guide");
        }
        return Guides;
    }
    private Guide createGuideFromResultset(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        int id = rs.getInt("id");
        String specialty = rs.getString("specialty");

        return new Guide(id, name, specialty);
    }
}

