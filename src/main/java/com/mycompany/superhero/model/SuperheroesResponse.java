/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.model;

import java.util.List;

/**
 * Superheroes response.
 *
 * @author duc
 */
public class SuperheroesResponse {

    private List<Superhero> superheroes;

    private SuperheroesResponse() {
        //For framework to create instance
    }

    /**
     * Constructor.
     *
     * @param superHeroes a list of {@link Superhero}
     */
    public SuperheroesResponse(final List<Superhero> superHeroes) {
        superheroes = superHeroes;
    }

    /**
     * Get superheroes.
     *
     * @return a list of {@link Superhero}
     */
    public List<Superhero> getSuperheroes() {
        return superheroes;
    }
}
