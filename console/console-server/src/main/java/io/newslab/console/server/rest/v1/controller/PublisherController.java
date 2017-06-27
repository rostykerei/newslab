package io.newslab.console.server.rest.v1.controller;

import io.newslab.console.server.rest.v1.restfultable.RestfulTablePage;
import io.newslab.console.server.rest.v1.restfultable.RestfulTableRequest;
import io.newslab.persistence.model.Publisher;
import io.newslab.persistence.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static io.newslab.console.server.rest.v1.restfultable.RestfulTableTranslator.translate;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherRepository publisherRepository;

    @RequestMapping(method = RequestMethod.GET)
    public RestfulTablePage<Publisher> list(RestfulTableRequest request) {
        return translate(
                publisherRepository.findAll(
                        translate(request)
                )
        );
    }
}
