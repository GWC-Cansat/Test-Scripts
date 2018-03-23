package cansat;

import java.util.ArrayList;
import java.util.List;

public class TempValue {
   public static List tempValues = new ArrayList();
   public float temp;
   public float xOffset;
   public float yOffset;
   public float size;

   public TempValue(float Temp, float XOffset, float YOffset, float Size) {
      this.temp = Temp;
      this.xOffset = XOffset;
      this.yOffset = YOffset;
      this.size = Size;
      tempValues.add(this);
   }
}
