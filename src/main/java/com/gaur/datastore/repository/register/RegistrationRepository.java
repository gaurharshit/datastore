package com.gaur.datastore.repository.register;

import com.gaur.datastore.domain.internal.DataSet;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 */
public interface RegistrationRepository {

    DataSet save(DataSet dataSet);

    boolean delete(String key);

}
