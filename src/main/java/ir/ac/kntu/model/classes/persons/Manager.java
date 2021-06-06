package ir.ac.kntu.model.classes.persons;

import ir.ac.kntu.model.classes.markets.Market;
import ir.ac.kntu.model.enums.AccountType;

import java.util.Objects;


public class Manager extends Account {

    private Market market;

    public Manager(String id, String name, String username, String password){
        super(id, name, username, password, AccountType.MANAGER);
        market = null;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return "Type: " + getAccountType() +
                " |ID: " + getId() +
                " |Name: " + getName() +
                " |Username: " + getUsername() +
                " |Password: " + getPassword();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Manager)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Manager manager = (Manager) o;
        return Objects.equals(getMarket(), manager.getMarket());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMarket());
    }
}
