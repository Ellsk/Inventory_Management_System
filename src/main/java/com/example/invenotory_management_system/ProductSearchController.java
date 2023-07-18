package com.example.invenotory_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ProductSearchController implements Initializable {

    @FXML
    private TableView<ProductSearchModule> productTableView;
    @FXML
    private TableColumn<ProductSearchModule, Integer> productIDTableColumn;
    @FXML
    private TableColumn<ProductSearchModule, String> brandTableColumn;
    @FXML
    private TableColumn<ProductSearchModule, String> modelNumberTableColumn;
    @FXML
    private TableColumn<ProductSearchModule, Integer> modelYearTableColumn;
    @FXML
    private TableColumn<ProductSearchModule, String> productNameTableColumn;
    @FXML
    private TableColumn<ProductSearchModule, String> descriptionTableColumn;

    ObservableList<ProductSearchModule> productSearchModuleObservableList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // SQL Query - Execute the database
        String productViewQuery = "SELECT ProductID, Brand, ModelNumber, ModelYear, ProductName, Description FROM products";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(productViewQuery);

            while(queryOutput.next()){
                Integer queryProductID = queryOutput.getInt("ProductID");
                String queryBrand = queryOutput.getString("Brand");
                String queryModelNumber = queryOutput.getString("ModelNumber");
                Integer queryModelYear = queryOutput.getInt("ModelYear");
                String queryProductName = queryOutput.getString("ProductName");
                String queryDescription = queryOutput.getString("Description");

                // Populate the observableList
                productSearchModuleObservableList.add(new ProductSearchModule(queryProductID, queryBrand, queryModelNumber, queryModelYear, queryProductName, queryDescription));
            }

            // Set cell value factories
            productIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
            brandTableColumn.setCellValueFactory(new PropertyValueFactory<>("Brand"));
            modelNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("ModelNumber"));
            modelYearTableColumn.setCellValueFactory(new PropertyValueFactory<>("ModelYear"));
            productNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
            descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));

            // Set the observable list to the table view
            productTableView.setItems(productSearchModuleObservableList);

        } catch (SQLException e) {
            Logger.getLogger(ProductSearchController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
