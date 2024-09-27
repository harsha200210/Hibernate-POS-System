package lk.ijse.controller;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.tdm.CustomerTm;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableView<CustomerTm> tblCustomer;


    CustomerBO customerBO = (CustomerBO) BOFactory.getBO(BOFactory.BOType.CUSTOMER);

    public void initialize() {
        setCellValueFactory();
        loadAllData();
    }

    private void loadAllData() {
        List<CustomerDTO> allCustomers = customerBO.getAllCustomers();
        tblCustomer.getItems().clear();
        ObservableList<CustomerTm> customerTmObservableList = tblCustomer.getItems();
        for (CustomerDTO customerDTO : allCustomers) {
            customerTmObservableList.add(new CustomerTm(customerDTO.getCustomerId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getTel()));
        }
        tblCustomer.setItems(customerTmObservableList);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        CustomerDTO customerDTO = new CustomerDTO(Integer.parseInt(txtId.getText()),txtName.getText(),txtAddress.getText(),Integer.parseInt(txtTel.getText()),new ArrayList<>());
        try {
            customerBO.save(customerDTO);
            loadAllData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        CustomerDTO customerDTO = new CustomerDTO(Integer.parseInt(txtId.getText()),txtName.getText(),txtAddress.getText(),Integer.parseInt(txtTel.getText()),new ArrayList<>());
        customerBO.delete(customerDTO);
        loadAllData();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        CustomerDTO customerDTO = new CustomerDTO(Integer.parseInt(txtId.getText()),txtName.getText(),txtAddress.getText(),Integer.parseInt(txtTel.getText()),new ArrayList<>());
        customerBO.update(customerDTO);
        loadAllData();
    }

    @FXML
    void tblCustomerAction(MouseEvent event) {
        CustomerTm customerTm = tblCustomer.getSelectionModel().getSelectedItem();
        if (customerTm != null) {
            txtId.setText(String.valueOf(customerTm.getCustomer_id()));
            txtName.setText(customerTm.getName());
            txtAddress.setText(customerTm.getAddress());
            txtTel.setText(String.valueOf(customerTm.getTel()));
        }
    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {
        txtTel.requestFocus();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @FXML
    void txtTelOnAction(ActionEvent event) {

    }


}
