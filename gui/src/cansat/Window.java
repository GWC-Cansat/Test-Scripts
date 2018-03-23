package cansat;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window {
   private int width = 1280;
   private int height = 720;
   private String title = "GWC CanSat Basestation";
   private Scene scene;

   public Window(Stage window) {
      window.setMinWidth(900.0D);
      window.setMinHeight(700.0D);
      window.setFullScreen(true);
      new Gui(this.width, this.height);
      this.scene = Gui.getGui();

      try {
         this.scene.getStylesheets().add("assets/style.css");
      } catch (IndexOutOfBoundsException var4) {
         System.err.println(var4.getMessage());
      }

      window.setScene(this.scene);
      window.titleProperty().bind(window.widthProperty().asString().concat(" : ").concat(window.heightProperty().asString()).concat(" " + this.title));
      window.show();
   }
}
