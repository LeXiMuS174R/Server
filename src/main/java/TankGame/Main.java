package TankGame;

import TankGame.repositories.*;
import TankGame.server.GameServer;
import TankGame.services.TanksServiceImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class Main {

//    private static final String PG_USER = "postgres";
//    private static final String PG_PASSWORD = "12345";
//    private static final String PG_URL = "jdbc:postgresql://localhost:5432/Gamedb";
//
//    private static DataSource dataSource;

    public static void main(String[] args) {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/Gamedb");
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername("postgres");
        config.setPassword("12345");
        config.setMaximumPoolSize(20);

        HikariDataSource dataSource = new HikariDataSource(config);

        PlayersRepository playersRepository = new PlayersRepositoryImpl(dataSource);


//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(PG_URL, PG_USER, PG_PASSWORD);
//        } catch (SQLException throwables) {
//            throw new IllegalArgumentException(throwables);
//        }
//
//        Statement statement = null;
//
//        try {
//            statement = connection.createStatement();
////            String sql = "insert into players(ip, name) values('192.168.1.100', 'LeXiMuS')";
////            statement.executeUpdate(sql);
//        } catch (SQLException throwables) {
//            throw new IllegalArgumentException(throwables);
//        }
//
//        ResultSet resultSet = null;


        // TODO: передать DataSource
        GamesRepository gamesRepository = new GamesRepositoryImpl();
//        PlayersRepository playersRepository = new PlayersRepositoryImpl();
        ShotsRepository shotsRepository = new ShotsRepositoryImpl();
        GameServer gameServer = new GameServer(new TanksServiceImpl(shotsRepository, playersRepository, gamesRepository));
        gameServer.start(24758);
    }
}
