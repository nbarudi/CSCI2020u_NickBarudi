package ca.bungo.lab07;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Lab07 extends Application {

    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    private Map<String, Integer> weatherTypes = new HashMap<>();
    private int totalElements = 0;

    @Override
    public void start(Stage stage) throws IOException {

        try{
            Reader in = new FileReader("weatherwarnings-2015.csv");
            Iterable<CSVRecord> records = CSVFormat.Builder.create().setHeader("TimeStart", "TimeEnd", "Unknown1", "Unknown2", "Unknown3", "Type", "PolyData").build().parse(in);
            for(CSVRecord record : records){
                totalElements++;
                String type = record.get("Type");
                if(weatherTypes.containsKey(type)){
                    weatherTypes.put(type, weatherTypes.get(type) + 1);
                }else{
                    weatherTypes.put(type, 1);
                }
            }
        } catch(IOException e){e.printStackTrace();}


        AnchorPane pane = new AnchorPane();

        int padding = 30;
        int idx = 0;
        for(String key : weatherTypes.keySet()){
            Rectangle rect = new Rectangle(40,30);
            rect.setFill(pieColours[idx]);
            rect.setX(50);
            rect.setStroke(Color.BLACK);
            rect.setStrokeWidth(2);
            rect.setY(padding);

            Label label = new Label(key);
            label.setFont(new Font(12));
            label.setTranslateY(padding);
            label.setTranslateX(100);

            pane.getChildren().add(label);
            pane.getChildren().add(rect);

            padding += 35;
            idx++;
        }

        Scene scene = new Scene(pane, 700, 400);
        createChart(pane);
        stage.setTitle("Lab 7!");
        stage.setScene(scene);
        stage.show();
    }

    private void createChart(AnchorPane container){
        int idx = 0;
        int startAngle = 0;
        for(String key : weatherTypes.keySet()){
            double num = weatherTypes.get(key);
            System.out.println(num);
            double perc = num / totalElements;
            double angle = 360 * perc;
            Arc arc = new Arc();
            arc.setType(ArcType.ROUND);
            arc.setCenterX(container.getWidth() - container.getWidth()/4);
            arc.setCenterY(container.getHeight() - container.getHeight()/2);
            arc.setFill(pieColours[idx]);
            arc.setStartAngle(startAngle);
            arc.setLength(angle);
            arc.setRadiusX(150);
            arc.setRadiusY(150);

            if(idx == weatherTypes.keySet().size() - 1){
                arc.setLength(360-startAngle);
            }

            idx++;
            container.getChildren().add(arc);
            startAngle+=angle;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}