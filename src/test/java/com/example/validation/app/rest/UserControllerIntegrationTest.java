package com.example.validation.app.rest;

import com.example.validation.app.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2019-03-11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void health() {
        assertThat(restTemplate
                        .getForEntity(
                                createURLWithPort("/users"),
                                null)
                        .getStatusCode(),
                is(HttpStatus.OK));
    }

    @Test
    public void register_success() {
//        given
        var user = User.builder()
                .name("name")
                .build();

//        when
        ResponseEntity<User> response = restTemplate
                .postForEntity(
                        createURLWithPort("/users/register"),
                        user,
                        User.class);

//        then
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(user));
    }

    @Test
    public void register_failure() {
//        given
        var user = User.builder()
                .name("%%%")
                .build();

//        when
        ResponseEntity<User> response = restTemplate
                .postForEntity(
                        createURLWithPort("/users/register"),
                        user,
                        User.class);

//        then
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
