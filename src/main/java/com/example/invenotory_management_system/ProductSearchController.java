package com.example.invenotory_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    @FXML
    private TextField keywordTextField;
    ObservableList<ProductSearchModule> productSearchModuleObservableList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // SQL Query - Execute the database
        String productViewQuery = "SELECT ProductID, Brand, ModelNumber, ModelYear, ProductName, Description FROM products";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(productViewQuery);

            // Create filtered list outside the loop
            FilteredList<ProductSearchModule> filteredData = new FilteredList<>(productSearchModuleObservableList, b -> true);

            while (queryOutput.next()) {
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

            // Here is the filtered list
            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(productSearchModule -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (productSearchModule.getProductName().toLowerCase().contains(searchKeyword)) {
                    return true; // Then we found a productName
                } else if (productSearchModule.getDescription().toLowerCase().contains(searchKeyword)) {
                    return true; // Found keyword in description
                } else // No match
                    if (productSearchModule.getModelNumber().contains(searchKeyword)) {
                        return true; // Found keyword in the modelNum
                    } else return productSearchModule.getModelYear().toString().contains(searchKeyword);
            }));

            SortedList<ProductSearchModule> sortedData = new SortedList<>(filteredData);

            // For binding sorted result with table
            sortedData.comparatorProperty().bind(productTableView.comparatorProperty());

            // Apply filtered and sorted data in Table
            productTableView.setItems(sortedData);


        } catch (SQLException e) {
            Logger.getLogger(ProductSearchController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
