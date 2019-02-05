package com.revature.hrms.utils.data;

import com.revature.hrms.data.exception.DataAccessException;
import com.revature.hrms.data.exception.DataOperationFailedException;
import com.revature.hrms.data.exception.DuplicateRecordException;
import com.revature.hrms.data.exception.ReferentialIntegrityException;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(
  propagation = Propagation.REQUIRED,
  readOnly = false,
  rollbackFor = DataAccessException.class
)
public class DataModifierUtilImpl {
  @Autowired private SessionFactory sessionFactory;

  public <T> void insert(T type) throws DataAccessException {
    try {
      Session session = sessionFactory.getCurrentSession();
      session.save(type);
      session.flush();
    } catch (ConstraintViolationException cvException) {
      throw new DuplicateRecordException(cvException.getMessage(), cvException);
    } catch (HibernateException hibernateException) {
      throw new DataOperationFailedException(hibernateException.getMessage(), hibernateException);
    } catch (Exception exception) {
      throw new DataOperationFailedException(exception.getMessage(), exception);
    }
  }

  public <E> void insertBulk(List<E> objects) throws DataAccessException {
    try {
      Session session = sessionFactory.getCurrentSession();
      for (int i = 0; i < objects.size(); i++) {
        Object type = objects.get(i);
        session.save(type);
        if (i % 20 == 0) {
          session.flush();
          session.clear();
        }
      }
      session.flush();
    } catch (ConstraintViolationException cvException) {
      throw new DuplicateRecordException(cvException.getMessage(), cvException);
    } catch (HibernateException hibernateException) {
      throw new DataOperationFailedException(hibernateException.getMessage(), hibernateException);
    } catch (Exception exception) {
      throw new DataOperationFailedException(exception.getMessage(), exception);
    }
  }

  public <T> void update(T type) throws DataAccessException {
    try {
      Session session = sessionFactory.getCurrentSession();
      session.clear();
      session.saveOrUpdate(type);
      session.flush();
    } catch (ConstraintViolationException cvException) {
      throw new DuplicateRecordException(cvException.getMessage(), cvException);
    } catch (HibernateException hibernateException) {
      throw new DataOperationFailedException(hibernateException.getMessage());
    } catch (Exception exception) {
      throw new DataOperationFailedException(exception.getMessage(), exception);
    }
  }

  public <E> void updateBulk(List<E> objects) throws DataAccessException {
    try {
      Session session = sessionFactory.getCurrentSession();
      for (int i = 0; i < objects.size(); i++) {
        Object type = objects.get(i);
        session.saveOrUpdate(type);
        if (i % 20 == 0) {
          session.flush();
          session.clear();
        }
      }
      session.flush();
    } catch (ConstraintViolationException cvException) {
      throw new DuplicateRecordException(cvException.getMessage(), cvException);
    } catch (HibernateException hibernateException) {
      throw new DataOperationFailedException(hibernateException.getMessage(), hibernateException);
    } catch (Exception exception) {
      throw new DataOperationFailedException(exception.getMessage(), exception);
    }
  }

  public <T> void delete(T type) throws DataAccessException {
    try {
      Session session = sessionFactory.getCurrentSession();
      session.delete(type);
      session.flush();
    } catch (ConstraintViolationException cvException) {
      throw new ReferentialIntegrityException(cvException.getMessage(), cvException);
    } catch (HibernateException hibernateException) {
      throw new DataOperationFailedException(hibernateException.getMessage(), hibernateException);
    } catch (Exception exception) {
      throw new DataOperationFailedException(exception.getMessage(), exception);
    }
  }

  public <E> void deleteBulk(List<E> objects) throws DataAccessException {
    try {
      Session session = sessionFactory.getCurrentSession();
      for (int i = 0; i < objects.size(); i++) {
        Object type = objects.get(i);
        session.delete(type);
        if (i % 20 == 0) {
          session.flush();
          session.clear();
        }
      }
      session.flush();
    } catch (ConstraintViolationException cvException) {
      throw new ReferentialIntegrityException(cvException.getMessage(), cvException);
    } catch (HibernateException hibernateException) {
      throw new DataOperationFailedException(hibernateException.getMessage(), hibernateException);
    } catch (Exception exception) {
      throw new DataOperationFailedException(exception.getMessage(), exception);
    }
  }

