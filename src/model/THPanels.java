package model;

import java.sql.*;

public class THPanels {
  private Date date;
  private Time time;
  private int id;
  private int panel_id;
  private float a_temperature;
  private float water_in_temp;
  private float water_out_temp;
  private float efficiency;

  public THPanels(Date date, Time time, int id, int panel_id, float a_temperature, float water_in_temp, float water_out_temp, float efficiency){
    this.date = date;
    this.time = time;
    this.id = id;
    this.panel_id = panel_id;
    this.a_temperature = a_temperature;
    this.water_in_temp = water_in_temp;
    this.water_out_temp = water_out_temp;
    this.efficiency = efficiency;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPanel_id() {
    return panel_id;
  }

  public void setPanel_id(int panel_id) {
    this.panel_id = panel_id;
  }

  public float getA_temperature() {
    return a_temperature;
  }

  public void setA_temperature(float a_temperature) {
    this.a_temperature = a_temperature;
  }

  public float getWater_in_temp() {
    return water_in_temp;
  }

  public void setWater_in_temp(float water_in_temp) {
    this.water_in_temp = water_in_temp;
  }

  public float getWater_out_temp() {
    return water_out_temp;
  }

  public void setWater_out_temp(float water_out_temp) {
    this.water_out_temp = water_out_temp;
  }

  public Time getTime() {
    return time;
  }

  public void setTime(Time time) {
    this.time = time;
  }

  public float getEfficiency()
  {
    return efficiency;
  }

  public void setEfficiency(float efficiency)
  {
    this.efficiency = efficiency;
  }
}
