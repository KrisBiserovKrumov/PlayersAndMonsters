package models.players;

import models.players.interfaces.Player;
import repositories.CardRepositoryImpl;
import repositories.interfaces.CardRepository;

import static common.ConstantMessages.*;


public abstract class  BasePlayer implements Player {
    private static final int HEALTH_POINTS_MIN = 0;
    private static final int DAMAGE_POINTS_MIN = 0;

    private String userName;
    private int health;
    private CardRepository cardRepository;
    private boolean isDead;

    protected BasePlayer(CardRepository cardRepository,String userName, int health) {
        this.setUserName(userName);
        this.setHealth(health);
        this.cardRepository = new CardRepositoryImpl();
        this.setDead(false);
    }

    @Override
    public CardRepository getCardRepository() {
        return this.cardRepository;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int healthPoints) {
        if (healthPoints < HEALTH_POINTS_MIN ){
            throw new IllegalArgumentException(PLAYER_HEALTH_LESS_THAN_ZERO);
        }
        this.health = healthPoints;

    }

    @Override
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public void takeDamage(int damagePoints) {
        if (damagePoints < DAMAGE_POINTS_MIN){
            throw new IllegalArgumentException("Damage points cannot be less than zero.");
        }
       this.health -= damagePoints;
        if (this.health <= HEALTH_POINTS_MIN){
            this.health = HEALTH_POINTS_MIN;
            this.isDead = true;

        }

    }

    @Override
    public String toString() {
        int cardCount = this.cardRepository.getCount();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(PLAYER_REPORT_INFO,this.getUsername(),this.getHealth(),cardCount))
                .append(System.lineSeparator());

        this.getCardRepository().getCards().forEach(e->sb.append(e.toString()).append(System.lineSeparator()));

        sb.append(DEFAULT_REPORT_SEPARATOR);
        return sb.toString().trim();
    }

    private void setUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()){
            throw  new IllegalArgumentException(PLAYER_USERNAME_NULL_OR_EMPTY);
        }
        this.userName = userName;
    }

    private void setDead(boolean dead) {
        this.isDead = dead;
    }
}
