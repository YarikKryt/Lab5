import commandPattern.*;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException{
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://localhost:1433;databaseName=TaxiDepotApp;encrypt=true;trustServerCertificate=true;integratedSecurity=true;";

        try{
            Connection connection;
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                System.out.println("Connected");
            }


            Statement statement = connection.createStatement();
            AccountManager acc = new AccountManager(statement);
            Scanner sc= new Scanner(System.in);
            System.out.println("If you want to log in   - press 0");
            System.out.println("If you want to register - press 1");
            while (true) {
                String choice = sc.nextLine();
                if(choice.equals("0") || choice.equals("1"))
                {
                    if (choice.equals("0"))
                        acc.logInAccount();
                    if (choice.equals("1"))
                        acc.createAccount();
                    break;
                }
                System.out.println("You didn't choose between 0 and 1");
            }

            String sqlCommand = "select * from UserAccount";

            ResultSet resultSet = statement.executeQuery(sqlCommand);

            while (resultSet.next()){
                int ID = resultSet.getInt("ID");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");

                System.out.printf("%d | %s | %s\n", ID, username, password);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("An error occurred:");
            throw new RuntimeException(e);
        }
    }
}
