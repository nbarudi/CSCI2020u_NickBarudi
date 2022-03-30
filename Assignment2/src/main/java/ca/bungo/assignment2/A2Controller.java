package ca.bungo.assignment2;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.List;

public class A2Controller {

    @FXML
    private BarChart airlineBarChart;

    @FXML
    public void initialize(){
        CSVController csvData = new CSVController("airline_safety.csv");

        List<CSVController.AirlineData> dataList = csvData.airlineDataList;

        //Creating Barchart Elements
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Fatal Incidents 1985-1999");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Fatal Incidents 2000-2014");

        for(CSVController.AirlineData data : dataList){
            //Adding Data to BarChart Elements
            series1.getData().add(new XYChart.Data(data.airlineName, data.inc_99));
            series2.getData().add(new XYChart.Data(data.airlineName, data.inc_14));
        }

        airlineBarChart.getData().addAll(series1, series2);

    }

}