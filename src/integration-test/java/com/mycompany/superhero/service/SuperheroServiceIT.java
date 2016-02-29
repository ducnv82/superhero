/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.service;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import com.mycompany.superhero.SuperheroApplication;
import com.mycompany.superhero.model.Superhero;
import com.mycompany.superhero.service.SuperheroService;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static java.time.LocalDate.of;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

/**
 * Integration tests for {@link SuperheroService}.
 *
 * @author duc
 *
 */
@SpringApplicationConfiguration(classes = SuperheroApplication.class)
public class SuperheroServiceIT extends AbstractTestNGSpringContextTests  {

    @Resource
    private SuperheroService superheroService;

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testFindByPseudonym_blankPseudonym() {
        superheroService.findByPseudonym(" ");
    }

    @Test
    public void testFindByPseudonym() {
        final Superhero superhero = superheroService.findByPseudonym("Batman").getSuperhero();
        assertBatman(superhero);
    }

    @Test
    public void testFindAll() {
        final List<Superhero> allSuperheroes = superheroService.findAll().getSuperheroes();

        assertThat(allSuperheroes.size(), equalTo(2));
        assertBatman(allSuperheroes.get(0));
        assertSuperman(allSuperheroes.get(1));
    }

    private void assertBatman(final Superhero batman) {
        assertThat(batman.getPseudonym(), equalTo("Batman"));
        assertThat(batman.getName(), equalTo("Bruce Wayne"));
        assertThat(batman.getPublisher(), equalTo("DC Comics"));
        assertThat(batman.getAllies(), contains("Robin", "Alfred Pennyworth", "Jim Gordon", "Barbara Gordon"));
        assertThat(batman.getSkills(), contains("Scientific knowledge", "Detective ability", "Athletic prowess"));
        assertThat(batman.getAppearedDate(), equalTo(of(1939, 5, 27)));
    }

    private void assertSuperman(final Superhero superman) {
        assertThat(superman.getPseudonym(), equalTo("Superman"));
        assertThat(superman.getName(), equalTo("Clark Ken"));
        assertThat(superman.getPublisher(), equalTo("DC Comics"));
        assertThat(superman.getAllies(), contains("Supergirl", "Lar Gand", "Steel", "Kal Kent", "Beppo",
                                                                                               "Streaky the Supercat"));
        assertThat(superman.getSkills(), contains("Solar energy absorption", "Solar flare", "Decelerated aging",
                                                                                                             "Flight"));
        assertThat(superman.getAppearedDate(), equalTo(of(1938, 4, 18)));
    }
}
