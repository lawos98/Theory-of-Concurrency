package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws InterruptedException {
        int SIZE=1000;

        DataMapper MapperTrue= new DataMapper(SIZE);
        DataMapper MapperFalse= new DataMapper(SIZE);

        startThreads(SIZE,MapperTrue,true);
        startThreads(SIZE,MapperFalse,false);



        sleep(120000);

        stage.setTitle("Line Chart");
        final NumberAxis xAxis1 = new NumberAxis();
        final NumberAxis yAxis1 = new NumberAxis();
        final NumberAxis xAxis2 = new NumberAxis();
        final NumberAxis yAxis2 = new NumberAxis();
        xAxis1.setLabel("Number");
        xAxis2.setLabel("Number");
        yAxis1.setLabel("Time");
        yAxis2.setLabel("Time");
        final ScatterChart<Number,Number> lineChartConsumer = new ScatterChart<Number,Number>(xAxis1,yAxis1);
        final ScatterChart<Number,Number> lineChartProducer = new ScatterChart<Number,Number>(xAxis2,yAxis2);

        lineChartConsumer.setTitle("Line Chart Consumer");
        lineChartProducer.setTitle("Line Chart Producer");
        XYChart.Series seriesProducerTrue = getSeries("Producer - true",MapperTrue.getResultsProducer());
        XYChart.Series seriesProducerFalse = getSeries("Producer - false",MapperFalse.getResultsProducer());
        XYChart.Series seriesConsumerTrue = getSeries("Consumer - true",MapperTrue.getResultsConsumer());
        XYChart.Series seriesConsumerFalse = getSeries("Consumer - false",MapperFalse.getResultsConsumer());

        HBox box=new HBox();
        box.alignmentProperty().set(Pos.CENTER);
        box.getChildren().add(lineChartConsumer);
        box.getChildren().add(lineChartProducer);

        lineChartProducer.getData().addAll(seriesProducerTrue, seriesProducerFalse);
        lineChartConsumer.getData().addAll(seriesConsumerTrue, seriesConsumerFalse);

        Scene scene  = new Scene(box,1800,1080);
        stage.setScene(scene);
        stage.show();
    }

    private static void startThread(Runnable object){
        Thread newThread=new Thread(object);
        newThread.start();
    }

    private static void startThreads(int size,DataMapper mapper,boolean flag){
        if(flag) {
            FairBuffer buffer1 = new FairBuffer(size);
            for (int i = 0; i <= 50; i++) {
                startThread(new Producer(buffer1, mapper));
            }
            for (int i = 0; i <= 50; i++) {
                startThread(new Consumer(buffer1, mapper));
            }
        }
        else{
            NaiveBuffer naiveBuffer1 = new NaiveBuffer(size);
            for (int i = 0; i <= 50;i++) {
                startThread(new Producer(naiveBuffer1, mapper));
            }
            for (int i = 0; i <= 50; i++) {
                startThread(new Consumer(naiveBuffer1, mapper));
            }
        }
    };

    public XYChart.Series getSeries(String name,long[] tab){
        XYChart.Series series = new XYChart.Series();
        series.setName(name);
        for(int i=1;i<tab.length;i++){
            if(tab[i]!=0)series.getData().add(new XYChart.Data(i,tab[i]));
        }
        return series;
    }

    public static void main(String[] args) throws InterruptedException {
        launch();
    }
}