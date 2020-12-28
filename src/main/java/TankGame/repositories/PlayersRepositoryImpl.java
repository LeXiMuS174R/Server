package TankGame.repositories;

import TankGame.models.Player;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class PlayersRepositoryImpl implements PlayersRepository {

    private static final String SQL_FIND_ALL = "SELECT * FROM players;";

    private static final String SQL_INSERT = "INSERT INTO players (name, ip) " + " VALUES (?,?);";

    private static final String SQL_UPDATE = "update players set " +
            "name = ?, " +
            "ip = ? where id = ?;";

    private final DataSource dataSource;

    public PlayersRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Player findByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            do if (resultSet.getObject("name", String.class).equals(name)) {
                return new Player(
                        resultSet.getObject("id", Long.class),
                        resultSet.getObject("name", String.class),
                        resultSet.getObject("ip", String.class)
                );
            } while (resultSet.next());

        } catch (SQLException e) {
            System.out.println("Произошло исключеение при обращении к БД " + e);
        }
        return null;
//        return new Player(null ,name, "192.168.10.100");
    }

    @Override
    public void save(Player player) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getIp());
            int insertedRowsCount = preparedStatement.executeUpdate();

            if (insertedRowsCount == 0) {
                throw new SQLException("Cannot save entity");
            }

        } catch (SQLException e) {
            System.out.println("Произошло исключеение при cохранении в БД " + e);
        }
    }

    @Override
    public void update(Player player) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getIp());
            int insertedRowsCount = preparedStatement.executeUpdate();

            if (insertedRowsCount == 0) {
                throw new SQLException("Cannot update entity");
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
