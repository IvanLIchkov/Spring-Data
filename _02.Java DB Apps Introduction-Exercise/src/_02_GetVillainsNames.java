import java.sql.*;
import java.util.Properties;

public class _02_GetVillainsNames {
    private static final String GET_VILLIANS_NAMES = "SELECT v.`name`, COUNT(distinct mv.minion_id) AS minions_count FROM villains AS v\n" +
            "JOIN minions_villains mv on v.id = mv.villain_id\n" +
            "GROUP BY mv.villain_id\n" +
            "HAVING minions_count >15\n" +
            "ORDER BY minions_count DESC" ;
    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();


       final PreparedStatement stmt = connection.prepareStatement(GET_VILLIANS_NAMES);

        final ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            System.out.printf("%s %s\n",rs.getString("name"),rs.getString("minions_count"));
        };
        connection.close();
    }
}