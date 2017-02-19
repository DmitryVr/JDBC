package connection;

import model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DBConnection {
    private String host;
    private String root;
    private String password;
    private String nameDb;
    private String url;

    private Properties properties = new Properties();

    private Connection mysqlConnect;

    public DBConnection(String host, String root, String password, String nameDb) {
        this.host = host;
        this.root = root;
        this.password = password;
        this.nameDb = nameDb;

        System.out.println("host: " + this.host + "\n" +
                           "root: " + this.root + "\n" +
                           "password: " + this.password + "\n" +
                           "nameDb: " + this.nameDb);

        this.initProperties();
    }

    private void initProperties() {
        url = "jdbc:mysql://" + host + "/" + nameDb;

        properties.setProperty("user", root);
        properties.setProperty("password", password);
        properties.setProperty("characterEncoding", "UTF-8");
        properties.setProperty("useUnicode", "true");

        System.out.println("URL: " + url);
    }

    public void init() {
        if (mysqlConnect == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver"); // зарегистрировать драйвер
                mysqlConnect = DriverManager.getConnection(url, properties);
                mysqlConnect.setAutoCommit(false); // не происходит транзакция после каждого запроса
                System.out.println("getConnection");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        try {
            if (mysqlConnect != null) {
                mysqlConnect.close();
                System.out.println("Close connection");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // метод для SELECT (statement)
    public ArrayList<Movie> getResultList(String query) {
        ArrayList<Movie> movieList = new ArrayList<>();

        System.out.println("Query: " + query);

        try (Statement stmt = mysqlConnect.createStatement();
             ResultSet result = stmt.executeQuery(query)) {

            while (result.next()) {
                Movie movie = new Movie();
                movie.setId(result.getInt("id"));
                movie.setNameRussian(result.getString("nameRussian"));
                movie.setNameOriginal(result.getString("nameOriginal"));

                movieList.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieList;
    }

    // метод для SELECT (PreparedStatement)
    // вместо вопросов значения
    // запрос компилируется один раз, потом подставляются значения
    public void showPreparedStatement() {
        ResultSet result = null;
        try (PreparedStatement pstmt = mysqlConnect.prepareStatement("SELECT * FROM movie WHERE nameRussian = ? or nameOriginal = ?")) {
            pstmt.setString(1, "Бойцовский клуб");
            pstmt.setString(2, "Dark Knight");

            result = pstmt.executeQuery();
            while (result.next()) {
                System.out.println(result.getString("nameRussian") + "|" + result.getString("nameOriginal"));
            }


            pstmt.setString(1, "Форрест Гамп");
            pstmt.setString(2, "Prometheus");

            result = pstmt.executeQuery();
            while (result.next()) {
                System.out.println(result.getString("nameRussian") + "|" + result.getString("nameOriginal"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // вставка, получение нескольких ResultSer
    public void insert(String query) {
        try (Statement stmt = mysqlConnect.createStatement()) {
            stmt.execute(query);
            mysqlConnect.commit(); // транзакция
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                mysqlConnect.rollback(); // отмена транзакции
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    // вставка, обновление, удаление, возвращает количество записей в которых было изменение
    public void updateQuery(String query) {
        try (Statement stmt = mysqlConnect.createStatement()) {
            stmt.executeUpdate(query);
            mysqlConnect.commit(); // транзакция
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                mysqlConnect.rollback(); // отмена транзакции
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
