package hardware;

import java.util.ArrayList;
import java.util.List;

public class CoinAcceptor {

    private List<Coin> acceptedCoins = new ArrayList<>();

    public void accept(Coin coin) {
        acceptedCoins.add(coin);
    }
}