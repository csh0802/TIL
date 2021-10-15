
package test1.stock.vo;

import java.io.Serializable;


public class Stock implements Serializable{
    private String code,symbol;
    private int price;

    public Stock() {
    }

    public Stock(String code, String symbol, int price) {
       setCode(code);
       setSymbol(symbol);
       setPrice(price);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
    
}
