package lk.ijse.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.bo.custom.OrderBO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;
import lk.ijse.entity.OrderItem;
import lk.ijse.tdm.OrderTm;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderFormController {

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colunitPrice;

    @FXML
    private TableView<OrderTm> orderTabel;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtprice;

    OrderBO orderBO = (OrderBO) BOFactory.getBO(BOFactory.BOType.ORDER);

    public void initialize()  {
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colunitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {
        ObservableList<OrderTm> items = orderTabel.getItems();
        items.add(new OrderTm(Integer.parseInt(txtItemId.getText()),txtModel.getText(),Double.parseDouble(txtprice.getText()),Integer.parseInt(txtQty.getText())));
        orderTabel.setItems(items);
        orderTabel.refresh();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        CustomerDTO customerDTO = orderBO.getCustomer(Integer.parseInt(txtCustomerId.getText()));
        Customer customer = new Customer(customerDTO.getCustomerId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getTel(), customerDTO.getOrders());
        OrderDTO orderDTO = new OrderDTO(Integer.parseInt(txtOrderId.getText()), Date.valueOf(LocalDate.now()),getTotal(),customer,getItems(Integer.parseInt(txtOrderId.getText())));

        orderBO.placeOrder(orderDTO,customer);
    }

    private List<OrderItem> getItems(int orderId){
        ObservableList<OrderTm> items = orderTabel.getItems();
        List<OrderItem> itemList = new ArrayList<>();

        int id = 0;
        for (OrderTm orderTm : items) {
            ItemDTO itemDTO = orderBO.getItem(orderTm.getItemId());
            Item item = new Item(itemDTO.getItemId(),itemDTO.getModel(),itemDTO.getUnitPrice(),itemDTO.getQty(),itemDTO.getOrderItems());

            itemList.add(new OrderItem(item,orderTm.getQty()));
            id++;
        }
        return itemList;
    }

    private double getTotal(){
        ObservableList<OrderTm> items = orderTabel.getItems();
        double total = 0;
        for (OrderTm orderTm : items) {
            total += orderTm.getQty() * orderTm.getUnitPrice();
        }
        return total;
    }

    @FXML
    void txtItemIdOnAction(ActionEvent event) {
        ItemDTO itemDTO = orderBO.getItem(Integer.parseInt(txtItemId.getText()));
        txtModel.setText(itemDTO.getModel());
        txtprice.setText(String.valueOf(itemDTO.getUnitPrice()));
        txtQty.setText(String.valueOf(itemDTO.getQty()));
    }

    @FXML
    void txtOrderIdOnAction(ActionEvent event) {
        txtCustomerId.requestFocus();
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddOnAction(event);
    }

}
