
package test1.stock.vo;

import java.io.Serializable;


public class Portfolio implements Serializable{
    private String id;
    private String symbol;
    private int quantity;

    public Portfolio(String id, String symbol, int quantity) {
        setId(id);
        setSymbol(symbol);
        setQuantity(quantity);
    }

    public Portfolio() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity>0){
            this.quantity = quantity;
        }else{
            System.out.println("0보다 커야 합니다.");
        }
    }
    
    
}
