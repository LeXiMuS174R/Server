package TankGame.repositories;

import TankGame.models.Game;

public class GamesRepositoryImpl implements GamesRepository {

    // TODO: temp
    private Game game;

    @Override
    public void save(Game game) {
        System.out.println("Игра началась для игроков " + game.getFirst().getName() + " "
                + game.getSecond().getName() + " в " + game.getGameDate());
        game.setId(1L);
        this.game = game;

    }

    @Override
    public Game findById(Long gameId) {
        return game;
    }

    @Override
    public void update(Game game) {
        this.game.setFirstPlayerShotsCount(game.getFirstPlayerShotsCount());
        this.game.setSecondPlayerShotsCount(game.getSecondPlayerShotsCount());
    }
}
