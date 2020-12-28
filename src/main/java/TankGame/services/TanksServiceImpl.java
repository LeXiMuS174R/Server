package TankGame.services;

import TankGame.models.Game;
import TankGame.models.Player;
import TankGame.models.Shot;
import TankGame.repositories.*;

import java.time.LocalDate;

public class TanksServiceImpl implements TanksService {

    private ShotsRepository shotsRepository;
    private PlayersRepository playersRepository;
    private GamesRepository gamesRepository;

    public TanksServiceImpl(ShotsRepository shotsRepository, PlayersRepository playersRepository, GamesRepository gamesRepository) {
        this.shotsRepository = shotsRepository;
        this.playersRepository = playersRepository;
        this.gamesRepository = gamesRepository;
    }

    public Long startGame(String firstPlayerNickname, String secondPlayerNickname) {
        Player firstPlayer = playersRepository.findByName(firstPlayerNickname);
        Player secondPlayer = playersRepository.findByName(secondPlayerNickname);
        // если пользователи новые
        if (firstPlayer == null) {
            firstPlayer = new Player(null, firstPlayerNickname, null);
            playersRepository.save(firstPlayer);
        } else {
            // TODO: обновить IP
        }

        if (secondPlayer == null) {
            secondPlayer = new Player(null, secondPlayerNickname, null);
            playersRepository.save(secondPlayer);
        } else {
            // TODO: обновить IP
        }

        Game game = new Game();
        game.setFirst(firstPlayer);
        game.setSecond(secondPlayer);
        game.setGameDate(LocalDate.now().toString());

        gamesRepository.save(game);
        return game.getId();
    }

    public void shot(Long gameId, String shooterNickname, String targetNickname) {
        Player shooter = playersRepository.findByName(shooterNickname);
        Player target = playersRepository.findByName(targetNickname);
        Game game = gamesRepository.findById(gameId);
        Shot shot = new Shot(shooter, target, game);
        if (game.getFirst().equals(shooter)) {
            game.setFirstPlayerShotsCount(game.getFirstPlayerShotsCount() + 1);
        } else if (game.getSecond().equals(shooter)) {
            game.setSecondPlayerShotsCount(game.getSecondPlayerShotsCount() + 1);
        }
        shotsRepository.save(shot);
        gamesRepository.update(game);
        System.out.println(" shots: " +
                game.getFirstPlayerShotsCount() + "|" + game.getSecondPlayerShotsCount());
    }
}
