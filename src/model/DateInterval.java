package model;
import java.time.LocalDate;

public class DateInterval
{
  private LocalDate initialDate;
  private LocalDate finalDate;

  public DateInterval(LocalDate initialDate, LocalDate finalDate) {
    this.initialDate = initialDate;
    this.finalDate = finalDate;
  }

  public LocalDate getInitialDate() {
    return initialDate;
  }

  public LocalDate getFinalDate() {
    return finalDate;
  }

  public String toString()
  {
    return "initialDate: " + initialDate + ", finalDate:" + finalDate;
  }
}
