public class BestTimeToBuySellStock {
    public static void main(String[] args) {
        BestTimeToBuySellStock bestTimeToBuySellStock = new BestTimeToBuySellStock();
    }

    public int maxProfit(int[] prices) {
        int mp = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i - 1]) {
                mp = mp +  prices[i] - prices[i - 1];
            }
        }
        return mp;
    }
}
