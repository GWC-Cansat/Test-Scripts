package cansat;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;

public class StatusButton extends Button {
   public static List statusButtons = new ArrayList();
   public int status;

   public StatusButton(int Status) {
      this.status = Status;
      if (this.status == 1) {
         this.setStyle("-fx-background-color: green");
      } else {
         this.setStyle("-fx-background-color: red");
      }

      statusButtons.add(this);
   }

   public void toggle(int Status) {
      this.status = Status;
      if (this.status == 1) {
         this.setStyle("-fx-background-color: green");
      } else {
         this.setStyle("-fx-background-color: red");
      }

   }

   public int getStatus() {
      return this.status;
   }
}
