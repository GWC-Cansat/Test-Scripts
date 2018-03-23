package cansat;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javafx.scene.paint.Color;

public class Utils {
   public static String newLine = "\n";

   public static String intToBoolean(float integer) {
      return (int)integer == 1 ? "True" : "false";
   }

   public static void clearAllGraphs(List graphs) {
      for(int i = 0; i < graphs.size(); ++i) {
         ((Graph)graphs.get(i)).clear();
      }

   }

   public static Color getPixelColor(BufferedImage image, int x, int y) {
      int clr = image.getRGB(x, y);
      int red = (clr & 16711680) >> 16;
      int green = (clr & '\uff00') >> 8;
      int blue = clr & 255;
      Color color = Color.rgb(red, green, blue);
      return color;
   }
   
   public static String readFile(final String file) {
       try {
           Throwable t = null;
           try {
               final BufferedReader br = new BufferedReader(new FileReader(file));
               try {
                   final StringBuilder sb = new StringBuilder();
                   String line = null;
                   try {
                       line = br.readLine();
                   }
                   catch (IOException e) {
                       e.printStackTrace();
                   }
                   while (line != null) {
                       sb.append(line);
                       sb.append(System.lineSeparator());
                       line = br.readLine();
                   }
                   final String everything = sb.toString();
                   return everything;
               }
               finally {
                   if (br != null) {
                       br.close();
                   }
               }
           }
           finally {
               if (t == null) {
                   final Throwable t2 = null;
                   t = t2;
               }
               else {
                   final Throwable t2 = null;
                   if (t != t2) {
                       t.addSuppressed(t2);
                   }
               }
           }
       }
       catch (IOException e2) {
           e2.printStackTrace();
           return null;
       }
   }

   public static void crateTempRange(BufferedImage image) {
      boolean imgI = false;
      int startNum = -4;

      for(int i = 0; i < 50; ++i) {
         int imgI1 = i;
         if (i > 0) {
            imgI1 = i * 2;
         }

         new TempColor(i + startNum, getPixelColor(image, imgI1, 0));
      }

   }

   public static Color getColorByTemp(int temp) {
      for(int i = 0; i < TempColor.tempColors.size(); ++i) {
         if (temp == ((TempColor)TempColor.tempColors.get(i)).temp) {
            return ((TempColor)TempColor.tempColors.get(i)).color;
         }
      }

      if (temp < -4) {
         return ((TempColor)TempColor.tempColors.get(0)).color;
      } else {
         return temp > 45 ? ((TempColor)TempColor.tempColors.get(49)).color : null;
      }
   }
}
