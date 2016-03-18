package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    private Canvas canvas;
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 700);


        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());

        root.getChildren().add(canvas);
        primaryStage.setTitle("Lab 6");
        primaryStage.setScene(scene);
        primaryStage.show();

        draw(root);
    }
    private void draw(Group group) {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        /*
        fillRect
        Parameters:
        x - the X position of the upper left corner of the rectangle.
        y - the Y position of the upper left corner of the rectangle.
        w - the width of the rectangle.
        h - the height of the rectangle.
        */
        double baseY = 450;
        int baseX = 50;
        int baseHeight = 100;
        double heightMult = 1;

        /*gc.setFill(Color.RED);
        gc.fillRect(50, baseY, 50, 200);
        gc.fillRect(150, baseY-50, 50, baseHeight+50);
        gc.fillRect(250, baseY-100, 50, baseHeight+100);*/

        //ArrayList<Double> avgHousingHeightMult = new ArrayList<>();

        gc.setFill(Color.RED);

        for (int i = 0; i < avgHousingPricesByYear.length; i++) {
            //double basePrice = avgHousingPricesByYear[0];

            //avgHousingHeightMult.add((basePrice/avgHousingPricesByYear[i]));


            heightMult = avgHousingPricesByYear[i] / avgHousingPricesByYear[0];
            System.out.println("heightMult = " + heightMult);
            baseY -= ((baseHeight * heightMult) - baseHeight);
            gc.fillRect(baseX, baseY, 10, baseHeight * heightMult);
            baseX += 30;
            baseY = 450;


        }

        baseX = 60;
        baseY = 350;
        baseHeight = 200;
        gc.setFill(Color.BLUE);

        for (int i = 0; i < avgCommercialPricesByYear.length; i++) {

            heightMult = avgCommercialPricesByYear[i] / avgCommercialPricesByYear[0];
            baseY -= ((baseHeight * heightMult) - baseHeight);
            gc.fillRect(baseX, baseY, 10, baseHeight * heightMult);
            baseX += 30;
            baseY = 350;
        }





        //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }


    public static void main(String[] args) {
        launch(args);
    }
}

