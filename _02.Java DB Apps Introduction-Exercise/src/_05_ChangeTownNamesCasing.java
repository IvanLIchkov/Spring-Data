import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _05_ChangeTownNamesCasing {
    private static final String GET_TOWNS_NAMES_AND_ID = "SELECT id, name from towns\n" +
            "WHERE country LIKE ?";

    private static final String UPDATE_TOWN_NAME = "Update towns \n" +
            "set name = ?\n" +
            "where id = ?";
    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();
        Scanner sc = new Scanner(System.in);
        String countryName = sc.nextLine();

        PreparedStatement getTownNameIdPrepare = connection.prepareStatement(GET_TOWNS_NAMES_AND_ID);
        getTownNameIdPrepare.setString(1, countryName);
        ResultSet getTownNameIdResult = getTownNameIdPrepare.executeQuery();
        List<String> editedTowns = new ArrayList<>();
        if (!getTownNameIdResult.next()) {
            System.out.println("No town names were affected.");
            return;
        } else {
            do {
                final int townId = getTownNameIdResult.getInt("id");
                final String townName = (getTownNameIdResult.getString("name")).toUpperCase();

                PreparedStatement updateTownNamePrepare = connection.prepareStatement(UPDATE_TOWN_NAME);
                updateTownNamePrepare.setString(1, townName);
                updateTownNamePrepare.setInt(2, townId);
                updateTownNamePrepare.executeUpdate();
                editedTowns.add(townName);
            } while (getTownNameIdResult.next());
        }
        System.out.printf("%d town names were affected.", editedTowns.size());
        System.out.printf("[%s]%n", String.join(", ", editedTowns));
    }
    }
