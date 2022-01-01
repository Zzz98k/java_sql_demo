package com.hacker.mybatis;

public class shops {
    private Integer cid;

    private String cname;

    private String cprice;

    private Integer tid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getCprice() {
        return cprice;
    }

    public void setCprice(String cprice) {
        this.cprice = cprice == null ? null : cprice.trim();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}