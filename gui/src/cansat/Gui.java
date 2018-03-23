package cansat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.imageio.ImageIO;

public class Gui {
   private int width;
   private int height;
   private VBox vBox0;
   private VBox vBox1;
   private VBox vBox2;
   private HBox row;
   private HBox tempRow;
   private VBox tempCol0;
   private VBox tempCol1;
   private static Scene scene;
   private static StackPane pane;
   private GridPane grid;
   private WebView Map;
   private static Console console;
   private static Button Btn;
   private static StatusButton statusBtn;
   private static WebEngine webEngine;
   private DynamicTooltip dynamicTooltip;
   public static TextArea dataPath;
   public static HBox c1;
   public static HBox c2;
   public static String[] StatusButtonNames;

   public Gui(int width, int height) {
      this.width = width;
      this.height = height;
      pane = new StackPane();
      this.grid = new GridPane();
      pane.getChildren().add(this.grid);

      for(int i = 0; i < 3; ++i) {
         VBox vBox = new VBox();
         vBox.setId("hBox" + i);
         vBox.getStyleClass().add("column");
         vBox.prefWidthProperty().bind(pane.widthProperty());
         vBox.prefHeightProperty().bind(pane.heightProperty());
         this.grid.addColumn(i, new Node[]{vBox});
      }

      this.vBox0 = (VBox)pane.lookup("#hBox0");
      this.vBox1 = (VBox)pane.lookup("#hBox1");
      this.vBox2 = (VBox)pane.lookup("#hBox2");
      String Url = "assets/temp.jpg";
      File file = new File(Url);
      BufferedImage image = null;

      try {
         image = ImageIO.read(file);
      } catch (IOException var24) {
         var24.printStackTrace();
      }

      Utils.crateTempRange(image);
      TempGrid tempGrid = new TempGrid(300, 300, image);
      TempGui colorGui = new TempGui(60, 310, image);
      this.tempCol0 = new VBox();
      this.tempCol1 = new VBox();
      this.tempCol0.setPadding(new Insets(10.0D, 0.0D, 40.0D, 40.0D));
      this.tempCol0.prefWidthProperty().bind(pane.widthProperty());
      this.tempCol0.prefHeightProperty().bind(pane.heightProperty().divide(48));
      this.tempCol1.setPadding(new Insets(0.0D, 0.0D, 0.0D, 0.0D));
      this.tempCol1.prefWidthProperty().bind(pane.widthProperty());
      this.tempCol1.prefHeightProperty().bind(pane.heightProperty().divide(48));
      this.tempRow = new HBox();
      this.tempRow.prefWidthProperty().bind(pane.widthProperty());
      this.tempRow.prefHeightProperty().bind(pane.heightProperty().divide(48));
      c1 = new HBox();
      c1.prefWidthProperty().bind(pane.widthProperty());
      c1.prefHeightProperty().bind(pane.heightProperty());
      this.tempCol0.getChildren().add(tempGrid);
      this.tempCol1.getChildren().add(colorGui);
      this.tempRow.getChildren().addAll(new Node[]{this.tempCol0, this.tempCol1});
      this.vBox2.getChildren().addAll(new Node[]{this.tempRow});

      for(int i = 0; i < 7; ++i) {
         NumberAxis xAxis = new NumberAxis();
         NumberAxis yAxis = new NumberAxis();
         int numOfSeries = 1;
         if (i == 0) {
            numOfSeries = 4;
         }

         Graph lineChart = new Graph(xAxis, yAxis, numOfSeries);
         if (i < 4) {
            this.vBox0.getChildren().add(lineChart);
         }

         if (i < 5 && i > 3) {
            this.vBox2.getChildren().add(lineChart);
         }

         if (i == 5) {
            c1.getChildren().add(lineChart);
         }

         if (i == 6) {
            c1.getChildren().add(lineChart);
         }
      }

      this.vBox2.getChildren().addAll(new Node[]{c1});
      Map map = new Map();
      StackPane stackPane = new StackPane();
      stackPane.prefWidthProperty().bind(pane.widthProperty());
      stackPane.prefHeightProperty().bind(pane.heightProperty());
      ImageView imageView = new ImageView();
      Image logo = new Image("logo.jpg");
      imageView.fitWidthProperty().bind(pane.widthProperty().divide(3));
      imageView.fitHeightProperty().bind(pane.heightProperty().divide(5));
      imageView.setImage(logo);
      imageView.setPreserveRatio(true);
      StackPane.setAlignment(imageView, Pos.CENTER_LEFT);
      dataPath = new TextArea();
      dataPath.prefHeight(100.0D);
      dataPath.prefWidthProperty().bind(pane.widthProperty());
      dataPath.setText("datafile.txt");
      StatusButtonNames = new String[]{"Serial connection", "Data logging", "Percent valid packets", "Last packet", "Magnitude of chi^2 location"};
      console = new Console();
      this.vBox1.getChildren().addAll(new Node[]{imageView, map, console, dataPath});

      for(int i = 0; i < 3; ++i) {
         this.row = new HBox();
         this.row.setId("row" + i);
         this.row.prefWidthProperty().bind(pane.widthProperty());
         this.vBox1.getChildren().addAll(new Node[]{this.row});
      }

      HBox tempRow = null;
      DynamicTooltip.setTooltipTimers(0L, 5000L, 0L);

      for(int i = 0; i < 5; ++i) {
         statusBtn = new StatusButton(0);
         statusBtn.setText(StatusButtonNames[i]);
         statusBtn.getStyleClass().add("Btn");
         statusBtn.prefWidthProperty().bind(pane.widthProperty());
         this.dynamicTooltip = new DynamicTooltip(StatusButtonNames[i]);
         statusBtn.setTooltip(this.dynamicTooltip);
         tempRow = (HBox)pane.lookup("#row0");
         tempRow.getChildren().add(statusBtn);
      }

      String[] buttonNames = new String[]{"Login to chip", "Start chip data logging", "+", "-", "Set minimum power", "Clear", "Clear all", "Toggle pressure", "Set heat mapping", "Data lists"};

      for(int i = 0; i < 10; ++i) {
         Btn = new Button("Click me");
         Btn.setId("btn" + i);
         Btn.setText(buttonNames[i]);
         Btn.getStyleClass().add("Btn");
         Btn.prefWidthProperty().bind(pane.widthProperty());
         this.dynamicTooltip = new DynamicTooltip(buttonNames[i]);
         Btn.setTooltip(this.dynamicTooltip);
         if (i < 5) {
            tempRow = (HBox)pane.lookup("#row1");
         }

         if (i > 4) {
            tempRow = (HBox)pane.lookup("#row2");
         }

         tempRow.getChildren().add(Btn);
      }

      Button btn0 = (Button)pane.lookup("#btn0");
      btn0.setOnAction(new EventHandler() {
         public void handle(ActionEvent event) {
         }

		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			
		}
      });
      Button btn1 = (Button)pane.lookup("#btn1");
      btn1.setOnAction(new EventHandler() {
         public void handle(ActionEvent event) {
         }

		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			
		}
      });
      Button btn2 = (Button)pane.lookup("#btn2");
      btn2.setOnAction(new EventHandler() {
         public void handle(ActionEvent event) {
            TempGrid.nextScale *= 2.0F;
         }

		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			
		}
      });
      Button btn3 = (Button)pane.lookup("#btn3");
      btn3.setOnAction(new EventHandler() {
         public void handle(ActionEvent event) {
            TempGrid.nextScale /= 2.0F;
         }

		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			
		}
      });
      Button btn4 = (Button)pane.lookup("#btn4");
      btn4.setOnAction(new EventHandler() {
         public void handle(ActionEvent event) {
         }

		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			
		}
      });
      Button btn5 = (Button)pane.lookup("#btn5");
      btn5.setOnAction(new EventHandler() {
         public void handle(ActionEvent event) {
            Utils.clearAllGraphs(Graph.graphs);
            TempGrid.clear();
            TempValue.tempValues.clear();
            Gui.console.clear();
            ((StatusButton)StatusButton.statusButtons.get(0)).toggle(0);
            ((StatusButton)StatusButton.statusButtons.get(1)).toggle(0);
            ((StatusButton)StatusButton.statusButtons.get(2)).toggle(0);
            ((StatusButton)StatusButton.statusButtons.get(3)).toggle(0);
            ((StatusButton)StatusButton.statusButtons.get(4)).toggle(0);
         }

		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			
		}
      });
      Button btn6 = (Button)pane.lookup("#btn6");
      btn6.setOnAction(new EventHandler() {
         public void handle(ActionEvent event) {
            Utils.clearAllGraphs(Graph.graphs);
            TempGrid.clear();
            Gui.console.clear();
            ((StatusButton)StatusButton.statusButtons.get(0)).toggle(0);
            ((StatusButton)StatusButton.statusButtons.get(1)).toggle(0);
            ((StatusButton)StatusButton.statusButtons.get(2)).toggle(0);
            ((StatusButton)StatusButton.statusButtons.get(3)).toggle(0);
            ((StatusButton)StatusButton.statusButtons.get(4)).toggle(0);
            DecodeData.numOfLinesRead = 0;
            DecodeData.lastNumOfLines = 0;
            DecodeData.numOfLines = 0;
            TempValue.tempValues.clear();
         }

		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			
		}
      });
      Button btn7 = (Button)pane.lookup("#btn7");
      btn7.setOnAction(new EventHandler() {
         public void handle(ActionEvent event) {
            if (((Graph)Graph.graphs.get(1)).updateNow) {
               ((Graph)Graph.graphs.get(1)).updateNow = false;
            } else {
               ((Graph)Graph.graphs.get(1)).updateNow = true;
            }

         }

		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			
		}
      });
      Button btn8 = (Button)pane.lookup("#btn8");
      btn8.setOnAction(new EventHandler() {
         public void handle(ActionEvent event) {
         }

		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			
		}
      });
      Button btn9 = (Button)pane.lookup("#btn9");
      btn9.setOnAction(new EventHandler() {
         public void handle(ActionEvent event) {
         }

		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			
		}
      });
      this.vBox1.setPadding(new Insets(0.0D, 0.0D, 0.0D, 0.0D));
      this.vBox1.setPadding(new Insets(15.0D, 0.0D, 40.0D, 0.0D));
      this.vBox2.setPadding(new Insets(5.0D, 0.0D, 0.0D, 0.0D));
      scene = new Scene(pane, (double)width, (double)height);
   }

   public static Scene getGui() {
      return scene;
   }

   public static WebEngine getWebEngine() {
      return webEngine;
   }

   public static StackPane getPane() {
      return pane;
   }

   public static Console getConsole() {
      return console;
   }
}
