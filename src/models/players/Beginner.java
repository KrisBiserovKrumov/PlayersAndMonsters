package models.players;

import repositories.interfaces.CardRepository;

public class Beginner extends BasePlayer{
    private static final int HEALTH_POINT_DEFAULT = 50;


    public Beginner(CardRepository cardRepository, String userName) {
        super(cardRepository, userName, HEALTH_POINT_DEFAULT);
    }
}
