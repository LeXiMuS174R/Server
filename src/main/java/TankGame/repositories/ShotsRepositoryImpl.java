package TankGame.repositories;

import TankGame.models.Shot;

public class ShotsRepositoryImpl implements ShotsRepository {
    public void save(Shot shot) {
        System.out.println("ShootRepository - saved " + shot.getShooter().getName() + " shoot to "
                + shot.getTarget().getName() + " with game id = " + shot.getGame().getId());
    }
}
