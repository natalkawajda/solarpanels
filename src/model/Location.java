package model;

public class Location
{
  private int row;
  private int column;
  public Location(int row, int column){
    this.row = row;
    this.column = column;
  }

  public int getRow()
  {
    return row;
  }

  public int getColumn()
  {
    return column;
  }

  public void setRow(int row)
  {
    this.row = row;
  }

  public void setColumn(int column)
  {
    this.column = column;
  }
}
