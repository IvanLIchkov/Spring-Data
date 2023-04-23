import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _04_AddMinions {
    private static final String GET_TOWN_ID_BY_GIVEN_NAME = "SELECT id from towns\n" +
            "where name LIKE ?";
    private static final String INSERT_TOWN_INTO_TOWNS_TABLE = "INSERT INTO towns(name) VALUES(?)";

    private static final String GET_VILLAIN_BY_GIVEN_NAME = "SELECT id FROM villains\n" +
            "WHERE name LIKE ?";

    private static final String INSERT_VILLAIN_INTO_VILLAINS = "INSERT INTO villains(name, evilness_factor) VALUES (?, 'evil')";

    private static final String INSERT_MINION_INTO_MINIONS = "INSERT INTO minions(name, age, town_id) VALUE (?,?,?)";

    private static final String GET_MINION_ID_BY_NAME = "SELECT id FROM minions\n" +
            "where name LIKE ?";
    private static final String INSERT_MINION_VILLAINS_TABLE = "INSERT INTO minions_villains(minion_id, villain_id) VALUE (?,?)";

    private static final String CHECK_MINION_VILLAIN_CONNECTION = "SELECT * from minions_villains\n" +
            "where villain_id = ? and minion_id = ?";
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        List<String> minionInfo = Arrays.stream(sc.nextLine().split(" ")).toList();
        final String minionName = minionInfo.get(1);
        final int minionAge = Integer.parseInt(minionInfo.get(2));
        final String minionTown = minionInfo.get(3);

        final String villainName = sc.nextLine().split(" ")[1];

        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement townPrepare = connection.prepareStatement(GET_TOWN_ID_BY_GIVEN_NAME);
        townPrepare.setString(1,minionTown);
         ResultSet townResult = townPrepare.executeQuery();
        int townId = 0;
        if(townResult.next()){
            townId=townResult.getInt("id");
        }else{
            final PreparedStatement townInsertState = connection.prepareStatement(INSERT_TOWN_INTO_TOWNS_TABLE);
            townInsertState.setString(1, minionTown);
            townInsertState.executeUpdate();
            System.out.printf("Town %s was added to the database.%n",minionTown);

            townResult = townPrepare.executeQuery();
            if(townResult.next()){
                townId = townResult.getInt("id");
            }
        }

        final PreparedStatement villainPrepare = connection.prepareStatement(GET_VILLAIN_BY_GIVEN_NAME);
        villainPrepare.setString(1, villainName);
        ResultSet villainResult = villainPrepare.executeQuery();
        int villainId = 0;
        if(villainResult.next()){
            villainId=villainResult.getInt("id");
        }else{
            final PreparedStatement villainAddPrepare = connection.prepareStatement(INSERT_VILLAIN_INTO_VILLAINS);
            villainAddPrepare.setString(1,villainName);
            villainAddPrepare.executeUpdate();
            System.out.printf("Villain %s was added to the database.%n",villainName);
            villainResult = villainPrepare.executeQuery();
            if(villainResult.next()){
                villainId = villainResult.getInt("id");
            }
        }

        final PreparedStatement getMinionIdPrepare = connection.prepareStatement(GET_MINION_ID_BY_NAME);
        getMinionIdPrepare.setString(1,minionName);
         ResultSet getMinionIdResult = getMinionIdPrepare.executeQuery();
        int minionId = 0;
        if(getMinionIdResult.next()){
            minionId = getMinionIdResult.getInt("id");
        }else{
            final PreparedStatement minionAddPrepare = connection.prepareStatement(INSERT_MINION_INTO_MINIONS);
            minionAddPrepare.setString(1,minionName);
            minionAddPrepare.setInt(2,minionAge);
            minionAddPrepare.setInt(3,townId);
            minionAddPrepare.executeUpdate();

            getMinionIdResult = getMinionIdPrepare.executeQuery();
            if(getMinionIdResult.next()){
                minionId = getMinionIdResult.getInt("id");
            }

        }



        final PreparedStatement minionsVillainsCheckPrepare = connection.prepareStatement(CHECK_MINION_VILLAIN_CONNECTION);
        minionsVillainsCheckPrepare.setInt(1,villainId);
        minionsVillainsCheckPrepare.setInt(2,minionId);
        final ResultSet minionsVillainsCheck = minionsVillainsCheckPrepare.executeQuery();
        if(!minionsVillainsCheck.next()){
            final PreparedStatement minionsVillainsInsertPrepare = connection.prepareStatement(INSERT_MINION_VILLAINS_TABLE);
            minionsVillainsInsertPrepare.setInt(1, minionId);
            minionsVillainsInsertPrepare.setInt(2,villainId);
            minionsVillainsInsertPrepare.executeUpdate();
            System.out.printf("Successfully added %s to be minion of %s.%n",minionName, villainName);
        }
    }
}
