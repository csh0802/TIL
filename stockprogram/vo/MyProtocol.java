
package test1.stock.vo;

import java.io.Serializable;

public class MyProtocol implements Serializable{ //포장이사 객체 위해 Serializable 상속
    public static final String signMsg[] = 
    {"INSERT_MEMBER","SELECT_MEMBER","UPDATE_MEMBER","DELETE_MEMBER"};
    private String sign;
    private StockSign sign2;        //enum값 받기위해 enum타입으로 선언
    private Object parameterObj;
    private Object resultObj;

    public MyProtocol() {
    }

    public MyProtocol(String sign, Object parameterObj, Object resultObj) {
        setSign(sign);
        setParameterObj(parameterObj);
        setResultObj(resultObj);
    }

    public MyProtocol(StockSign sign2, Object parameterObj, Object resultObj) {
        setSign2(sign2);
        setParameterObj(parameterObj);
        setResultObj(resultObj);
    }
    
    

    public StockSign getSign2() {
        return sign2;
    }

    public void setSign2(StockSign sign2) {
        this.sign2 = sign2;
    }
    
    
    
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) { //데이터 유효성 검사 필수!!
        this.sign = sign;
    }

    public Object getParameterObj() {
        return parameterObj;
    }

    public void setParameterObj(Object parameterObj) {
        this.parameterObj = parameterObj;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }
    
    
}
