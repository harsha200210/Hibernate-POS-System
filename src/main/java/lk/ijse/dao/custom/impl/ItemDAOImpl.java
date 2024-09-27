package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public void saveItem(Item item) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(item);

        transaction.commit();
        session.close();
    }

    @Override
    public void updateItem(Item item) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(item);

        transaction.commit();
        session.close();
    }

    @Override
    public void deleteItem(Item item) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(item);

        transaction.commit();
        session.close();
    }

    @Override
    public Item getItem(int itemId) {
        Item item = null;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        item = session.get(Item.class, itemId);

        transaction.commit();
        session.close();

        return item;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = null;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        items = session.createQuery("from Item", Item.class).list();

        transaction.commit();
        session.close();

        return items;
    }
}
