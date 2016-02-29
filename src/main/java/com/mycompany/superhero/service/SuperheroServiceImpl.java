/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.service;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.mycompany.superhero.config.Config;
import com.mycompany.superhero.model.Superhero;
import com.mycompany.superhero.model.SuperheroResponse;
import com.mycompany.superhero.model.SuperheroesResponse;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * {@link SuperheroService} implementation.
 *
 * @author duc
 *
 */
@Service
@Validated
public class SuperheroServiceImpl implements SuperheroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SuperheroServiceImpl.class);

    /**
     * Injected by {@link Config#superHeroes()} and mocked in unit tests.
     */
    @Resource
    private List<Superhero> superHeroes;

    @Override
    public SuperheroesResponse findAll() {
        return new SuperheroesResponse(superHeroes);
    }

    @Override
    public SuperheroResponse findByPseudonym(@Valid @NotBlank final String pseudonym) {
        LOGGER.debug("pseudonym: {}", pseudonym);

        Superhero foundSuperhero = null;

        for (final Superhero superhero : superHeroes) {
            if (pseudonym.equals(superhero.getPseudonym())) {
                foundSuperhero = superhero;
                break;
            }
        }

        return new SuperheroResponse(foundSuperhero);
    }
}
