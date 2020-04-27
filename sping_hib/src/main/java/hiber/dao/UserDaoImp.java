package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCar(String name, int series) {
        try {
            TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where car.series =:series and car.name =:name").
                    setParameter("name", name).
                    setParameter("series", series);
            return query.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
