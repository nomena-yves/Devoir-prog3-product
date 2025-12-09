package hei.group.devoirprog3.DataRedriver;

import hei.group.devoirprog3.Connection.DBConnection;
import hei.group.devoirprog3.Entity.Category;
import hei.group.devoirprog3.Entity.Product;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    private final DBConnection dbConnection;

    public DataRetriever(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Category> getAllCategories()throws java.sql.SQLException {
        String SQL = "SELECT id,name FROM Product_category";
        List<Category> categories = new ArrayList<>();
        try(Connection conn =dbConnection.getConnection();
            PreparedStatement stmt =conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery()){
             while (rs.next()){
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getNString("name")
                );
                categories.add(category);
             }
             return categories;
        }
    }


    public List<Product> getAllProducts(int page, int size)throws java.sql.SQLException {
        int offset = (page - 1) * size;
       String sql= "SELECT * FROM Product limit? offset?";
       List<Product> products = new ArrayList<>();
       try(Connection conn =dbConnection.getConnection();
       PreparedStatement stmt =conn.prepareStatement(sql);){
           stmt.setInt(1, size);
           stmt.setInt(2, offset);

           ResultSet rs = stmt.executeQuery();
           while (rs.next()){
               Instant createdAt = rs.getTimestamp("creation_datime").toInstant();
               Product product = new Product(
                       rs.getInt("id"),
                       rs.getString("name"),
                       createdAt,
                       null
               );
               products.add(product);
           }
       }
       return products;
    }
}
