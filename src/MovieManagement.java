import java.sql.*;

public class MovieManagement {
    private static final String URL = "jdbc:postgresql://localhost:5432/demo_jdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = "200426";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void addMovie(String title, String director, int year) {
        String sqlAdd = "CALL add_movie(?,?,?)";
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall(sqlAdd);
            callStmt.setString(1, title);
            callStmt.setString(2, director);
            callStmt.setInt(3, year);
            callStmt.execute();
            System.out.println("=> Them phim thanh cong!");
        } catch (SQLException e) {
            System.err.println("Loi khi them phim: " + e.getMessage());
        }
    }

    public void listMovies() {
        String sqlList = "SELECT * FROM list_movies()";
        PreparedStatement pstmt = null;
        try {
            pstmt = getConnection().prepareStatement(sqlList);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- DANH SACH PHIM ---");
            System.out.printf("%-5s %-30s %-20s %-5s\n", "ID", "Title", "Director", "Year");
            System.out.println("-".repeat(65));

            while (rs.next()) {
                System.out.printf("%-5d %-30s %-20s %-5d\n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("director"),
                        rs.getInt("year"));
            }
        } catch (SQLException e) {
            System.err.println("Loi khi lay danh sach: " + e.getMessage());
        }
    }

    public void updateMovie(int id, String title, String director, int year) {
        String sqlUpdate = "CALL update_movie(?,?,?,?)";
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall(sqlUpdate);
            callStmt.setInt(1, id);
            callStmt.setString(2, title);
            callStmt.setString(3, director);
            callStmt.setInt(4, year);
            callStmt.execute();
            System.out.println("=> Cap nhat phim thanh cong!");
        } catch (SQLException e) {
            System.err.println("Loi khi cap nhat phim: " + e.getMessage());
        }
    }

    public void deleteMovie(int id) {
        String sqlDelete = "CALL delete_movie(?)";
        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall(sqlDelete);
            callStmt.setInt(1, id);
            callStmt.execute();
            System.out.println("=> Xoa phim thanh cong!");
        } catch (SQLException e) {
            System.err.println("Loi khi xoa phim: " + e.getMessage());
        }
    }
}
