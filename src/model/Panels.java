package model;

import java.sql.*;

public class Panels {
  private int id;
  private String manufacturer;
  private String type;
  private int row;
  private int column;
  private Date installation_date;

  public Panels(int id, String manufacturer, String type, int row, int column, Date installation_date){
    this.id = id;
    this.installation_date = installation_date;
    this.manufacturer = manufacturer;
    this.type = type;
    this.row = row;
    this.column = column;
  }
  public Panels(int row, int column){
    this.row = row;
    this.column = column;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getInstallation_date() {
    return installation_date;
  }

  public void setInstallation_date(Date installation_date) {
    this.installation_date = installation_date;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }
}
