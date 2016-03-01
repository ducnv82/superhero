/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.service;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.mycompany.superhero.SuperheroApplication;
import com.mycompany.superhero.controller.SuperheroController;
import com.mycompany.superhero.model.Superhero;
import com.mycompany.superhero.model.SuperheroResponse;
import com.mycompany.superhero.model.SuperheroesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.time.LocalDate.of;
import static javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier;
import static javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory;
import static javax.net.ssl.SSLContext.getInstance;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

/**
 * Integration tests for RESTful APIs in {@link SuperheroController}.
 *
 * @author duc
 *
 */
@SpringApplicationConfiguration(classes = SuperheroApplication.class)
@WebIntegrationTest
public class SuperheroIControllerIT extends AbstractTestNGSpringContextTests {

    @Value("${security.user.name}")
    private String username;

    @Value("${security.user.password}")
    private String password;

    @Value("${server.port}")
    private int serverPort;

    private RestTemplate restTemplate;

    private static final TrustManager [] UNQUESTIONING_TRUST_MANAGER = new TrustManager[] { new X509TrustManager() {
        @Override
        public X509Certificate [] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(final X509Certificate[] certs, final String authType) {
            //EMPTY
        }

        @Override
        public void checkServerTrusted(final X509Certificate[] certs, final String authType) {
            //EMPTY
        }
    }};

    static {
        // for localhost testing only
        setDefaultHostnameVerifier((hostname, sslSession) -> {
            if ("localhost".equals(hostname)) {
                return true;
            }
            return false;
        });

        // Install the all-trusting trust manager
        try {
            final SSLContext sc = getInstance("SSL");
            sc.init( null, UNQUESTIONING_TRUST_MANAGER, null);
            setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeMethod
    public void setUp() {
        restTemplate = new TestRestTemplate(username, password);
    }

    @Test
    public void testFindAll() {
        final List<Superhero> superheroes = restTemplate.getForObject("https://localhost:" + serverPort + "/superheroes",
                                                                            SuperheroesResponse.class).getSuperheroes();
        assertThat(superheroes.size(), equalTo(2));

        final Superhero batman = superheroes.get(0);
        assertBatman(batman);

        final Superhero superman = superheroes.get(1);
        assertSuperman(superman);
    }

    @Test
    public void testFindByPseudonym() {
        final Superhero batman =
                restTemplate.getForObject("https://localhost:" + serverPort + "/superheroes/superhero/pseudonym/Batman",
                                                                                SuperheroResponse.class).getSuperhero();
        assertBatman(batman);

        final Superhero superman = restTemplate
                .getForObject("https://localhost:" + serverPort + "/superheroes/superhero/pseudonym/Superman",
                                                                                SuperheroResponse.class)
                .getSuperhero();

        assertSuperman(superman);
    }

    @Test
    public void testFindByPseudonym_notFound() {
        final Superhero nonexistentHero = restTemplate
                .getForObject("https://localhost:" + serverPort + "/superheroes/superhero/pseudonym/nonExistence",
                                                                                SuperheroResponse.class)
                .getSuperhero();

        assertThat(nonexistentHero, nullValue());
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