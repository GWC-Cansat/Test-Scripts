package cansat;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainLoop extends Application {
   public static Timer timer;
   private Scene scene;
   private Window window;
   private DecodeData decodeData;
   private static int tick;
   public static boolean skip;

   public void start(Stage stage) {
      tick = 0;
      this.window = new Window(stage);
      this.scene = Gui.getGui();
      TempGrid.nextScale = 1.0F;
      timer = new Timer();
      timer.scheduleAtFixedRate(new TimerTask() {
         public void run() {
            Platform.runLater(() -> {
               MainLoop.skip = false;
               String path = Gui.dataPath.getText();

               try {
                  MainLoop.this.decodeData = new DecodeData(path);
               } catch (IndexOutOfBoundsException var9) {
                  System.err.println(var9.getMessage());
               }

               try {
                  TempGrid.update();
               } catch (IndexOutOfBoundsException var8) {
                  System.err.println(var8.getMessage());
               }

               if (!MainLoop.skip) {
                  int i;
                  for(i = 0; i < Graph.graphs.size(); ++i) {
                     try {
                        ((Graph)Graph.graphs.get(i)).update();
                     } catch (IndexOutOfBoundsException var7) {
                        System.err.println(var7.getMessage());
                     }
                  }

                  try {
                     Map.update();
                  } catch (IndexOutOfBoundsException var6) {
                     System.err.println(var6.getMessage());
                  }

                  try {
                     Console console = Gui.getConsole();
                     console.update();
                  } catch (IndexOutOfBoundsException var5) {
                     System.err.println(var5.getMessage());
                  }

                  for(i = 0; i < DynamicTooltip.dynamicTooltips.size(); ++i) {
                     try {
                        ((DynamicTooltip)DynamicTooltip.dynamicTooltips.get(i)).update(i);
                     } catch (IndexOutOfBoundsException var4) {
                        ;
                     }
                  }
               }

               MainLoop.tick = MainLoop.tick + 1;
            });
         }
      }, 100L, 50L);
   }

   public static int getTick() {
      return tick;
   }
}
