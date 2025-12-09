package hei.group.devoirprog3.Connection;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBConnection {
    final  String URL = "jdbc:postgresql://localhost:5432/product_manager_db";
    final  String USER = "product_manager_user";
    final  String PASSWORD = "123456";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        try (Connection conn = db.getConnection()) {
            if (conn != null) {
                System.out.println("Connexion réussie à la base de données !");
            } else {
                System.out.println("Échec de la connexion !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
