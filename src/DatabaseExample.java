/**
 * This is a class
 * Created 2020-03-10
 *
 * @author Magnus Silverdal
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseExample {
    public static void main(String[] args) {
        try {
            // Set up connection to database
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://" + DatabaseLoginData.DBURL + ":" + DatabaseLoginData.port + "/" + DatabaseLoginData.DBname +
                            "? allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    DatabaseLoginData.user, DatabaseLoginData.password);

            // Setup statement
            Statement stmt = conn.createStatement();
            Scanner tgb = new Scanner(System.in);
            int currentRoom = 1;

            // Create query and execute
            while (currentRoom != 0) {
                String strSelect = "select body from story where id = " + currentRoom;
                //System.out.println("The SQL statement is: " + strSelect + "\n");

                ResultSet rset = stmt.executeQuery(strSelect);

                // Loop through the result set and print
                //System.out.println("The records selected are:");
                while (rset.next()) {
                    String body = rset.getString("body");
                    System.out.println(body);
                }

                strSelect = "select description, storyLink from links where storyId = " + currentRoom;
                //System.out.println("The SQL statement is: " + strSelect + "\n");

                rset = stmt.executeQuery(strSelect);
                ArrayList<Integer> storyLinks = new ArrayList();

                // Loop through the result set and print
                //System.out.println("The records selected are:");
                int rowCount = 1;
                while (rset.next()) {
                    String description = rset.getString("description");
                    int storyLink = rset.getInt("storyLink");
                    storyLinks.add(storyLink);
                    System.out.println(rowCount + " " + description);
                    ++rowCount;
                }

                if (rowCount == 1) {
                    System.out.println("Thanks for playing...");
                    currentRoom = 0;

                } else {

                    System.out.println("Make your choice: ");
                    int input = tgb.nextInt();
                    while (input < 1 || input > storyLinks.size()) {
                        System.out.println("Illegal choice, try again");
                        input = tgb.nextInt();
                    }
                    currentRoom = storyLinks.get(input - 1);
                }
            }
            // Close conn and stmt
            conn.close();
            stmt.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}

