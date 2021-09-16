package models.players;

import repositories.interfaces.CardRepository;

public class Advanced  extends BasePlayer{
    private static final int HEALTH_POINT_DEFAULT = 250;


    public Advanced(CardRepository cardRepository, String userName) {
        super(cardRepository, userName, HEALTH_POINT_DEFAULT);
    }
}
