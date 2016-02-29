/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.service;

import javax.validation.Valid;

import com.mycompany.superhero.model.SuperheroResponse;
import com.mycompany.superhero.model.SuperheroesResponse;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Super hero service.
 *
 * @author duc
 *
 */
public interface SuperheroService {

    /**
     * Find all super heroes.
     *
     * @return a {@link SuperheroesResponse}
     */
    SuperheroesResponse findAll();

    /**
     * Find by pseudonym.
     *
     * @param pseudonym pseudonym like "Batman", "Superman"
     * @return a {@link SuperheroResponse}
     */
    SuperheroResponse findByPseudonym(@Valid @NotBlank String pseudonym);
}
