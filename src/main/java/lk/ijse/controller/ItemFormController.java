package lk.ijse.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.tdm.ItemTm;

import java.util.ArrayList;
import java.util.List;

public class ItemFormController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<ItemTm> tblItem;


    ItemBO itemBO = (ItemBO) BOFactory.getBO(BOFactory.BOType.ITEM);

    public void initialize() {
        setCellValueFactory();;
        loadAllData();
    }

    private void loadAllData() {
        List<ItemDTO> allItems = itemBO.getAllItems();
        tblItem.getItems().clear();
        ObservableList<ItemTm> items = tblItem.getItems();
        for (ItemDTO itemDTO : allItems) {
            items.add(new ItemTm(itemDTO.getItemId(),itemDTO.getModel(),itemDTO.getUnitPrice(),itemDTO.getQty()));
        }

        tblItem.setItems(items);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ItemDTO itemDTO = new ItemDTO(Integer.parseInt(txtId.getText()), txtModel.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQty.getText()),new ArrayList<>());
        itemBO.delete(itemDTO);
        loadAllData();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        ItemDTO itemDTO = new ItemDTO(Integer.parseInt(txtId.getText()), txtModel.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQty.getText()),new ArrayList<>());
        itemBO.save(itemDTO);
        loadAllData();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        ItemDTO itemDTO = new ItemDTO(Integer.parseInt(txtId.getText()), txtModel.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQty.getText()),new ArrayList<>());
        itemBO.update(itemDTO);
        loadAllData();
    }

    @FXML
    void tblItemAction(MouseEvent event) {
        ItemTm itemTm = tblItem.getSelectionModel().getSelectedItem();
        if (itemTm != null) {
            txtId.setText(String.valueOf(itemTm.getItem_id()));
            txtModel.setText(itemTm.getModel());
            txtPrice.setText(String.valueOf(itemTm.getUnit_price()));
            txtQty.setText(String.valueOf(itemTm.getQty()));
        }
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtModel.requestFocus();
    }

    @FXML
    void txtModelOnAction(ActionEvent event) {
        txtPrice.requestFocus();
    }

    @FXML
    void txtPriceOnAction(ActionEvent event) {
        txtQty.requestFocus();
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

}
