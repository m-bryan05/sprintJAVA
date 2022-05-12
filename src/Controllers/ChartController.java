/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Utils.MaConnexion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ChartController implements Initializable {

    @FXML
    private HBox chartHBox;
    @FXML
    private AnchorPane chartNode;
    @FXML
    private ImageView logoView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       logoView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\logo FitHUb.png"));
      //  detailleBonPlan();
        // TODO
    }    

    @FXML
    private void globalChart(ActionEvent event) {
         List<PieChart.Data> myList = new ArrayList<PieChart.Data>();
        ResultSet rs = null;
        PieChart.Data d;
        ObservableList observableList = null;

        try {
// String requete1 = "SELECT * from Reclamation"; //MAJUSCULE NON OBLIGATOIRE 
// String requete = "SELECT ref_bonplan,COUNT(id) as nbr FROM `Reclamation` group by ref_bonplan";
// String requete = "SELECT r.ref_bonplan,COUNT(r.id) as nbr FROM Reclamation r,bon_plan b where r.ref_bonplan=b.id group by r.ref_bonplan";
            String requete = "SELECT publication2.type,COUNT(publication2.id) as nbr FROM publication2 group by publication2.type";

            Statement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            rs = pst.executeQuery(requete);
            while (rs.next()) {

                if (rs.getObject(1) == null) {
                    System.out.println(rs.getString(1));
                    d = new PieChart.Data("Autre ", rs.getInt(2));
                } else {
                    d = new PieChart.Data(rs.getString(1), rs.getInt(2));
                }
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getInt(2));
                myList.add(d);

            }
            observableList = FXCollections.observableArrayList(myList);

        } catch (Exception e) {

            System.out.println("Error on DB connection BuildDataBonPlan");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }

    }

    @FXML
    private void detailleBonPlan() {
        double total = 0;
        DecimalFormat df2 = new DecimalFormat(".##");
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("interaction with topics");
        stage.setWidth(600);
        stage.setHeight(600);

        final PieChart chart = new PieChart(buildDataBonPlan());
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKBLUE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData()) {
            total = total + data.getPieValue();
        }
        final double totalFinal = total;

        for (final PieChart.Data data : chart.getData()) {
            data.setName(((data.getName() + " " + df2.format((data.getPieValue() / totalFinal) * 100))) + "%");
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {

                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(df2.format((data.getPieValue() / totalFinal) * 100)) + "%");
                    if (!((Group) scene.getRoot()).getChildren().contains(caption)) {
                        ((Group) scene.getRoot()).getChildren().add(caption);
                    }
                }
            });
        }

        chart.setTitle("Interactions with topics");
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        chartNode.getChildren().clear();
        chartNode.getChildren().setAll(chart);

    }
    


    @FXML
    private void lineChart(ActionEvent event) {
        double total = 0;
        DecimalFormat df2 = new DecimalFormat(".##");
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Number of posts per day");
        stage.setWidth(600);
        stage.setHeight(600);


        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        //creating the chart
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                
        
        lineChart.getData().add(buildDataLineChart());
       ((Group) scene.getRoot()).getChildren().add(lineChart);
        stage.setScene(scene);
        chartNode.getChildren().clear();
        chartNode.getChildren().setAll(lineChart);
        
    }

     public XYChart.Series buildDataLineChart() {
         XYChart.Series series = new XYChart.Series();
        series.setName("Number of posts per day");

        ResultSet rs = null;
        XYChart.Series d;
        try {
            String requete = "SELECT publication2.date_creation,COUNT(publication2.id) as nbr FROM publication2 group by publication2.date_creation";

            Statement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            rs = pst.executeQuery(requete);
            while (rs.next()) 
            {
                    series.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
            }
            
            return series;

        } catch (Exception e) {

            System.out.println("Error on DB connection BuildDataLineChart");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
        return series;
    }

    private ObservableList<PieChart.Data> buildDataBonPlan() {
        List<PieChart.Data> myList = new ArrayList<PieChart.Data>();
        ResultSet rs = null;
        PieChart.Data d;
        ObservableList observableList = null;

        try {
// String requete1 = "SELECT * from Reclamation"; //MAJUSCULE NON OBLIGATOIRE 
// String requete = "SELECT ref_bonplan,COUNT(id) as nbr FROM `Reclamation` group by ref_bonplan";
// String requete = "SELECT r.ref_bonplan,COUNT(r.id) as nbr FROM Reclamation r,bon_plan b where r.ref_bonplan=b.id group by r.ref_bonplan";
            String requete = "SELECT topic2.nom,COUNT(publication2.id) as nbr FROM publication2 left join topic2 on publication2.topic_id=topic2.id group by publication2.topic_id";

            Statement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            rs = pst.executeQuery(requete);
            while (rs.next()) {

                if (rs.getObject(1) == null) {
                    System.out.println(rs.getString(1));
                    d = new PieChart.Data("Autre ", rs.getInt(2));
                } else {
                    d = new PieChart.Data(rs.getString(1), rs.getInt(2));
                }
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getInt(2));
                myList.add(d);

            }
            observableList = FXCollections.observableArrayList(myList);

            return observableList;

        } catch (Exception e) {

            System.out.println("Error on DB connection BuildDataBonPlan");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
        return observableList;
    
    }
    
}
