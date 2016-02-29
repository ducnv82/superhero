/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.service;

import java.util.List;

import com.mycompany.superhero.model.Superhero;
import com.mycompany.superhero.service.SuperheroServiceImpl;
import mockit.Injectable;
import mockit.Tested;
import org.testng.annotations.Test;

import static java.time.LocalDate.of;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.nullValue;

public class SuperheroServiceImplTest {

    @Tested
    private SuperheroServiceImpl superheroServiceImpl;

    @Injectable
    private List<Superhero> superheroes = createSuperheroes();

    @Test
    public void testFindAll() {
        final List<Superhero> allSuperheroes = superheroServiceImpl.findAll().getSuperheroes();

        assertThat(allSuperheroes.size(), equalTo(1));
        assertRobin(allSuperheroes.get(0));
    }

    @Test
    public void testFindByPseudonym() {
        final Superhero robin = superheroServiceImpl.findByPseudonym("Robin").getSuperhero();

        assertRobin(robin);
    }

    @Test
    public void testFindByPseudonym_notFound() {
        assertThat(superheroServiceImpl.findByPseudonym("Batman").getSuperhero(), nullValue());
    }

    private List<Superhero> createSuperheroes() {
        final Superhero robin = new Superhero();
        robin.setName("Dick Grayson");
        robin.setPublisher("DC Comics");
        robin.setPseudonym("Robin");
        robin.setSkills(asList("Scientific knowledge", "Detective ability", "Athletic prowess"));
        robin.setAppearedDate(of(1939, 5, 27));
        robin.setAllies(asList("Batman", "Alfred Pennyworth", "Jim Gordon", "Barbara Gordon"));

        return asList(robin);
    }

    private void assertRobin(final Superhero robin) {
        assertThat(robin.getPseudonym(), equalTo("Robin"));
        assertThat(robin.getName(), equalTo("Dick Grayson"));
        assertThat(robin.getPublisher(), equalTo("DC Comics"));
        assertThat(robin.getAllies(), contains("Batman", "Alfred Pennyworth", "Jim Gordon", "Barbara Gordon"));
        assertThat(robin.getSkills(), contains("Scientific knowledge", "Detective ability", "Athletic prowess"));
        assertThat(robin.getAppearedDate(), equalTo(of(1939, 5, 27)));
    }

}
