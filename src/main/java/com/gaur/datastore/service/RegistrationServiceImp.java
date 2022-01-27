package com.gaur.datastore.service;

import com.gaur.datastore.domain.internal.DataSet;
import com.gaur.datastore.repository.register.RegistrationRepository;
import org.springframework.stereotype.Service;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 * This service provides the registration and removal of keys from the database.
 */
@Service
public class RegistrationServiceImp implements RegistrationService {


    RegistrationRepository registrationRepository;

    RegistrationServiceImp(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public DataSet save(DataSet dataSet) {
        return registrationRepository.save(dataSet);
    }

    @Override
    public boolean delete(String key) {
        return registrationRepository.delete(key);
    }

}
