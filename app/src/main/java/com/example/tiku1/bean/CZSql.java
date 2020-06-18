package com.example.tiku1.bean;

import org.litepal.crud.LitePalSupport;

/**
 * Create by 张瀛煜 on 2020-06-04 ：）
 */
public class CZSql extends LitePalSupport {
    private int id;
    private String Cp,Je,Name,Sj;

    public CZSql(String cp, String je, String name, String sj) {
        Cp = cp;
        Je = je;
        Name = name;
        Sj = sj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCp() {
        return Cp;
    }

    public void setCp(String cp) {
        Cp = cp;
    }

    public String getJe() {
        return Je;
    }

    public void setJe(String je) {
        Je = je;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSj() {
        return Sj;
    }

    public void setSj(String sj) {
        Sj = sj;
    }
}
