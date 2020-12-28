package TankGame.services;

public interface TanksService {
    //    // срабатывает при входе пользователя в игру
//    // если пользователь с таким никнеймом уже есть, то мы используем его
//    // если пользователя с таким никнеймом еще нет - то создаем его
//    Player createOrUpdatePlayer(Player player);
//
//    // срабатываем при начале
//    Game startGame(Game game);
//
//    // срабатывает при завершении игры
//    void finishGameForPlayers(Game game);
//
    Long startGame(String firstPlayerNickname, String secondPlayerNickname);

    void shot(Long gameId, String shooterNickname, String targetNickname);

}
