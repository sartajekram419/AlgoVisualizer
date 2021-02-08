package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * This class controls the customized input for searching
 *
 * @author Sajid_180041203
 */
public class SearchCustomInputController implements Initializable {

    @FXML
    private Button OkButton;
    @FXML private TextArea TextInput;

    public static int[] arr;

    @FXML
    void OkButtonPressed(ActionEvent event) {

        arr = new int[SearchPageController.arraySize];

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

        /*for(int i=0;i<SearchPageController.arraySize; i++){
            System.out.println(arr[i]);
        }*/


        OkButton.getScene().getWindow().hide();
    }

    /**
     * This method initializes the class
     *
     * @author Sajid_180041203
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
