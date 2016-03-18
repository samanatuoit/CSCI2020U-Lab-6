package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    private Canvas canvas;

    // bar chart date
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    // pie chart data
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
            //System.out.println("heightMult = " + heightMult);
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
        /*
        fillArc Parameters:
        x - the X coordinate of the arc.
        y - the Y coordinate of the arc.
        w - the width of the arc.
        h - the height of the arc.
        startAngle - the starting angle of the arc in degrees.
        arcExtent - the angular extent of the arc in degrees.
        closure - closure type (Round, Chord, Open) or null.
         */
        //gc.fillArc(500,250,250,250,0,45,ArcType.ROUND);

        /*gc.setFill(Color.BLUE);
        gc.fillArc(500,250,250,250,45,110, ArcType.ROUND);
        gc.setFill(Color.RED);
        gc.fillArc(500,250,250,250,110,145, ArcType.ROUND);
        gc.setFill(Color.YELLOW);
        gc.fillArc(500,250,250,250,145,260, ArcType.ROUND);*/

        /*gc.setFill(Color.BLUE);
        gc.fillArc(500,250,250,250,0,90, ArcType.ROUND);
        gc.setFill(Color.RED);
        gc.fillArc(500,250,250,250,90,90,ArcType.ROUND);
        gc.setFill(Color.YELLOW);
        gc.fillArc(500,250,250,250,180,90,ArcType.ROUND);
        gc.setFill(Color.GREEN);
        gc.fillArc(500,250,250,250,270,90,ArcType.ROUND);*/






        double startAngle = 0;
        double extentAngle = 0;
        double totalPurchases = 0;

        for (double purchase : purchasesByAgeGroup) {
            totalPurchases += purchase;
        }

        /*

        First version

        for (int i=0; i < ageGroups.length; i++) {
            gc.setFill(pieColours[i]);
            //extentAngle = (((totalPurchases / purchasesByAgeGroup[i]) / 100) * 360);
            extentAngle += (purchasesByAgeGroup[i] / totalPurchases) * (360 - startAngle);
            System.out.println("startAngle = " + startAngle);
            System.out.println("extantAngle = " + extentAngle);
            gc.fillArc(500, 250, 250, 250,startAngle, extentAngle, ArcType.ROUND);
            startAngle = extentAngle;

        }
        */

        /* Second version */

        for (int i =0; i < ageGroups.length; i++) {
            gc.setFill(pieColours[i]);
            startAngle += extentAngle;
            extentAngle = (purchasesByAgeGroup[i] / totalPurchases) * 360;
            gc.fillArc(500, 250, 250, 250, startAngle, extentAngle, ArcType.ROUND);
        }








    }


    public static void main(String[] args) {
        launch(args);
    }
}