  public <T> void merge(T type) throws DataAccessException {
    try {
      Session session = sessionFactory.getCurrentSession();
      session.clear();
      session.merge(type);
      session.flush();
    } catch (ConstraintViolationException cvException) {
      throw new DuplicateRecordException(cvException.getMessage(), cvException);
    } catch (HibernateException hibernateException) {
      throw new DataOperationFailedException(hibernateException.getMessage(), hibernateException);
    } catch (Exception exception) {
      throw new DataOperationFailedException(exception.getMessage(), exception);
    }
  }

  public <E> void mergeBulk(List<E> objects) throws DataAccessException {
    try {
      Session session = sessionFactory.getCurrentSession();
      for (int i = 0; i < objects.size(); i++) {
        Object type = objects.get(i);
        session.merge(type);
        if (i % 20 == 0) {
          session.flush();
          session.clear();
        }
      }
      session.flush();
    } catch (ConstraintViolationException cvException) {
      throw new DuplicateRecordException(cvException.getMessage(), cvException);
    } catch (HibernateException hibernateException) {
      throw new DataOperationFailedException(hibernateException.getMessage(), hibernateException);
    } catch (Exception exception) {
      throw new DataOperationFailedException(exception.getMessage(), exception);
    }
  }

  public Integer executeQuery(String queryString) throws DataAccessException {
    Integer noOfRowsUpdated = 0;
    try {
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createQuery(queryString);
        noOfRowsUpdated = query.executeUpdate();
        session.flush();
      }
      return noOfRowsUpdated;
    } catch (ConstraintViolationException cvException) {
      throw new ReferentialIntegrityException(cvException.getMessage(), cvException);
    } catch (HibernateException hibernateException) {
      throw new DataOperationFailedException(hibernateException.getMessage(), hibernateException);
    } catch (Exception exception) {
      throw new DataOperationFailedException(exception.getMessage(), exception);
    }
  }

  public Integer executeQuery(String queryString, List<QueryParameter<?>> queryParameters)
      throws DataAccessException {
    Integer noOfRowsUpdated = 0;
    try {
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createQuery(queryString);
        setQueryParameters(query, queryParameters);
        noOfRowsUpdated = query.executeUpdate();
      }
      return noOfRowsUpdated;
    } catch (ConstraintViolationException cvException) {
      throw new ReferentialIntegrityException(cvException.getMessage(), cvException);
    } catch (HibernateException hibernateException) {
      throw new DataOperationFailedException(hibernateException.getMessage(), hibernateException);
    } catch (Exception exception) {
      throw new DataOperationFailedException(exception.getMessage(), exception);
    }
  }

  public void executeSQLQuery(String queryString) throws DataAccessException {
    try {
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createSQLQuery(queryString);
        query.executeUpdate();
      }
    } catch (HibernateException hibernateException) {
      throw new DataOperationFailedException(hibernateException.getMessage(), hibernateException);
    } catch (Exception exception) {
      throw new DataOperationFailedException(exception.getMessage(), exception);
    }
  }

  public Integer executeSQLQuery(String queryString, List<QueryParameter<?>> queryParameters)
      throws DataAccessException {
    try {
      Integer noOfRowsUpdated = 0;
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createSQLQuery(queryString);
        setQueryParameters(query, queryParameters);
        noOfRowsUpdated = query.executeUpdate();
      }
      return noOfRowsUpdated;
    } catch (ConstraintViolationException cvException) {
      throw new ReferentialIntegrityException(cvException.getMessage(), cvException);
    } catch (HibernateException hibernateException) {
      throw new DataOperationFailedException(hibernateException.getMessage());
    } catch (Exception exception) {
      throw new DataOperationFailedException(exception.getMessage());
    }
  }

  public <T> T refreshObject(T type) throws DataOperationFailedException {
    try {
      Session session = sessionFactory.getCurrentSession();
      session.refresh(type);
    } catch (Exception exception) {
      throw new DataOperationFailedException(exception.getMessage(), exception);
    }
    return type;
  }

  private void setQueryParameters(Query query, Collection<QueryParameter<?>> parameters) {
    for (QueryParameter<?> parameter : parameters) {
      String name = parameter.getName();
      Object value = parameter.getValue();
      if (value instanceof Collection) {
        query.setParameterList(name, (Collection<?>) value);
      } else {
        query.setParameter(name, value);
      }
    }
  }
}
