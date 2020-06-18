package com.example.tiku1.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Create by 张瀛煜 on 2020-06-05 ：）
 */
public class WZBH implements Serializable {


    /**
     * id : [4,8,9]
     * RESULT : S
     */

    private String RESULT;
    private List<Integer> id;

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }
}
