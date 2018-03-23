package cansat;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.StackPane;

public class Console extends TextArea {
   private StackPane pane = Gui.getPane();

   public Console() {
      this.getStyleClass().add("console");
      this.prefHeightProperty().bind(this.pane.heightProperty());
      this.setEditable(false);
      this.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
   }

   public void update() {
      this.clear();

      for(int i = 0; i < Gui.StatusButtonNames.length; ++i) {
         this.appendText(String.valueOf(Gui.StatusButtonNames[i] + ": " + DecodeData.Status[i] + Utils.newLine));
      }

   }
}
