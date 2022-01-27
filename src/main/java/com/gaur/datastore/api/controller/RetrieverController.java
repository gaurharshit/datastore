package com.gaur.datastore.api.controller;

import com.gaur.datastore.api.model.Value;
import com.gaur.datastore.service.RetrieverService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 * This controller provides the APIs for external entity to interact with RetrieverService
 */
@RestController
@Log4j2
@RequestMapping("${application.datastore.rest.uribase:/rest}")
public class RetrieverController {

    private RetrieverService retrieverService;

    public RetrieverController(RetrieverService retrieverService) {
        this.retrieverService = retrieverService;
    }

    @GetMapping(value = "/api/v1/values/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Value getValueByKey(@PathVariable (value="key") String key) {
        log.debug("Incoming request for get with key: {}", key);
        return retrieverService.getValueFromKey(key)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No value with key = " + key));
    }


}
