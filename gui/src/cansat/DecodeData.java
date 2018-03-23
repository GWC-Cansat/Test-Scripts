package cansat;

import java.util.ArrayList;

public class DecodeData {
   public static String[] Status;
   private String[][] axis = new String[][]{{"time", "temperature"}, {"time", "pressure"}, {"time", "humidity"}, {"time", "acceleration magnitude"}, {"x", "y"}, {"x", "z"}, {"y", "z"}};
   public static Float[][][] axisValues = new Float[7][2][4];
   private static float tempAverageTime;
   private static float tempImuTime;
   private static float tempD6tTime;
   private static float tempEnvTime;
   private static float tempAverage;
   private static float tempImu;
   private static float tempD6t;
   private static float tempEnv;
   private static float pressureTime;
   public static float pressure;
   private static float humidityTime;
   public static float humidity;
   public static float accelTime;
   public static float accel;
   public static float x;
   public static float y;
   public static float z;
   public static float time;
   public static int temp;
   public static float tempX;
   public static float tempY;
   public static float size;
   public static double lat;
   public static double lon;
   private int tick;
   private static String newFile;
   private static String oldFile;
   private String[] textSplit;
   public static int numOfLines;
   public static int lastNumOfLines;
   public static int numOfLinesRead;
   private int startIndex;
   private int endIndex;
   private int numOfVars;
   private static ArrayList fileSplit = new ArrayList();

   public DecodeData(String path) {
      newFile = Utils.readFile(path);
      if (newFile.indexOf(59) == -1) {
         MainLoop.skip = true;
      } else {
         String[] numOf = newFile.split(";");
         numOfLines = numOf.length - 1;
         System.out.println("numOfLines " + numOfLines + " lastNumOfLines " + lastNumOfLines + " numOfLinesRead " + numOfLinesRead + Utils.newLine);
         if (numOfLinesRead < numOfLines) {
            if (lastNumOfLines < numOfLines) {
               for(int i = lastNumOfLines; i < numOfLines; ++i) {
                  this.startIndex = newFile.indexOf(58);
                  this.endIndex = newFile.indexOf(59);
                  fileSplit.add(newFile.substring(this.startIndex + 1, this.endIndex));
                  newFile = newFile.substring(0, this.startIndex) + " " + newFile.substring(this.endIndex + 1, newFile.length());
               }
            }

            this.textSplit = ((String)fileSplit.get(numOfLinesRead)).split(",");
            ++numOfLinesRead;
            lastNumOfLines = numOfLines;
            this.loadData();
         } else {
            MainLoop.skip = true;
         }
      }

   }

   public void loadData() {
      tempImu = Float.parseFloat(this.textSplit[0]);
      tempImuTime = Float.parseFloat(this.textSplit[1]);
      tempD6t = Float.parseFloat(this.textSplit[2]);
      tempD6tTime = Float.parseFloat(this.textSplit[3]);
      System.out.println(tempEnv + " " + tempEnvTime);
      tempEnv = Float.parseFloat(this.textSplit[4]);
      tempEnvTime = Float.parseFloat(this.textSplit[5]);
      tempAverage = (tempD6t + tempImu + tempEnv) / 3.0F;
      tempAverageTime = tempEnvTime;
      pressure = Float.parseFloat(this.textSplit[6]);
      pressureTime = Float.parseFloat(this.textSplit[7]);
      humidity = Float.parseFloat(this.textSplit[8]);
      humidityTime = Float.parseFloat(this.textSplit[9]);
      accel = Float.parseFloat(this.textSplit[10]);
      accelTime = Float.parseFloat(this.textSplit[11]);
      Status = new String[]{Utils.intToBoolean(Float.parseFloat(this.textSplit[12])), Utils.intToBoolean(Float.parseFloat(this.textSplit[13])), this.textSplit[14] + "%", Utils.intToBoolean(Float.parseFloat(this.textSplit[15])), Utils.intToBoolean(Float.parseFloat(this.textSplit[16]))};
      ((StatusButton)StatusButton.statusButtons.get(0)).toggle((int)Float.parseFloat(this.textSplit[12]));
      ((StatusButton)StatusButton.statusButtons.get(1)).toggle((int)Float.parseFloat(this.textSplit[13]));
      ((StatusButton)StatusButton.statusButtons.get(2)).toggle((int)Float.parseFloat("1.0"));
      ((StatusButton)StatusButton.statusButtons.get(3)).toggle((int)Float.parseFloat(this.textSplit[15]));
      ((StatusButton)StatusButton.statusButtons.get(4)).toggle((int)Float.parseFloat(this.textSplit[16]));
      x = Float.parseFloat(this.textSplit[17]);
      y = Float.parseFloat(this.textSplit[18]);
      z = Float.parseFloat(this.textSplit[19]);
      lon = (double)Float.parseFloat(this.textSplit[20]);
      lat = (double)Float.parseFloat(this.textSplit[21]);

      for(int i = 0; i < 16; ++i) {
         temp = (int)Float.parseFloat(this.textSplit[i * 4 + 22]);
         tempX = Float.parseFloat(this.textSplit[i * 4 + 23]);
         tempY = Float.parseFloat(this.textSplit[i * 4 + 24]);
         size = Float.parseFloat(this.textSplit[i * 4 + 25]);
         new TempValue((float)temp, tempX, tempY, size);
      }

      axisValues[0][0][0] = tempAverageTime;
      axisValues[0][0][1] = tempImuTime;
      axisValues[0][0][2] = tempD6tTime;
      axisValues[0][0][3] = tempEnvTime;
      axisValues[0][1][0] = tempAverage;
      axisValues[0][1][1] = tempImu;
      axisValues[0][1][2] = tempD6t;
      axisValues[0][1][3] = tempEnv;
      axisValues[1][0][0] = pressureTime;
      axisValues[1][1][0] = pressure;
      axisValues[2][0][0] = humidityTime;
      axisValues[2][1][0] = humidity;
      axisValues[3][0][0] = accelTime;
      axisValues[3][1][0] = accel;
      axisValues[4][0][0] = x;
      axisValues[4][1][0] = y;
      axisValues[5][0][0] = x;
      axisValues[5][1][0] = z;
      axisValues[6][0][0] = y;
      axisValues[6][1][0] = z;
   }

   public static float getAxis(int graphIndex, int axisIndex, int seriesIndex) {
      return axisValues[graphIndex][axisIndex][seriesIndex];
   }

   public static int getNnumOfSeries(int index) {
      return axisValues[index][0][2] != null ? 4 : 1;
   }
}
