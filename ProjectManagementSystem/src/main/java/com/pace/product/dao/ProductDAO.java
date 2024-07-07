package com.pace.product.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pace.product.bean.Product;
import com.pace.product.util.DBUtil;

public class ProductDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/pro?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Sai@#786";

	private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products" + "  (name, country,price) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_PRODUCTS_BY_ID = "select id,name,country,price from products where id =?";
	private static final String SELECT_ALL_PRODUCTS= "select * from products";
	private static final String DELETE_PRODUCTS_SQL = "delete from products where id = ?;";
	private static final String UPDATE_PRODUCTS_SQL = "update products set name = ?, country =? , price = ?where id = ?;";

	public ProductDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertProduct(Product product) throws SQLException {
		System.out.println(INSERT_PRODUCTS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTS_SQL)) {
			preparedStatement.setString(1, product.getName());
			//preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(2, product.getCountry());
			preparedStatement.setFloat(3, product.getPrice());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Product selectProduct(int id) {
		Product product = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String country = rs.getString("country");
				Float price = rs.getFloat("price");
				product = new Product(id, name, country, price);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return product;
	}

	public List<Product> selectAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String country = rs.getString("country");
                float price = rs.getFloat("price");
                products.add(new Product(id, name, country, price));
            }
        }
        return products;
    }

	public boolean deleteProduct(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateProduct(Product product) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL);) {
			System.out.println("updated Product:"+statement);
			statement.setString(1, product.getName());
			//statement.setString(2, user.getEmail());
			statement.setString(2, product.getCountry());
			statement.setFloat(3, product.getPrice());
			statement.setInt(4, product.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
