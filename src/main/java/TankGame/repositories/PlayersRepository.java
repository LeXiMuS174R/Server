package TankGame.repositories;

import TankGame.models.Player;

public interface PlayersRepository {
    Player findByName(String nickname);

    void save(Player player);

    void update(Player player);
}
