package database;


import model.Timemachine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimemachineRepository {
    public List<Timemachine> initializeTimeMachine() throws SQLException {
        List<Timemachine> timemachines = new ArrayList<>();
        String sql = "SELECT * FROM TimeMachines";

        try (Connection conn = databasecon.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Timemachine timemachine = createTimemachineFromReultset(rs);
                if(timemachine != null) {
                    timemachines.add(timemachine);

                }
            }
        }catch(SQLException e){
            throw new RuntimeException("failt to initialize timemachine");
        }
        return timemachines;
    }
    private Timemachine createTimemachineFromReultset(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        Integer capacity = rs.getInt("capacity");
        String status = rs.getString("status");

        return new Timemachine (id, name, capacity, status);
    }
    public void saveTimemachine(String name, Integer capacity, String status) throws SQLException {
        String sql = "INSERT INTO timemachines (name,capacity,status) VALUES(?,?,?)";
        try(Connection conn = databasecon.getConnection()){
            conn.setAutoCommit(false);
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, name);
                pstmt.setInt(2, capacity);
                pstmt.setString(3, status);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("save failed");
        }
    }
    public Timemachine findById(int id) throws SQLException {
        String sql = "SELECT * FROM timeMachines WHERE id = ?";

        try (Connection conn = databasecon.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return createTimemachineFromReultset(rs);
            }
            return null;
        }
    }
}