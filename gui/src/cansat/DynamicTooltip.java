package cansat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class DynamicTooltip extends Tooltip {
   public static List dynamicTooltips = new ArrayList();
   int openDelay;
   int visibleDuration;
   int closeDelay;
   String text;

   public DynamicTooltip(String text) {
      super(text);
      dynamicTooltips.add(this);
   }

   public void update(int index) {
      this.setText(String.valueOf(Gui.StatusButtonNames[index] + " " + DecodeData.Status[index]));
   }

   public static boolean setTooltipTimers(long openDelay, long visibleDuration, long closeDelay) {
      try {
         Field f = Tooltip.class.getDeclaredField("BEHAVIOR");
         f.setAccessible(true);
         Class[] classes = Tooltip.class.getDeclaredClasses();
         Class[] var11 = classes;
         int var10 = classes.length;

         for(int var9 = 0; var9 < var10; ++var9) {
            Class clazz = var11[var9];
            if (clazz.getName().equals("javafx.scene.control.Tooltip$TooltipBehavior")) {
               Constructor ctor = clazz.getDeclaredConstructor(Duration.class, Duration.class, Duration.class, Boolean.TYPE);
               ctor.setAccessible(true);
               Object tooltipBehavior = ctor.newInstance(new Duration((double)openDelay), new Duration((double)visibleDuration), new Duration((double)closeDelay), false);
               f.set((Object)null, tooltipBehavior);
               break;
            }
         }

         return true;
      } catch (Exception var14) {
         return false;
      }
   }
}
