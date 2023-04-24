import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class _08_IncreaseMinionsAge {

    private static final String UPDATE_MINIONS_AGE_BY_GIVEN_ID = "UPDATE minions\n" +
            "SET age = age+1\n" +
            "WHERE id = ?";

    private static final String GET_MINIONS_NAMES = "SELECT name, age from minions";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();
        Scanner sc = new Scanner(System.in);
        int[] ids= Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        PreparedStatement updateMinionsAge = connection.prepareStatement(UPDATE_MINIONS_AGE_BY_GIVEN_ID);
        for (int id : ids) {
            updateMinionsAge.setInt(1, id);
            updateMinionsAge.executeUpdate();
        }
        PreparedStatement selectMinionsPrepare = connection.prepareStatement(GET_MINIONS_NAMES);
        ResultSet selectMinionsResult = selectMinionsPrepare.executeQuery();
        while (selectMinionsResult.next()){
            System.out.printf("%s %d%n",selectMinionsResult.getString("name"),selectMinionsResult.getInt("age"));
        }
    }
}
