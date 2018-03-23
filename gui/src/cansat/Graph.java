package cansat;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class Graph extends LineChart {
   public static List graphs = new ArrayList();
   public List maxValue = new ArrayList();
   public List minValue = new ArrayList();
   private String[] titles = new String[]{"CanSat Temperature", "Pressure", "Relative Humidity", "Total Acceleration", "X-Y", "X-Z", "Y-Z"};
   private String[][] axis = new String[][]{{"time (s)", "temperature (C)"}, {"time (s)", "pressure (hPa)"}, {"time (s)", "humidity (%)"}, {"time (s)", "ms^-2"}, {"X Pos (m)", "Y Pos (m)"}, {"X Pos (m)", "Z Pos (m)"}, {"Y Pos (m)", "Z Pos (m)"}};
   private String[] seriesNames = new String[]{"Average", "Center", "Bottom", "Front"};
   public boolean updateNow;
   Series series;
   public int numOfSeries;
   public int index;
   public float newXAxis;
   public float newYAxis;

   public Graph(NumberAxis xAxis, NumberAxis yAxis, int numOfSeries) {
      super(xAxis, yAxis);
      this.numOfSeries = numOfSeries;
      this.index = graphs.size();
      this.updateNow = true;
      this.setAnimated(false);
      this.setTitle(this.titles[this.index]);
      xAxis.setLabel(this.axis[this.index][0]);
      yAxis.setLabel(this.axis[this.index][1]);
      xAxis.setForceZeroInRange(false);
      yAxis.setForceZeroInRange(false);

      for(int i = 0; i < this.numOfSeries; ++i) {
         this.series = new Series();
         if (this.numOfSeries > 1) {
            this.series.setName(this.seriesNames[i]);
         } else {
            this.series.setName("series");
         }

         this.getData().add(this.series);
      }

      if (this.numOfSeries > 1) {
         this.setLegendVisible(true);
      } else {
         this.setLegendVisible(false);
      }

      graphs.add(this);
   }

   public void update() {
      if (this.updateNow) {
         for(int i = 0; i < this.numOfSeries; ++i) {
            this.newXAxis = DecodeData.getAxis(this.index, 0, i);
            this.newYAxis = DecodeData.getAxis(this.index, 1, i);
            Data newData = new Data(this.newXAxis, this.newYAxis);
            this.series = (Series)this.getData().get(i);
            this.series.getData().add(newData);
         }
      }

   }

   public void clear() {
      for(int i = 0; i < this.numOfSeries; ++i) {
         this.series = (Series)this.getData().get(i);
         this.series.getData().clear();
      }

   }
}
