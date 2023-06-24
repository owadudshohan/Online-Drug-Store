package com.app.onlinepharmecy.Models;

public class CartData {

    String md_name,md_dose,md_amnt;
    int md_price;

    public CartData(String md_name, String md_dose, String md_amnt, int md_price) {
        this.md_name = md_name;
        this.md_dose = md_dose;
        this.md_amnt = md_amnt;
        this.md_price = md_price;
    }


    public String getMd_name() {
        return md_name;
    }

    public void setMd_name(String md_name) {
        this.md_name = md_name;
    }

    public String getMd_dose() {
        return md_dose;
    }

    public void setMd_dose(String md_dose) {
        this.md_dose = md_dose;
    }

    public String getMd_amnt() {
        return md_amnt;
    }

    public void setMd_amnt(String md_amnt) {
        this.md_amnt = md_amnt;
    }

    public int getMd_price() {
        return md_price;
    }

    public void setMd_price(int md_price) {
        this.md_price = md_price;
    }
}
