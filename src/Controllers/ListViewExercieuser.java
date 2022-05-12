import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListViewExercieuser extends Application {

    private final Image IMAGE_Abdos  = new Image("http://www.all-musculation.com/AM-v6/images/20170130-abdos-perdre-ventre.jpg");
    private final Image IMAGE_Biceps  = new Image("https://www.objectifs-fitness.com/wp-content/uploads/2016/12/programme-musculation-biceps-gratuit.jpg");
    private final Image IMAGE_Triceps  = new Image("https://www.all-musculation.com/AM/images/AM-ameliorer-puissance-triceps.jpg");
    private final Image IMAGE_Dorsos = new Image("https://t2.uc.ltmcdn.com/fr/posts/9/1/2/comment_muscler_son_dos_sans_materiel_11219_600.jpg");

    private Image[] listOfImages = {IMAGE_Abdos, IMAGE_Biceps, IMAGE_Triceps, IMAGE_Dorsos};

    @Override
    public void start(Stage primaryStage) throws Exception {

        ListView<String> listView = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList (
                "Abdos", "Biceps", "Triceps", "Dorsos");
        listView.setItems(items);

        listView.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if(name.equals("Abdos"))
                        imageView.setImage(listOfImages[0]);
                    else if(name.equals("Biceps"))
                        imageView.setImage(listOfImages[1]);
                    else if(name.equals("Triceps"))
                        imageView.setImage(listOfImages[2]);
                    else if(name.equals("Dorsos"))
                        imageView.setImage(listOfImages[3]);
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });
        VBox box = new VBox(listView);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}