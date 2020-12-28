package TankGame.repositories;

import TankGame.models.Game;

public interface GamesRepository {
    void save(Game game);

    Game findById(Long gameId);

    void update(Game game);
}
