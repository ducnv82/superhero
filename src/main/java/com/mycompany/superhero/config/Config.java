/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.config;

import java.util.List;

import javax.validation.Validator;

import com.mycompany.superhero.model.Superhero;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import static java.time.LocalDate.of;
import static java.util.Arrays.asList;

/**
 * Configurations.
 *
 * @author duc
 *
 */
@Configuration
public class Config {

    @Bean
    public Validator validator() {
        final LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(validationMessageSource());
        return validatorFactoryBean;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        final MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidator(validator());
        return methodValidationPostProcessor;
    }

    @Bean
    public MessageSource validationMessageSource() {
        final ResourceBundleMessageSource validationMessageSource = new ResourceBundleMessageSource();
        validationMessageSource.setBasename("ValidationMessages");
        validationMessageSource.setDefaultEncoding("UTF-8");

        return validationMessageSource;
    }

    /**
     * Super heroes.
     *
     * @return list of {@link Superhero}
     */
    @Bean
    public List<Superhero> superHeroes() {
        final Superhero batman = new Superhero();
        batman.setName("Bruce Wayne");
        batman.setPublisher("DC Comics");
        batman.setPseudonym("Batman");
        batman.setSkills(asList("Scientific knowledge", "Detective ability", "Athletic prowess"));
        batman.setAppearedDate(of(1939, 5, 27));
        batman.setAllies(asList("Robin", "Alfred Pennyworth", "Jim Gordon", "Barbara Gordon"));

        final Superhero superman = new Superhero();
        superman.setName("Clark Ken");
        superman.setPublisher("DC Comics");
        superman.setPseudonym("Superman");
        superman.setSkills(asList("Solar energy absorption", "Solar flare", "Decelerated aging", "Flight"));
        superman.setAppearedDate(of(1938, 4, 18));
        superman.setAllies(asList("Supergirl", "Lar Gand", "Steel", "Kal Kent", "Beppo", "Streaky the Supercat"));

        return asList(batman, superman);
    }
}
