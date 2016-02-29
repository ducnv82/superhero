/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Superhero model.
 *
 * @author duc
 *
 */
public class Superhero {

    private String name;
    private String pseudonym;
    private String publisher;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate appearedDate;

    private List<String> skills;
    private List<String> allies;

    /**
     * Get name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get pseudonym.
     *
     * @return pseudonym
     */
    public String getPseudonym() {
        return pseudonym;
    }

    /**
     * Set pseudonym.
     *
     * @param pseudonym pseudonym
     */
    public void setPseudonym(final String pseudonym) {
        this.pseudonym = pseudonym;
    }

    /**
     * Get publisher.
     *
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Set publisher.
     *
     * @param publisher
     */
    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    /**
     * Get date of first appearance.
     *
     * @return appearedDate
     */
    public LocalDate getAppearedDate() {
        return appearedDate;
    }

    /**
     * Set date of first appearance.
     *
     * @param appearedDate date of first appearance
     */
    public void setAppearedDate(final LocalDate appearedDate) {
        this.appearedDate = appearedDate;
    }

    /**
     * Get skills-or "powers".
     *
     * @return skills
     */
    public List<String> getSkills() {
        return skills;
    }

    /**
     * Set skills-or "powers".
     *
     * @param skills
     */
    public void setSkills(final List<String> skills) {
        this.skills = skills;
    }

    /**
     * Get allies.
     *
     * @return allies
     */
    public List<String> getAllies() {
        return allies;
    }

    /**
     * Set allies.
     *
     * @param allies allies
     */
    public void setAllies(final List<String> allies) {
        this.allies = allies;
    }

}
