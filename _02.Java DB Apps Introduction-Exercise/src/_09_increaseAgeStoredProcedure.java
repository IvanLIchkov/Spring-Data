import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class _09_increaseAgeStoredProcedure {

    private static final String INCREASE_AGE_OF_MINION_AND_GET_NAME_AND_AGE = "call usp_get_older(?)";
    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();

        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        PreparedStatement updateAgePrepare = connection.prepareStatement(INCREASE_AGE_OF_MINION_AND_GET_NAME_AND_AGE);
        updateAgePrepare.setInt(1, id);
        ResultSet updateAgeResult = updateAgePrepare.executeQuery();

        updateAgeResult.next();
        System.out.printf("%s %d%n",updateAgeResult.getString("name"),updateAgeResult.getInt("age"));
    }
}
