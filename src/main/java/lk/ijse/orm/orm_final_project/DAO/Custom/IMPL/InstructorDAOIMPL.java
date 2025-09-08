package lk.ijse.orm.orm_final_project.DAO.Custom.IMPL;

import lk.ijse.orm.orm_final_project.Config.FactoryConfiguration;
import lk.ijse.orm.orm_final_project.DAO.Custom.InstructorDAO;
import lk.ijse.orm.orm_final_project.Entity.Instructor;
import lk.ijse.orm.orm_final_project.Entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class InstructorDAOIMPL implements InstructorDAO {
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public boolean delete(String ID) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            Instructor deleteInd = session.get(Instructor.class , ID);


            if(deleteInd == null){
                transaction.rollback();
                return false;
            }

            session.remove(deleteInd);

            transaction.commit();
            System.out.println("DAO true");
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("DAO false");
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean save(Instructor instructor) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            session.persist(instructor);

            transaction.commit();
            System.out.println("DAO true");
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("DAO false");
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(Instructor instructor) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            Instructor updateIns = session.get(Instructor.class , instructor.getID());

            if(updateIns == null){
                transaction.rollback();
                return false;
            }



            updateIns.setName(instructor.getName());
            updateIns.setAvailability(instructor.getAvailability());
            updateIns.setContact(instructor.getContact());
            updateIns.setSpecification(instructor.getSpecification());
            session.merge(updateIns);

            transaction.commit();
            System.out.println("DAO true");
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("DAO false");
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Instructor> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<Instructor> query = session.createQuery(
                    "from Instructor ",
                    Instructor.class
            );
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public String getNextID() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<String> query = session.createQuery(
                    "SELECT i.id FROM Instructor i ORDER BY i.id DESC",
                    String.class
            ).setMaxResults(1);
            List<String> list = query.list();
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Instructor> searchByID(String s) throws SQLException {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            String hql = "FROM Instructor WHERE ID = :id";
            Query<Instructor> query = session.createQuery(hql, Instructor.class);
            query.setParameter("id", s);

            Instructor instructor = query.uniqueResult();
            return Optional.ofNullable(instructor);

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }
}
