package cansat;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class TempColor {
   public static List tempColors = new ArrayList();
   public int temp;
   public Color color;

   public TempColor(int Temp, Color Color) {
      this.temp = Temp;
      this.color = Color;
      tempColors.add(this);
   }
}
