package com.example.tiku1.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-12 at 17:23 ：）
 */
public class SSJT implements Serializable {

    /**
     * ROWS_DETAIL : [{"id":2,"start":"06:20:00","end":"21:20:00","mileage":"19","price":"7","site":["左安门外站","靛厂新村","平乐园","宽街路口南","四方桥西","金家村桥东","三营门","四惠桥","祁家豁子","西安门","五间楼","北京华侨城","金台路","大观园公交场站","小营公交场站","四惠枢纽站","故宫"]}]
     * RESULT : S
     */

    private String RESULT;
    private List<ROWSDETAILBean> ROWS_DETAIL;

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public List<ROWSDETAILBean> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAILBean> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAILBean implements Serializable {
        /**
         * id : 2
         * start : 06:20:00
         * end : 21:20:00
         * mileage : 19
         * price : 7
         * site : ["左安门外站","靛厂新村","平乐园","宽街路口南","四方桥西","金家村桥东","三营门","四惠桥","祁家豁子","西安门","五间楼","北京华侨城","金台路","大观园公交场站","小营公交场站","四惠枢纽站","故宫"]
         */

        private int id;
        private String start;
        private String end;
        private String mileage;
        private String price;
        private List<String> site;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<String> getSite() {
            return site;
        }

        public void setSite(List<String> site) {
            this.site = site;
        }
    }
}
