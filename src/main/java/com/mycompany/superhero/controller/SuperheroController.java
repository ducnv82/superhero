/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.controller;

import javax.annotation.Resource;

import com.mycompany.superhero.model.SuperheroResponse;
import com.mycompany.superhero.model.SuperheroesResponse;
import com.mycompany.superhero.service.SuperheroService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Super hero controller. <br/>
 * It is applied the http basic authentication, to make request to server uses <br/>
 *  curl user:cGFzc3dvcmQ=@localhost:8080/superheroes
 *
 * @author duc
 *
 */
@RestController
@RequestMapping("/superheroes")
public class SuperheroController {

    @Resource
    private SuperheroService superheroService;

    /**
     * Find all super heroes. <br/>
     *
     * It is applied the http basic authentication, to make request to server uses
     * curl user:cGFzc3dvcmQ=@localhost:8080/superheroes
     *
     * @return {@link SuperheroesResponse} object
     */
    @RequestMapping(method = GET)
    public SuperheroesResponse findAll() {
        return superheroService.findAll();
    }

    /**
     * Find by pseudonym. <br/>
     *
     * It is applied the http basic authentication, to make request to server uses
     * curl user:cGFzc3dvcmQ=@localhost:8080/superheroes/superhero/pseudonym/Batman <br/>
     * curl user:cGFzc3dvcmQ=@localhost:8080/superheroes/superhero/pseudonym/Superman <br/>
     *
     * @param pseudonym pseudonym like "Batman", "Superman"
     * @return {@link SuperheroResponse} object
     */
    @RequestMapping(value = "/superhero/pseudonym/{pseudonym}", method = GET)
    public SuperheroResponse findByPseudonym(@PathVariable final String pseudonym) {
        return superheroService.findByPseudonym(pseudonym);
    }
}
