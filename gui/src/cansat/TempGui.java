package cansat;

import java.awt.image.BufferedImage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TempGui extends Canvas {
   private static GraphicsContext gc;
   private static int width;
   private static int height;
   private PixelReader pixelReader;

   public TempGui(int width, int height, BufferedImage image) {
      super((double)width, (double)height);
      width = width;
      height = height;
      gc = this.getGraphicsContext2D();
      this.clear();
      int maxSize = TempColor.tempColors.size();

      for(int i = 0; i < maxSize; ++i) {
         gc.setFill(((TempColor)TempColor.tempColors.get(maxSize - i - 1)).color);
         gc.fillRect(0.0D, (double)(height / maxSize * i + 10), 20.0D, (double)(height / 25));
      }

      gc.setFill(Color.WHITE);
      gc.fillRect(20.0D, 10.0D, 2.0D, 310.0D);
      int[] nums = new int[]{45, 35, 25, 15, 5, -4};

      for(int i = 0; i < 6; ++i) {
         gc.fillRect(22.0D, (double)(i * 59 + 10), 10.0D, 2.0D);
         gc.setFont(Font.font("Serif", 12.0D));
         gc.setFill(Color.WHITE);
         gc.fillText(nums[i] + "C", 34.0D, (double)(i * 59 + 15));
      }

   }

   public void clear() {
      gc.setFill(Color.TRANSPARENT);
      gc.fillRect(0.0D, 0.0D, (double)width, (double)height);
   }
}
