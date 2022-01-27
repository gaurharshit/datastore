package com.gaur.datastore.domain.internal;

import lombok.Builder;
import lombok.Data;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 */
@Data
@Builder
public class DataSet {

    private String key;
    private String value;

}
