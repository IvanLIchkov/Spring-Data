import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class _03_GetMinionNames {

    private static final String GET_MINIONS_NAMES_AND_AGE_FOF_VILLAIN_ID = "SELECT m.name, m.age FROM minions_villains\n" +
            "         JOIN minions m on minions_villains.minion_id = m.id\n" +
            "         JOIN villains v on v.id = minions_villains.villain_id\n" +
            "where villain_id = ?";

    private static final String GET_VILLAIN_NAME_BY_ID = "SELECT name FROM villains where id = ?";
    private static final String VILLAIN_NAME = "name";
    private static final String MINION_NAME = "m.name";
    private static final String MINION_AGE = "age";

    private static final String NO_VILLAIN_WITH_ID = ("No villain with ID %d exists in the database.");

    private static final String MINION_NAME_AND_AGE =("%d. %s %d%n");
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement stmt = connection.prepareStatement(GET_MINIONS_NAMES_AND_AGE_FOF_VILLAIN_ID);
        final PreparedStatement vName = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);

        final int villainID = Integer.parseInt(sc.nextLine());
        stmt.setInt(1, villainID);
        vName.setInt(1, villainID);

        final ResultSet rs = stmt.executeQuery();
        final ResultSet villainNameInfo = vName.executeQuery();

        if(!villainNameInfo.next()){
            System.out.printf(NO_VILLAIN_WITH_ID,villainID);
            return;
        }
        final String villainName = villainNameInfo.getString(VILLAIN_NAME);
        System.out.printf("Villain: %s%n", villainName);
        int counter = 0;
        while (rs.next()){
            counter++;
            final String minionName = rs.getString(MINION_NAME);
            final int minionAge = Integer.parseInt(rs.getString(MINION_AGE));
            System.out.printf(MINION_NAME_AND_AGE,counter,minionName,minionAge);
        }
    }
}
