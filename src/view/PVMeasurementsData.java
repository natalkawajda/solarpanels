package view;
import model.PVPanels;

public class PVMeasurementsData
{
  private DatabaseConnector connection;
  private final String[] timestamps = {"2023-05-01 12:05:30", "2023-05-01 13:05:30", "2023-05-01 14:05:30", "2023-05-01 15:05:30", "2023-05-02 12:05:30", "2023-05-02 13:05:30", "2023-05-02 14:05:30", "2023-05-02 15:05:30",
      "2023-05-03 12:05:30", "2023-05-03 13:05:30", "2023-05-03 14:05:30", "2023-05-03 15:05:30", "2023-05-04 12:05:30", "2023-05-04 13:05:30", "2023-05-04 14:05:30", "2023-05-04 15:05:30",
      "2023-05-05 12:05:30", "2023-05-05 13:05:30", "2023-05-05 14:05:30", "2023-05-05 15:05:30", "2023-05-06 12:05:30", "2023-05-06 13:05:30", "2023-05-06 14:05:30", "2023-05-06 15:05:30",
      "2023-05-07 12:05:30", "2023-05-07 13:05:30", "2023-05-07 14:05:30", "2023-05-07 15:05:30", "2023-05-08 12:05:30", "2023-05-08 13:05:30", "2023-05-08 14:05:30", "2023-05-08 15:05:30",
      "2023-05-09 12:05:30", "2023-05-09 13:05:30", "2023-05-09 14:05:30", "2023-05-09 15:05:30", "2023-05-10 12:05:30", "2023-05-10 13:05:30", "2023-05-10 14:05:30", "2023-05-10 15:05:30",
      "2023-05-11 12:05:30", "2023-05-11 13:05:30", "2023-05-11 14:05:30", "2023-05-11 15:05:30", "2023-05-12 12:05:30", "2023-05-12 13:05:30", "2023-05-12 14:05:30", "2023-05-12 15:05:30",
      "2023-05-13 12:05:30", "2023-05-13 13:05:30", "2023-05-13 14:05:30", "2023-05-13 15:05:30", "2023-05-14 12:05:30", "2023-05-14 13:05:30", "2023-05-14 14:05:30", "2023-05-14 15:05:30",
      "2023-05-15 12:05:30", "2023-05-15 13:05:30", "2023-05-15 14:05:30", "2023-05-15 15:05:30", "2023-05-16 12:05:30", "2023-05-16 13:05:30", "2023-05-16 14:05:30", "2023-05-16 15:05:30",
      "2023-05-17 12:05:30", "2023-05-17 13:05:30", "2023-05-17 14:05:30", "2023-05-17 15:05:30", "2023-05-18 12:05:30", "2023-05-18 13:05:30", "2023-05-18 14:05:30", "2023-05-18 15:05:30",
      "2023-05-19 12:05:30", "2023-05-19 13:05:30", "2023-05-19 14:05:30", "2023-05-19 15:05:30", "2023-05-20 12:05:30", "2023-05-20 13:05:30", "2023-05-20 14:05:30", "2023-05-20 15:05:30",
      "2023-05-21 12:05:30", "2023-05-21 13:05:30", "2023-05-21 14:05:30", "2023-05-21 15:05:30", "2023-05-22 12:05:30", "2023-05-22 13:05:30", "2023-05-22 14:05:30", "2023-05-22 15:05:30",
      "2023-05-23 12:05:30", "2023-05-23 13:05:30", "2023-05-23 14:05:30", "2023-05-23 15:05:30", "2023-05-24 12:05:30", "2023-05-24 13:05:30", "2023-05-24 14:05:30", "2023-05-24 15:05:30",
      "2023-05-25 12:05:30", "2023-05-25 13:05:30", "2023-05-25 14:05:30", "2023-05-25 15:05:30", "2023-05-26 12:05:30", "2023-05-26 13:05:30", "2023-05-26 14:05:30", "2023-05-26 15:05:30",
      "2023-05-27 12:05:30", "2023-05-27 13:05:30", "2023-05-27 14:05:30", "2023-05-27 15:05:30", "2023-05-28 12:05:30", "2023-05-28 13:05:30", "2023-05-28 14:05:30", "2023-05-28 15:05:30",
      "2023-05-29 12:05:30", "2023-05-29 13:05:30", "2023-05-29 14:05:30", "2023-05-29 15:05:30", "2023-05-30 12:05:30", "2023-05-30 13:05:30", "2023-05-30 14:05:30", "2023-05-30 15:05:30",
      "2023-05-31 12:05:30", "2023-05-31 13:05:30", "2023-05-31 14:05:30", "2023-05-31 15:05:30", "2023-06-01 12:05:30", "2023-06-01 13:05:30", "2023-06-01 14:05:30", "2023-06-01 15:05:30"};
  private int id;
  private final double[] voltages = {11.1, 11.2, 11.3, 11.4, 11.5, 11.6, 11.7, 11.8, 11.9, 12.0, 12.1, 12.2, 12.3, 12.4, 12.5, 12.6, 12.7, 12.8, 12.9};
  private final double[] currents = {0.25, 0.26, 0.27, 0.28, 0.29, 0.30, 0.31, 0.32, 0.33, 0.34, 0.35, 0.36, 0.37, 0.38, 0.39, 0.40, 0.41, 0.42, 0.43, 0.44,
      0.45, 0.46, 0.47, 0.48, 0.49, 0.50, 0.51, 0.52, 0.53, 0.54, 0.55, 0.56, 0.57, 0.58, 0.59, 0.60, 0.61, 0.62, 0.63, 0.64};
  private final int[] solar_fluxes = {1021, 1022, 1023, 1024, 1025, 1026, 1027, 1028, 1029, 1030, 1031, 1032, 1033, 1034, 1035, 1036, 1037, 1038, 1039, 1040,
      1041, 1042, 1043, 1044, 1045, 1046, 1047, 1048, 1049, 1050, 1051, 1052, 1053, 1054, 1055, 1056, 1057, 1058, 1059, 1060,
      1061, 1062, 1063, 1064, 1065, 1066, 1067, 1068, 1069, 1070, 1071, 1072, 1073, 1074, 1075, 1076, 1077, 1078, 1079, 1080};
  private double power_out;
  private final double[] power_ins = {77.5, 77.52, 77.54, 77.56, 77.58, 77.6, 77.62, 77.64, 77.66, 77.68, 77.7, 77.72, 77.74, 77.76, 77.78, 77.8, 77.82, 77.84, 77.86, 77.88, 77.9,
      77.92, 77.94, 77.96, 77.98, 78, 78.02, 78.04, 78.06, 78.08, 78.1, 78.12, 78.14, 78.16, 78.18, 78.2, 78.22, 78.24, 78.26, 78.28, 78.3,
      78.32, 78.34, 78.36, 78.38, 78.4, 78.42, 78.44, 78.46, 78.48, 78.5, 78.52, 78.54, 78.56, 78.58, 78.6};
  public PVMeasurementsData(DatabaseConnector connection){
    this.connection = connection;
  }
  public void generateData(){
    for (int i = 0; i < timestamps.length; i++){
      String timestamp = timestamps[i];
      for (int j = 1; j < 93; j++)
      {
        id = j;
        double voltage = voltages[(int) Math.floor(Math.random() * voltages.length)];
        double current = currents[(int) Math.floor(Math.random() * currents.length)];
        int solar_flux = solar_fluxes[(int) Math.floor(
            Math.random() * solar_fluxes.length)];
        double p_out = voltage * current;
        double efficiency = p_out / power_ins[(int) Math.floor(
            Math.random() * power_ins.length)];
        PVPanels pvPanels = new PVPanels(timestamp, id, voltage, current,
            solar_flux, p_out, efficiency);
        connection.storePVMeasurements(pvPanels);

      }
    }
  }
}