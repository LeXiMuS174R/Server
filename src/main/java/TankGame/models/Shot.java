package TankGame.models;

public class Shot {
    private Player shooter;
    private Player target;
    private Game game;

    public Shot(Player shooter, Player target, Game game) {
        this.shooter = shooter;
        this.target = target;
        this.game = game;

    }

    public Player getShooter() {
        return shooter;
    }

    public Player getTarget() {
        return target;
    }

    public Game getGame() {
        return game;
    }
}
