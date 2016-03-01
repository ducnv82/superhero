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
 * Super hero controller.
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
     * @return {@link SuperheroesResponse} object
     */
    @RequestMapping(method = GET)
    public SuperheroesResponse findAll() {
        return superheroService.findAll();
    }

    /**
     * Find by pseudonym. <br/>
     *
     * @param pseudonym pseudonym like "Batman", "Superman"
     * @return {@link SuperheroResponse} object
     */
    @RequestMapping(value = "/superhero/pseudonym/{pseudonym}", method = GET)
    public SuperheroResponse findByPseudonym(@PathVariable final String pseudonym) {
        return superheroService.findByPseudonym(pseudonym);
    }
}
