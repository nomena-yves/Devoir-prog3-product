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
    public List<Product> getProductsByCriteria(String productName, String categoryName, Instant creationMin, Instant creationMax)throws java.sql.SQLException{
    if (productName!=null){
        String sql= "SELECT * FROM Product WHERE name ILIKE ?";
        List<Product> products = new ArrayList<>();
        try(Connection conn =dbConnection.getConnection();
        PreparedStatement stmt =conn.prepareStatement(sql);){
            stmt.setString(1, productName);
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
            return products;
        }

    } else if (categoryName!=null) {
        String sql= "SELECT * FROM Product p inner join product_category c on p.id=c.id WHERE c.name ILIKE ?";
        List<Product> products = new ArrayList<>();
        try(Connection conn =dbConnection.getConnection();
        PreparedStatement stmt =conn.prepareStatement(sql);){
            stmt.setString(1, productName);
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
            return products;
        }

    } else if (creationMin!=null) {
        String sql= "SELECT * FROM Product WHERE creation_min<=?";
        List<Product> products = new ArrayList<>();
        try(Connection conn =dbConnection.getConnection();
        PreparedStatement stmt =conn.prepareStatement(sql);){
            stmt.setString(1, creationMin.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Instant createdAt = rs.getTimestamp("creation_datime").toInstant();
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getNString("name"),
                        createdAt,
                        null
                );
                products.add(product);
            }
            return products;
        }
    } else if (creationMax!=null) {
        String sql= "SELECT * FROM Product WHERE creation_max>=?";
        List<Product> products = new ArrayList<>();
        try(Connection conn =dbConnection.getConnection();
        PreparedStatement stmt =conn.prepareStatement(sql);){
            stmt.setString(1, creationMax.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Instant createdAt = rs.getTimestamp("creation_datime").toInstant();
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getNString("name"),
                        createdAt,
                        null
                );
                products.add(product);
            }
            return products;
        }
    }
    return null;
    }
    List<Product>
    getProductsByCriteria(String categoryName, Instant creationMin, Instant creationMax, int page, int size, String productName)throws java.sql.SQLException{
        int offset = (page - 1) * size;
        if (productName!=null){
            String sqlSelect= "SELECT * FROM Product WHERE name ILIKE ? and limit? offset?";
            List<Product> products = new ArrayList<>();
            try(Connection conn =dbConnection.getConnection();
                PreparedStatement stmt =conn.prepareStatement(sqlSelect);) {
                stmt.setString(1, productName);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Instant createdAt = rs.getTimestamp("creation_datime").toInstant();
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            createdAt,
                            null
                    );
                    products.add(product);
                }
                return products;
            }
            }else if (categoryName!=null) {
                String sqlSelects= "SELECT * FROM Product p inner join product_category c on p.id=c.id WHERE c.name ILIKE ? and limit? offset?";
                List<Product> products3 = new ArrayList<>();
                try(Connection conn =dbConnection.getConnection();
                    PreparedStatement stmt =conn.prepareStatement(sqlSelects);){
                    stmt.setString(1, productName);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()){
                        Instant createdAt = rs.getTimestamp("creation_datime").toInstant();
                        Product product = new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                createdAt,
                                null
                        );
                        products3.add(product);
                    }
                    return products3;
                }

            } else if (creationMin!=null) {
                String SQL= "SELECT * FROM Product WHERE creation_min<=? and limit? offset?";
                List<Product> products2 = new ArrayList<>();
                try(Connection conn =dbConnection.getConnection();
                    PreparedStatement stmt =conn.prepareStatement(SQL);){
                    stmt.setString(1, creationMin.toString());
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()){
                        Instant createdAt = rs.getTimestamp("creation_datime").toInstant();
                        Product product = new Product(
                                rs.getInt("id"),
                                rs.getNString("name"),
                                createdAt,
                                null
                        );
                        products2.add(product);
                    }
                    return products2;
                }
            } else if (creationMax!=null) {
                String sql= "SELECT * FROM Product WHERE creation_max>=? and limit? offset?";
                List<Product> products1 = new ArrayList<>();
                try(Connection conn =dbConnection.getConnection();
                    PreparedStatement stmt =conn.prepareStatement(sql);){
                    stmt.setString(1, creationMax.toString());
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()){
                        Instant createdAt = rs.getTimestamp("creation_datime").toInstant();
                        Product product = new Product(
                                rs.getInt("id"),
                                rs.getNString("name"),
                                createdAt,
                                null
                        );
                        products1.add(product);
                    }
                    return products1;
                }
            }
            return null;

        }
    }

