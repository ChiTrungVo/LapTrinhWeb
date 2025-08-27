package vn.iotstar.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // để xử lý kết quả SELECT
import java.sql.SQLException;

public class DBconnect {
	private final String serverName = "localhost";
	private final String dbName = "GiaoVien";
	private final String portNumber = "1433";
	private final String instance = "";

	public Connection getConnectionW() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber
				+ ";integratedSecurity=true;databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true";
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";integratedSecurity=true;databaseName="
					+ dbName + ";encrypt=true;trustServerCertificate=true";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url);
	}

	public static void main(String[] args) {
		try (Connection conn = new DBconnect().getConnectionW()) {
			System.out.println("Kết nối thành công: " + conn);

			// Câu lệnh thêm giáo viên
			String sql = "INSERT INTO accounts (id, username, pass, fullname) VALUES (?, ?, ?, ?)";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				// Thay giá trị tùy ý
				stmt.setInt(1, 1); // id
				stmt.setString(2, "trungvo"); // tên
				stmt.setString(3, "1234");
				stmt.setString(4, "Vo Chi Trung");
				int rows = stmt.executeUpdate();
				System.out.println("Đã thêm " + rows + " dòng vào bảng accounts.");
			}
			String sqls = "SELECT * FROM accounts";
			try (PreparedStatement stmt = conn.prepareStatement(sqls)) {
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					int id = rs.getInt("id");
					String username = rs.getString("username");
					String pass = rs.getString("pass");
					String fullname=rs.getString("fullname");

					System.out.println(id + " - " + username + " - " + pass+" - "+fullname);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
