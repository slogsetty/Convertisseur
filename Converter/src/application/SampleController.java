package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;

public class SampleController implements Initializable {
	
	@FXML
	private TextField txtTemps2;

	@FXML
	private TextField txtTemps1;
    
	@FXML
    private ComboBox<String> cboTemps2;
    
    @FXML
    private ComboBox<String> cboTemps1;
    
    @FXML
    private TextField txtTemperature1;

    @FXML
    private ComboBox<String> cboTemperature1;

    @FXML
    private TextField txtTemperature2;

    @FXML
    private ComboBox<String> cboTemperature2;
    
    @FXML
    private TextField txtSN1;
    
    @FXML
    private TextField txtSN2;
    
    @FXML
    private ComboBox<String> cboSN1;
    
    @FXML
    private ComboBox<String> cboSN2;
    

  // Permet de mettre les listes/sections du combobox (Temps, Temperature, Stockage Numerique)
    public ObservableList<String> listTemps=FXCollections.observableArrayList("Seconde","Minute","Heure");
    
    public ObservableList<String> listTemperature=FXCollections.observableArrayList("Degree Celcius","Farenheit","Kelvin");
    
    public ObservableList<String> listStockageNumerique=FXCollections.observableArrayList("Byte","Kilobyte","Kibibyte");
    
    // Permet de determiner les valeurs de chaque article du liste
    private double Temps[]= {1.0, 0.0166667, 0.000277778};
    
    private double Temperature[]= {1.0, 33.8, 274.15};
    
    private double StockageNumerique[]= {1.0, 0.001, 0.000976563};
    
  
    @Override
    public void initialize(URL location, ResourceBundle resources)
    /*
     * Pour que le ComboBox selectionne toujours le premier element de la liste (Temps, Temperature, Stockage Numerique)
     */
    {
    	cboTemps1.setItems(listTemps);
    	cboTemps2.setItems(listTemps);
    	
    	cboTemperature1.setItems(listTemperature);
    	cboTemperature2.setItems(listTemperature);
    	
    	cboSN1.setItems(listStockageNumerique);
    	cboSN2.setItems(listStockageNumerique);
    	
    	cboTemps1.getSelectionModel().selectFirst();
    	cboTemps2.getSelectionModel().selectFirst();
    	
    	cboTemperature1.getSelectionModel().selectFirst();
    	cboTemperature2.getSelectionModel().selectFirst();
    	
    	
    	cboSN1.getSelectionModel().selectFirst();
    	cboSN2.getSelectionModel().selectFirst();
  
    	
    }

     @FXML
   private void verifNum(KeyEvent e) //verifie l'action cree par le clavier
   {
	   TextField txt=(TextField)e.getSource();
	   
	   txt.textProperty().addListener((observable,oldValue,newValue)->
	   {
		   if(!newValue.matches("^-?[0-9](\\.[0-9]+)?$"))
		   {
			   txt.setText(newValue.replaceAll("[^\\d*\\.\\-]",""));
		   }
	   });
	   }
  
    //donne un index a chaque article sur la liste du combobox 
   // de plus il va retourner la valeur indique dans le tableau  
   private double setTaux(ComboBox a, double tbl[])
   {
	int item=a.getSelectionModel().getSelectedIndex();
	double val=tbl[item];
	return val;
   }
   
   //determine le taux que l'on doit changer, fait des correspondances avec les combobox, textfields, tableau
   private void convert (ComboBox a, ComboBox b, TextField c, TextField d, double tbl[])
   {
	   double from=setTaux(a,tbl);
	   double depart=0;
	   
	   if(c.getText().equals(" "))
		   depart=0;
	   else
		   depart=Double.parseDouble(c.getText());
	    /*
	    * depart=c.getText().equals("")?0:Double.parseDouble(c.getText());
	    */
	   
	   double to=setTaux(b,tbl);
	   double dest=(to/from)*depart; 
	   d.setText(String.valueOf(dest));
   }
  
   //Cela convertit le temps (Seconde,Minute,Heure)
   @FXML
   private void ConvertTemps1()
   {
	   convert(cboTemps1,cboTemps2,txtTemps1,txtTemps2,Temps);
   }
   
   @FXML
   private void ConvertTemps2()
   {
	   convert(cboTemps2,cboTemps1,txtTemps2,txtTemps1,Temps);
   }
   
   //Cela converit la temperature (Degree Celcius, Farenheit, Kelvin)
   @FXML
   private void ConvertTemperature1()
   {
	   convert(cboTemperature1,cboTemperature2,txtTemperature1,txtTemperature2,Temperature);
   }
   
   @FXML
   private void ConvertTemperature2()
   {
	   convert(cboTemperature2,cboTemperature1,txtTemperature2,txtTemperature1,Temperature);
   }
   
   //Cela convertit le stockage numerique (Byte, Kiliobyte, Kibibyte)
   @FXML
   private void ConvertSN1()
   {
	   convert(cboSN1,cboSN2,txtSN1,txtSN2,StockageNumerique);
   }
   
   @FXML
   private void ConvertSN2()
   {
	   convert(cboSN2,cboSN1,txtSN2,txtSN1,StockageNumerique);
   }
   
   @FXML 
   void quitter() //confirme avec l'utlisateur si il veut quitter
   {
   	Alert alert= new Alert(AlertType.CONFIRMATION);
   	alert.setHeaderText("Confirmation");
   	alert.setTitle("Sortie ");
   	alert.setContentText("Voudrais tu vraiment quitter");
   	Optional<ButtonType> result=alert.showAndWait();
   	if(result.get()==ButtonType.OK)
   		System.exit(0);
   		
   }
}