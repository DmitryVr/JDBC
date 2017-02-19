import connection.DBConnection;
import java.util.List;

import model.Movie;

public class Main {
    public static void main(String[] args) {
        DBConnection connect = new DBConnection("localhost", "root", "root", "movieDatabase");
        connect.init();

//        connect.insert("INSERT INTO movie (nameRussian, nameOriginal) VALUES('Мост', 'Bron');");

//        connect.updateQuery("INSERT INTO movie (nameRussian, nameOriginal) VALUES('Мост 3', 'Bron 3')");
//        connect.updateQuery("UPDATE movie SET nameRussian='NEW' WHERE id=2");
        connect.updateQuery("DELETE FROM movie WHERE id=2");


        List<Movie> movieList;
        movieList = connect.getResultList("SELECT * FROM movie");
        for (Movie movie : movieList) {
            System.out.println(movie);
        }

        System.out.println();

        connect.showPreparedStatement();

        connect.closeConnection();
    }
}
