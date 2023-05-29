package model;

import java.sql.*;

import java.util.Date;

public class THPanels {
  private String timestamp;
  private Date date;
  private Time time;
  private int id;
  private int panel_id;
  private double a_temperature;
  private double water_in_temp;
  private double water_out_temp;
  private double efficiency;

  public THPanels(Date date, Time time, int id, int panel_id, double a_temperature, double water_in_temp, double water_out_temp, double efficiency){
    this.date = date;
    this.time = time;
    this.id = id;
    this.panel_id = panel_id;
    this.a_temperature = a_temperature;
    this.water_in_temp = water_in_temp;
    this.water_out_temp = water_out_temp;
    this.efficiency = efficiency;
  }
  public THPanels(Date date, Time time, int panel_id, double a_temperature, double water_in_temp, double water_out_temp, double efficiency){
    this.date = date;
    this.time = time;
    this.panel_id = panel_id;
    this.a_temperature = a_temperature;
    this.water_in_temp = water_in_temp;
    this.water_out_temp = water_out_temp;
    this.efficiency = efficiency;
  }
  public THPanels(String timestamp, int panel_id, double a_temperature, double water_in_temp, double water_out_temp, double efficiency){
    this.timestamp = timestamp;
    this.panel_id = panel_id;
    this.a_temperature = a_temperature;
    this.water_in_temp = water_in_temp;
    this.water_out_temp = water_out_temp;
    this.efficiency = efficiency;
  }
  public THPanels(Date date, int panel_id, double a_temperature, double water_in_temp, double water_out_temp, double efficiency){
    this.date = date;
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

  public double getA_temperature() {
    return a_temperature;
  }

  public void setA_temperature(float a_temperature) {
    this.a_temperature = a_temperature;
  }

  public double getWater_in_temp() {
    return water_in_temp;
  }

  public void setWater_in_temp(float water_in_temp) {
    this.water_in_temp = water_in_temp;
  }

  public double getWater_out_temp() {
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

  public double getEfficiency()
  {
    return efficiency;
  }

  public void setEfficiency(float efficiency)
  {
    this.efficiency = efficiency;
  }

  public String getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(String timestamp)
  {
    this.timestamp = timestamp;
  }
}
