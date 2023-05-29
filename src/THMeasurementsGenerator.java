import model.PVPanels;
import model.THPanels;
import view.DatabaseConnector;
import java.util.Date;
public class THMeasurementsGenerator extends Thread
{
  private DatabaseConnector connection;
  private final int waitTime = 1000;
  private int id;
  private final double[] a_temperatures = {9.1, 9.2, 9.3, 9.4, 9.5, 9.6, 9.7, 9.8, 9.9};
  private final double[] water_in_temps = {12.1, 12.2, 12.3, 12.4, 12.5,12.6, 12.7, 12.8, 12.9};
  private final double[] water_out_temps = {11.1, 11.2, 11.3, 11.4, 11.5, 11.6, 11.7, 11.8, 11.9};
  private final double[] efficiencies = {10, 10.1, 10.2, 10.3, 10.4, 10.5, 10.6, 10.7, 10.8, 10.9};
  public THMeasurementsGenerator(DatabaseConnector connection){
    this.connection = connection;
  }
  public void run(){
    while (true)
    {
      Date date = new Date();
      for (int j = 93; j < 101; j++)
      {
        id = j;
        double a_temperature = a_temperatures[(int) Math.floor(Math.random() * a_temperatures.length)];
        double water_in_temp = water_in_temps[(int) Math.floor(Math.random() * water_in_temps.length)];
        double water_out_temp = water_out_temps[(int) Math.floor(Math.random() * water_out_temps.length)];
        double efficiency = efficiencies[(int) Math.floor(Math.random() * efficiencies.length)];
        THPanels thPanels = new THPanels(date, id, a_temperature, water_in_temp,
            water_out_temp, efficiency);
        connection.generateTHMeasurements(thPanels);
      }
      try
      {
        Thread.sleep(waitTime);
      }
      catch (InterruptedException e)
      {
        System.out.println("Thread sleeping interrupted");
        e.printStackTrace();
      }

    }
  }
}
