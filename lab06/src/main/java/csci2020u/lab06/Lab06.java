package csci2020u.lab06;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Lab06 extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Lab06.class.getResource("lab06.fxml"));

        AnchorPane container = new AnchorPane();

        Scene scene = new Scene(container, 700, 400);
        createBars(container);
        stage.setTitle("Lab 06!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    //Bar Graph
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    //Pie Graph
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    private void createBars(AnchorPane container){
        container.setRotate(180);
        //Getting the max price
        double maxValue = 0;
        for(int i = 0; i < avgHousingPricesByYear.length; i++){
            if(avgCommercialPricesByYear[i] > maxValue)
                maxValue = avgCommercialPricesByYear[i];
            if(avgHousingPricesByYear[i] > maxValue)
                maxValue = avgHousingPricesByYear[i];
        }

        //Looping through all prices and creating their respective rectangles
        double padding = container.getWidth()-50;
        for(int i = 0; i < avgHousingPricesByYear.length; i++){

            Rectangle rect = new Rectangle();
            rect.setFill(Color.RED);
            rect.setWidth(15);
            rect.setHeight(avgHousingPricesByYear[i]/maxValue*container.getHeight());
            rect.setX(padding);
            rect.setY(25);

            container.getChildren().add(rect);
            padding-=15;

            rect = new Rectangle();
            rect.setFill(Color.BLUE);
            rect.setWidth(15);
            rect.setHeight(avgCommercialPricesByYear[i]/maxValue*(container.getHeight()-50));
            rect.setX(padding);
            rect.setY(25);
            container.getChildren().add(rect);
            padding-=20;
        }

        maxValue = 0;
        for(int x : purchasesByAgeGroup)
            maxValue+=x;
        int startAngle = 0;
        for(int i = 0; i < pieColours.length; i++){

            double angle = 360 * (purchasesByAgeGroup[i]/maxValue);

            Arc arc = new Arc();
            arc.setType(ArcType.ROUND);
            arc.setCenterX(container.getWidth()/4);
            arc.setCenterY(container.getHeight()/2);
            arc.setFill(pieColours[i]);
            arc.setStartAngle(startAngle);
            arc.setLength(angle);
            arc.setRadiusX(150);
            arc.setRadiusY(150);

            if(i == pieColours.length-1)  //Filling in last pie section
                arc.setLength(360-startAngle);

            container.getChildren().add(arc);

            startAngle+=angle;

        }
    }

}