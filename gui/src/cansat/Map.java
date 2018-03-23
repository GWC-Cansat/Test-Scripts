package cansat;

import java.net.URL;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Map extends StackPane {
   WebView map;
   static WebEngine webEngine;
   static boolean loaded;

   public Map() {
      loaded = false;
      this.prefWidthProperty().bind(Gui.getPane().widthProperty());
      this.prefHeightProperty().bind(Gui.getPane().heightProperty());
      this.map = new WebView();
      this.map.setContextMenuEnabled(false);
      this.map.prefHeightProperty().bind(Gui.getPane().heightProperty());
      webEngine = this.map.getEngine();
      URL mapUrl = this.getClass().getResource("map.html");
      webEngine.load(mapUrl.toExternalForm());
      final String CSS = Utils.readFile("assets/mapStyles.css");
      webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener() {
         public void changed(ObservableValue ov, State oldState, State newState) {
            if (newState == State.SUCCEEDED) {
               Document doc = Map.webEngine.getDocument();
               Element styleNode = doc.createElement("style");
               Text styleContent = doc.createTextNode(CSS);
               styleNode.appendChild(styleContent);
               doc.getDocumentElement().getElementsByTagName("head").item(0).appendChild(styleNode);
               Map.loaded = true;
            }

         }

		@Override
		public void changed(ObservableValue arg0, Object arg1, Object arg2) {
			// TODO Auto-generated method stub
			
		}
      });
      this.getChildren().add(this.map);
   }

   public static void clear() {
   }

   public static void update() {
      if (loaded) {
         double lat = DecodeData.lat;
         double lon = DecodeData.lon;
         if ((int)lat != -1 || (int)lat != 0 || (int)lon != -1 || (int)lon != 0) {
            webEngine.executeScript("document.goToLocation( " + lat + "," + lon + ");");
         }
      }

   }
}
