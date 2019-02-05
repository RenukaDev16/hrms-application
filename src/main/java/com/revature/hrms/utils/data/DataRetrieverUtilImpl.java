package com.revature.hrms.utils.data;

import com.revature.hrms.data.exception.DataAccessException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
@SuppressWarnings("unchecked")
public class DataRetrieverUtilImpl {

  @Autowired private SessionFactory sessionFactory;

  /*---------------------------------HQL Methods--------------------------------*/
  public <T> T retrieveObjectByHQL(String queryString) throws DataAccessException {
    T object = null;
    try {
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createQuery(queryString);
        object = (T) query.uniqueResult();
      }
    } catch (Exception exception) {
      String msg = "Error";
      throw new DataAccessException(msg, exception);
    }
    return object;
  }

  public <T> T retrieveObjectByHQL(String queryString, List<QueryParameter<?>> queryParameters)
      throws DataAccessException {
    T object = null;
    try {
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createQuery(queryString);
        setQueryParameters(query, queryParameters);
        object = (T) query.uniqueResult();
      }
    } catch (Exception exception) {
      String msg = "Error";
      throw new DataAccessException(msg, exception);
    }
    return object;
  }

  public <E> List<E> retrieveByHQL(String queryString) throws DataAccessException {
    List<E> objects = null;
    try {
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createQuery(queryString);
        objects = query.list();
      }
    } catch (Exception exception) {
      String msg = "Error";
      throw new DataAccessException(msg, exception);
    }
    return objects;
  }

  public <E> List<E> retrieveByHQL(String queryString, List<QueryParameter<?>> queryParameters)
      throws DataAccessException {
    List<E> objects = null;
    try {
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createQuery(queryString);
        setQueryParameters(query, queryParameters);
        objects = query.list();
      }
    } catch (Exception exception) {
      String msg = "Error";
      throw new DataAccessException(msg, exception);
    }
    return objects;
  }

  public <E> List<E> retrieveByHQL(String queryString, QueryProperties queryProperties)
      throws DataAccessException {
    List<E> objects = null;
    try {
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createQuery(queryString);
        setQueryProperties(queryProperties, query);
        objects = query.list();
      }
    } catch (Exception exception) {
      String msg = "Error";
      throw new DataAccessException(msg, exception);
    }
    return objects;
  }

  public <E> List<E> retrieveByHQL(
      String queryString, List<QueryParameter<?>> queryParameters, QueryProperties queryProperties)
      throws DataAccessException {
    List<E> objects = null;
    try {
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createQuery(queryString);
        setQueryParameters(query, queryParameters);
        setQueryProperties(queryProperties, query);
        objects = query.list();
      }
    } catch (Exception exception) {
      String msg = "Error";
      throw new DataAccessException(msg, exception);
    }
    return objects;
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

  private void setQueryProperties(QueryProperties queryProperties, Query query) {
    if (queryProperties != null) {
      if (queryProperties.getFirstResult() != null) {
        query.setFirstResult(queryProperties.getFirstResult());
      }
      if (queryProperties.getMaxResults() != null) {
        query.setMaxResults(queryProperties.getMaxResults());
      }
      if (queryProperties.getFetchSize() != null) {
        query.setFetchSize(queryProperties.getFetchSize());
      }
    }
  }

  /*---------------------------------SQL Methods--------------------------------*/
  public <E> List<E> retrieveBySQL(String queryString) throws DataAccessException {
    List<E> objects = null;
    try {
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createSQLQuery(queryString);
        objects = query.list();
      }
    } catch (Exception exception) {
      String msg = "Error";
      throw new DataAccessException(msg, exception);
    }
    return objects;
  }

  public <E> List<E> retrieveBySQL(String queryString, List<QueryParameter<?>> queryParameters)
      throws DataAccessException {
    List<E> objects = null;
    try {
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createSQLQuery(queryString);
        for (QueryParameter<?> queryParameter : queryParameters) {
          if (queryParameter.getValue() != null
              && (queryParameter.getValue().getClass().equals(List.class)
                  || queryParameter.getValue().getClass().equals(ArrayList.class))) {
            List<?> parameter = (List<?>) queryParameter.getValue();
            query.setParameterList(queryParameter.getName(), parameter);
          } else {
            query.setParameter(queryParameter.getName(), queryParameter.getValue());
          }
        }
        objects = query.list();
      }

    } catch (Exception exception) {
      String msg = "Error";
      throw new DataAccessException(msg, exception);
    }
    return objects;
  }

  public <E> List<E> retrieveBySQL(String queryString, QueryProperties queryProperties)
      throws DataAccessException {
    List<E> objects = null;
    try {
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createSQLQuery(queryString);
        setQueryProperties(queryProperties, query);
        objects = query.list();
      }
    } catch (Exception exception) {
      String msg = "Error";
      throw new DataAccessException(msg, exception);
    }
    return objects;
  }

  public <E> List<E> retrieveBySQL(
      String queryString, List<QueryParameter<?>> queryParameters, QueryProperties queryProperties)
      throws DataAccessException {
    List<E> objects = null;
    try {
      Session session = sessionFactory.getCurrentSession();
      if (queryString != null) {
        Query query = session.createSQLQuery(queryString);
        for (QueryParameter<?> queryParameter : queryParameters) {
          if (queryParameter.getValue().getClass().equals(List.class)
              || queryParameter.getValue().getClass().equals(ArrayList.class)) {
            List<?> parameter = (List<?>) queryParameter.getValue();
            query.setParameterList(queryParameter.getName(), parameter);
          } else {
            query.setParameter(queryParameter.getName(), queryParameter.getValue());
          }
        }
        setQueryProperties(queryProperties, query);
        objects = query.list();
      }
    } catch (Exception exception) {
      String msg = "Error";
      throw new DataAccessException(msg, exception);
    }
    return objects;
  }

  public <E> List<E> retrieveBySQLResultTransformer(String query, Class<?> t)
      throws DataAccessException {
    List<E> list = null;
    try {
      Session session = sessionFactory.getCurrentSession();
      if (query != null) {
        list =
            session.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(t)).list();
      }
    } catch (Exception exception) {
      String msg = "Error";
      throw new DataAccessException(msg, exception);
    }
    return list;
  }
}
