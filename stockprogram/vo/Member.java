package test1.stock.vo;

import java.io.Serializable;

public class Member implements Serializable{
    private String id, name, addr;

    public Member() {
    }
        
    public Member(String id, String name, String addr) {
        setId(id);
        setName(name);
        setAddr(addr);      
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
    
    
}
