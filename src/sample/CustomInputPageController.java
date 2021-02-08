package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * This class controls customized input for sorting
 *
 * @author Sajid_180041203
 */
public class CustomInputPageController implements Initializable {

    @FXML private Button OkButton;
    @FXML private TextArea TextInput;

    /**
     * It denotes the array of custom input
     */
    public static int[] arr;

    @FXML
    void OkButtonPressed(ActionEvent event) {

        arr = new int[SortingPageController.NO_OF_NODES];

        String numbers = TextInput.getText();
        String temp = new String();

        //System.out.println(numbers);

        int j=0;
        for (int i=0;i<numbers.length();i++)
        {

            if (numbers.charAt(i)!=' ')
                temp+=numbers.charAt(i);
            if (numbers.charAt(i)==' '){
                arr[j]=Integer.parseInt(temp);
                j++;
                temp="";
            }
        }
        arr[j]=Integer.parseInt(temp);


        OkButton.getScene().getWindow().hide();
    }

    /**
     * This method initializes when the class is created
     *
     * @author Sajid_180041203
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
