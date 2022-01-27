package com.gaur.datastore.service;

import com.gaur.datastore.api.model.Value;
import java.util.Optional;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 * This interface is intended to handle the getter for value from key. This can also be
 * extended for further use.
 */
public interface RetrieverService {

    Optional<Value> getValueFromKey(String key);
}
