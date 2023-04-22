package WritingOwnDataRetrievalApplication;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
//        System.out.print("Enter username default (root): ");
//        String user = scan.nextLine();
//        user = user.equals("") ? "root" : user;
//        System.out.println();
//
//        System.out.print("Enter password default (empty):");
//        String password = scan.nextLine().trim();
//        System.out.println();

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "Vanko10plus1");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", properties);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT first_name, last_name,COUNT(ug.id) AS count FROM users \n" +
                        "LEFT JOIN\n" +
                        "\tusers_games AS ug ON users.id = ug.user_id\n" +
                        "WHERE user_name = ?\n" +
                        "Group by users.id");

        String username = scan.nextLine();
        stmt.setNString(1, username);
        ResultSet rs = stmt.executeQuery();
        int count = 0;
        String firstName = "";
        String lastName = "";
        if(rs.next()){
            count = rs.getInt("count");
            firstName = (rs.getString("first_name"));
            lastName = (rs.getString("last_name"));
            System.out.printf("User: %s\n%s %s has played %d games",username,firstName,lastName,count);
        }else{
            System.out.println("No such user exists");
        }
       connection.close();

    }
}
