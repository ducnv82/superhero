/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.model;

/**
 * SuperheroResponse.
 *
 * @author duc
 */
public class SuperheroResponse {

    private Superhero superhero;

    private SuperheroResponse() {
        //For framework to create instance
    }

    /**
     * Constructor.
     *
     * @param superhero a superhero.
     */
    public SuperheroResponse(final Superhero superhero) {
        this.superhero = superhero;
    }

    /**
     * Get superhero
     *
     * @return superhero
     */
    public Superhero getSuperhero() {
        return superhero;
    }
}
