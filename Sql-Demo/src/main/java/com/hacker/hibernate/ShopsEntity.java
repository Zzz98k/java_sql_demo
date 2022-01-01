package com.hacker.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "shops", schema = "demo", catalog = "")
public class ShopsEntity {
    private int cid;
    private String cname;
    private String cprice;
    private Integer tid;

    @Id
    @Column(name = "cid")
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "cname")
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Basic
    @Column(name = "cprice")
    public String getCprice() {
        return cprice;
    }

    public void setCprice(String cprice) {
        this.cprice = cprice;
    }

    @Basic
    @Column(name = "tid")
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopsEntity that = (ShopsEntity) o;

        if (cid != that.cid) return false;
        if (cname != null ? !cname.equals(that.cname) : that.cname != null) return false;
        if (cprice != null ? !cprice.equals(that.cprice) : that.cprice != null) return false;
        if (tid != null ? !tid.equals(that.tid) : that.tid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (cname != null ? cname.hashCode() : 0);
        result = 31 * result + (cprice != null ? cprice.hashCode() : 0);
        result = 31 * result + (tid != null ? tid.hashCode() : 0);
        return result;
    }
}
