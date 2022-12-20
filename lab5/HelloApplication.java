package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws InterruptedException {

        DataMapper mapperOneToOne=new DataMapper("One To One");
        DataMapper mapperOneToTen=new DataMapper("One To Ten");
        DataMapper mapperOneToPixel=new DataMapper("One To Pixel");
        DataMapper mapperCoreToOne=new DataMapper("Core To One");
        DataMapper mapperCoreToTen=new DataMapper("Core To Ten");
        DataMapper mapperCoreToPixel=new DataMapper("Core To Pixel");
        DataMapper mapper2CoreToOne=new DataMapper("2 Core To One");
        DataMapper mapper2CoreToTen=new DataMapper("2 Core To Ten");
        DataMapper mapper2CoreToPixel=new DataMapper("2 Core To Pixel");

        int cores = Runtime.getRuntime().availableProcessors();
        run(1,1,mapperOneToOne);
        run(1,10,mapperOneToTen);
        run(1,Config.getHeight()*Config.getWidth(),mapperOneToPixel);
        run(cores,1,mapperCoreToOne);
        run(cores,10,mapperCoreToTen);
        run(cores,Config.getHeight()*Config.getWidth(),mapperCoreToPixel);
        run(2*cores,1,mapper2CoreToOne);
        run(2*cores,10,mapper2CoreToTen);
        run(2*cores,Config.getHeight()*Config.getWidth(),mapper2CoreToPixel);

        TableColumn<DataMapper,String> typeColum = new TableColumn<>("Type");
        typeColum.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColum.setPrefWidth(200);
        TableColumn<DataMapper,Double> avgColum = new TableColumn<>("AVG");
        avgColum.setCellValueFactory(new PropertyValueFactory<>("AVG"));
        avgColum.setPrefWidth(200);
        TableColumn<DataMapper,Double> sdColum = new TableColumn<>("SD");
        sdColum.setCellValueFactory(new PropertyValueFactory<>("SD"));
        sdColum.setPrefWidth(200);

        TableView table = new TableView();

        table.getColumns().addAll(typeColum, avgColum, sdColum);

        mapperOneToOne.setAll();
        mapperOneToTen.setAll();
        mapperOneToPixel.setAll();
        mapperCoreToOne.setAll();
        mapperCoreToTen.setAll();
        mapperCoreToPixel.setAll();
        mapper2CoreToOne.setAll();
        mapper2CoreToTen.setAll();
        mapper2CoreToPixel.setAll();

        table.getItems().add(mapperOneToOne);
        table.getItems().add(mapperOneToTen);
        table.getItems().add(mapperOneToPixel);
        table.getItems().add(mapperCoreToOne);
        table.getItems().add(mapperCoreToTen);
        table.getItems().add(mapperCoreToPixel);
        table.getItems().add(mapper2CoreToOne);
        table.getItems().add(mapper2CoreToTen);
        table.getItems().add(mapper2CoreToPixel);

        table.setCenterShape(true);

        Scene scene  = new Scene(table,600,600);
        stage.setScene(scene);
        stage.show();


    }

    public void run(int threads, int tasks,DataMapper mapper){
        for (int i = 0; i < Config.getArraySize(); i++) {
            Mandelbrot mandelBrot = new Mandelbrot(threads, tasks);
            long startTime = System.nanoTime();
            mandelBrot.run();
            mapper.addTime(System.nanoTime() - startTime);
//            mandelBrot.showImage();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        launch();
    }
}