package com.gaur.datastore.service;

import com.gaur.datastore.domain.internal.DataSet;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 * This interface is intended to provide registration and removal of keys from the database.
 */
public interface RegistrationService {

    DataSet save(DataSet dataSet);

    boolean delete(String key);

}
