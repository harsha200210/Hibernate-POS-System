package lk.ijse.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.OrderViewBO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.OrderItemDTO;
import lk.ijse.entity.Orders;
import lk.ijse.tdm.OrderItemView;
import lk.ijse.tdm.OrderViewTm;

import java.util.List;

public class OrderViewFormController {

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<OrderItemView> tblItemView;

    @FXML
    private TableView<OrderViewTm> tblOrderView;

    OrderViewBO orderViewBO = (OrderViewBO) BOFactory.getBO(BOFactory.BOType.ORDERVIEW);

    public void initialize() {
        setCellValueFactory();
        loadAllData();
    }

    private void loadAllData() {
        List<OrderDTO> allOrders = orderViewBO.getAllOrders();
        tblOrderView.getItems().clear();
        ObservableList<OrderViewTm> orderViewTms = tblOrderView.getItems();
        for (OrderDTO orderDTO : allOrders) {
            orderViewTms.add(new OrderViewTm(orderDTO.getOrderId(),orderDTO.getDate(),orderDTO.getTotal(),orderDTO.getCustomer().getCustomerId(),orderDTO.getCustomer().getName()));
        }
        tblOrderView.setItems(orderViewTms);
    }

    @FXML
    void tblOrderViewAction(MouseEvent event) {
        OrderViewTm orderViewTm = (OrderViewTm) tblOrderView.getSelectionModel().getSelectedItem();
        if (orderViewTm != null) {
            List<OrderItemDTO> orderItemsByOrderId = orderViewBO.getOrderItemsByOrderId(orderViewTm.getOrderId());
            tblItemView.getItems().clear();
            ObservableList<OrderItemView> orderItemViews = tblItemView.getItems();
            for (OrderItemDTO orderItemDTO : orderItemsByOrderId) {
                orderItemViews.add(new OrderItemView(orderItemDTO.getItem().getItemId(),orderItemDTO.getItem().getModel(),orderItemDTO.getQty()));
            }
            tblItemView.setItems(orderItemViews);
        }
    }

    private void setCellValueFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
}
