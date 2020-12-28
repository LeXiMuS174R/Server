package TankGame.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
//@AllArgsConstructor

public class Player {
    private Long id;
    private String Name;
    private String ip;

    public Player(Long id, String name, String ip) {
        this.id = id;
        Name = name;
        this.ip = ip;
    }

//    public Player(String ip) {
//        this.ip = ip;
//    }
}
