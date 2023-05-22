package model;
import java.sql.*;

public class PVPanels
{
  private Date date;
  private Time time;
  private int id;
  private int panel_id;
  private float voltage;
  private float current;
  private int solar_flux;
  private float power_out;
  private float efficiency;

  public PVPanels(Date date, Time time, int id, int panel_id, float voltage, float current, int solar_flux, float power_out, float efficiency){
    this.date = date;
    this.time = time;
    this.panel_id = panel_id;
    this.voltage = voltage;
    this.current = current;
    this.solar_flux = solar_flux;
    this.power_out = power_out;
    this.efficiency = efficiency;
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getPanel_id()
  {
    return panel_id;
  }

  public void setPanel_id(int panel_id)
  {
    this.panel_id = panel_id;
  }

  public float getVoltage()
  {
    return voltage;
  }

  public void setVoltage(float voltage)
  {
    this.voltage = voltage;
  }

  public float getCurrent()
  {
    return current;
  }

  public void setCurrent(float current)
  {
    this.current = current;
  }

  public int getSolar_flux()
  {
    return solar_flux;
  }

  public void setSolar_flux(int solar_flux)
  {
    this.solar_flux = solar_flux;
  }

  public float getPower_out()
  {
    return power_out;
  }

  public void setPower_out(float power_out)
  {
    this.power_out = power_out;
  }

  public float getEfficiency()
  {
    return efficiency;
  }

  public void setEfficiency(float efficiency)
  {
    this.efficiency = efficiency;
  }

  public Time getTime()
  {
    return time;
  }

  public void setTime(Time time)
  {
    this.time = time;
  }
}
