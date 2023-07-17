package com.example.invenotory_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mysql.cj.conf.PropertyKey.logger;

public class ProductSearchController {

    @FXML
    private TableView<ProductSearchModule> productTableView;
    @FXML
    private TableColumn<ProductSearchModule, Integer> productTableColumn;
    @FXML
    private TableColumn<ProductSearchModule, Integer> productIDTableColumn;
    @FXML
    private TableColumn<ProductSearchModule, String> modelNumberColumn;
    @FXML
    private TableColumn<ProductSearchModule, String> brandTableColumn;
    @FXML
    private TableColumn<ProductSearchModule, Integer> modelYearTableColumn;
    @FXML
    private TableColumn<ProductSearchModule, String> descriptionTableColumn;
    @FXML
    private TableColumn<ProductSearchModule, String> productNameTableColumn;

    ObservableList<ProductSearchModule> productSearchModuleObservableList = FXCollections.observableArrayList();

    public void initialize(URL url){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        //SQL Query - Execute the database
        String productViewQuery = "select PoductID, productName, Brand, ModelNumber, ModelYear, Description from products";

        try{

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(productViewQuery);

            while(queryOutput.next()){

                Integer queryProductID = queryOutput.getInt("ProductID");
                String queryBrand = queryOutput.getString("Brand");
                Integer queryModelYear = queryOutput.getInt("ModelYear");
                String queryModelNumber = queryOutput.getString("ModelYear");
                String queryProductName = queryOutput.getString("ProductName");
                String queryDescription = queryOutput.getString("Description");

                //Populate the observableList
                productSearchModuleObservableList.add(new ProductSearchModule(queryProductID, queryBrand,queryModelYear, queryModelNumber , queryProductName,  queryDescription));


            }
        }catch (SQLException e){
            Logger.getLogger(ProductSearchController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
