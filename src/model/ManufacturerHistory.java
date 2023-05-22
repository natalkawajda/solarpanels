package model;
import java.sql.*;

public class ManufacturerHistory
{
  private int id;
  private Date date;
  private Time time;
  private int manufacturer_id;
  private String name;
  private String country;
  private String phone;
  private String email;

  public ManufacturerHistory(int id, Date date, Time time, int manufacturer_id, String name, String country, String phone, String email){
    this.id = id;
    this.date = date;
    this.time = time;
    this.manufacturer_id = manufacturer_id;
    this.name = name;
    this.country = country;
    this.phone = phone;
    this.email = email;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public Time getTime()
  {
    return time;
  }

  public void setTime(Time time)
  {
    this.time = time;
  }

  public int getManufacturer_id()
  {
    return manufacturer_id;
  }

  public void setManufacturer_id(int manufacturer_id)
  {
    this.manufacturer_id = manufacturer_id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getCountry()
  {
    return country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getPhone()
  {
    return phone;
  }

  public void setPhone(String phone)
  {
    this.phone = phone;
  }
}
