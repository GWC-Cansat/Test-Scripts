package cansat;

import java.awt.image.BufferedImage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TempGrid extends Canvas {
   private static GraphicsContext gc;
   private static int width;
   private static int height;
   static float center;
   static float nextScale;
   static BufferedImage image;
   private static float temp;
   private static float xOffset;
   private static float yOffset;
   private static float size;
   private static float newSize;
   private static float newScale;
   private static float newScaleX;
   private static float newScaleY;
   private static float fullHeight;
   private static float fullWidth;
   private static float nNewScaleX;
   private static float nNewScaleY;
   private static float nFullHeight;
   private static float nFullWidth;

   public TempGrid(int width, int height, BufferedImage image) {
      super((double)width, (double)height);
      width = width;
      height = height;
      image = image;
      gc = this.getGraphicsContext2D();
      clear();
   }

   public static void clear() {
      gc.setFill(Color.WHITE);
      gc.fillRect(0.0D, 0.0D, (double)width, (double)height);
   }

   public static void update() {
      center = (float)(width / 2);
      clear();

      for(int i = 0; i < TempValue.tempValues.size(); ++i) {
         temp = ((TempValue)TempValue.tempValues.get(i)).temp;
         xOffset = ((TempValue)TempValue.tempValues.get(i)).xOffset * nextScale;
         yOffset = ((TempValue)TempValue.tempValues.get(i)).yOffset * nextScale;
         size = ((TempValue)TempValue.tempValues.get(i)).size * nextScale;
         gc.setFill(Utils.getColorByTemp((int)temp));
         gc.fillRect((double)(center + xOffset), (double)(center + yOffset), (double)size, (double)size);
      }

   }

   public static GraphicsContext getGc() {
      return gc;
   }
}
