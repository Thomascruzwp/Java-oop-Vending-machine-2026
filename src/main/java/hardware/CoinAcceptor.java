package hardware;

import java.util.List;
import java.util.ArrayList;

public class CoinAcceptor {

    private List<Double> acceptedCoins;

    public CoinAcceptor() {
        acceptedCoins = new ArrayList<>();
        acceptedCoins.add(0.25);
        acceptedCoins.add(1.0);
        acceptedCoins.add(0.5);
    }

    public boolean accept(Coin coin) {
        return acceptedCoins.contains(coin.getValue());
    }
}