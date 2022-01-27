package com.gaur.datastore.api.controller;

import com.gaur.datastore.api.model.Value;
import com.gaur.datastore.domain.internal.DataSet;
import com.gaur.datastore.service.RegistrationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 * This controller provides the APIs for external entity to interact with RegistrationService
 */
@Log4j2
@RestController
@RequestMapping("${application.datastore.rest.uribase:/rest}")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PutMapping(value = "/api/v1/values/{key}",
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object registerApplication(@PathVariable(value = "key") String key,
        @RequestBody @Validated Value value) {
        log.debug("Incoming request for create with key: {} and value: {}", key, value);
        registrationService.save(DataSet.builder().key(key).value(value.getValue()).build());
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/api/v1/values/{key}")
    public Object removeApplication(@PathVariable(value = "key") String key) {
        log.debug("Incoming request for delete with key: {}", key);
        registrationService.delete(key);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
