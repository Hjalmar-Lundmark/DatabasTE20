import javax.swing.*;
import java.sql.*;

public class DatabaseExample {
    public static void main(String[] args) {
        Connection conn = null;
        //String user = "magnus";
        /*JPasswordField pf = new JPasswordField();
        JOptionPane.showConfirmDialog(null, pf, "password?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        String password = new String(pf.getPassword());*/
        //String password = "test";

        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + dbData.URL + ":3306/" + dbData.host + "? "+
                    "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",dbData.user,dbData.pwd);
            //conn = DriverManager.getConnection("jdbc:mysql://10.70.45.159:3306/bookshop? "+
            //        "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            Statement stmt = conn.createStatement();
            String SQLQuery = "SELECT * FROM hl21forum";
            ResultSet result = stmt.executeQuery(SQLQuery);

            ResultSetMetaData metadata = result.getMetaData();

            int numCols = metadata.getColumnCount();
            for (int i = 1 ; i <= numCols ; i++) {
                System.out.println(metadata.getColumnClassName(i));
            }

            while (result.next()) {
                String output = "";
                output += result.getInt("id") + ", " +
                        result.getString("title") + ", " +
                        result.getString("content") + ", " +
                        result.getTimestamp("createdAt") + ", " +
                        result.getInt("authorId");
                System.out.println(output);
            }

            /*//INSERT command
            SQLQuery = "INSERT INTO hl21forum(title,content) VALUES ('"+"HEJ"+"', '"+"brödtext"+"')";
            stmt.executeUpdate(SQLQuery);
            */

            /*//UPDATE
            SQLQuery = "UPDATE hl21forum SET title='"+"Tjena"+"' WHERE id="+2+"";
            stmt.executeUpdate(SQLQuery);
            */

            /*//Full create post in my forum
            SQLQuery = "INSERT INTO hl21forum(title,content,authorId) VALUES ('"+"Java post 2"+"', '"+"Mer innehåll från java"+"', "+2+")";
            stmt.executeUpdate(SQLQuery);
            */

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
