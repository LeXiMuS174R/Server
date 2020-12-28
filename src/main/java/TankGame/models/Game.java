package TankGame.models;

public class Game {
    private Long id;
    private String gameDate;
    private Player first;
    private Player second;
    private Long gameTime;
    private Integer firstPlayerShotsCount = 0;
    private Integer secondPlayerShotsCount = 0;

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public Player getFirst() {
        return first;
    }

    public void setFirst(Player first) {
        this.first = first;
    }

    public Player getSecond() {
        return second;
    }

    public void setSecond(Player second) {
        this.second = second;
    }

    public Long getGameTime() {
        return gameTime;
    }

    public void setGameTime(Long gameTime) {
        this.gameTime = gameTime;
    }

    public Integer getFirstPlayerShotsCount() {
        return firstPlayerShotsCount;
    }

    public void setFirstPlayerShotsCount(Integer firstPlayerShotsCount) {
        this.firstPlayerShotsCount = firstPlayerShotsCount;
    }

    public Integer getSecondPlayerShotsCount() {
        return secondPlayerShotsCount;
    }

    public void setSecondPlayerShotsCount(Integer secondPlayerShotsCount) {
        this.secondPlayerShotsCount = secondPlayerShotsCount;
    }
}
