import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class _07_PrintAllMinionNames {

    private static final String GET_MINIONS_NAMES = "SELECT name from minions";
    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();

        PreparedStatement selectNamesPrepare = connection.prepareStatement(GET_MINIONS_NAMES);
        ResultSet selectNamesResult = selectNamesPrepare.executeQuery();

        ArrayDeque<String> namesOFMinions = new ArrayDeque<>();
        while (selectNamesResult.next()){
            final String name = selectNamesResult.getString("name");
            namesOFMinions.push(name);
        }

        while (namesOFMinions.size()>0){
            System.out.println(namesOFMinions.pollLast());
            System.out.println(namesOFMinions.pollFirst());
        }
    }
}
