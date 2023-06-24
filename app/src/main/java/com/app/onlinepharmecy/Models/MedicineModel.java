package com.app.onlinepharmecy.Models;

public class MedicineModel {

    String m_name,dose,price;
    int image;
    String m_des;

    public MedicineModel(String m_name, String dose, String price, int image, String m_des) {

        this.m_name = m_name;
        this.dose = dose;
        this.price = price;
        this.image = image;
        this.m_des = m_des;
    }

    public MedicineModel() {
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getM_des() {
        return m_des;
    }

    public void setM_des(String m_des) {
        this.m_des = m_des;
    }
}
