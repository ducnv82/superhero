/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.controller;

import com.mycompany.superhero.controller.SuperheroController;
import com.mycompany.superhero.service.SuperheroService;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import org.testng.annotations.Test;

import static org.springframework.http.HttpMethod.GET;

public class SuperheroControllerTest extends BaseControllerTest {

    @Tested
    private SuperheroController superheroController;

    @Injectable
    private SuperheroService superheroService;

    @Test
    public void testRequestMapping_findAll() throws Exception {
        testRequestMapping(GET, "/superheroes", "findAll");
    }

    @Test
    public void testFindAll() {
        superheroController.findAll();

        new Verifications() {{
            superheroService.findAll();
        }};
    }

    @Test
    public void testRequestMapping_findByPseudonym() throws Exception {
        testRequestMapping(GET, "/superheroes/superhero/pseudonym/Batman", "findByPseudonym");
    }

    @Test
    public void testFindByPseudonym() {
        final String pseudonym = "Superman";

        superheroController.findByPseudonym(pseudonym);

        new Verifications() {{
            superheroService.findByPseudonym(pseudonym);
        }};
    }

    @Override
    protected Object getController() {
        return superheroController;
    }
}
