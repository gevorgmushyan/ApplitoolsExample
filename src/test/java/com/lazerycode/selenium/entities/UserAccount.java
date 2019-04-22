package com.lazerycode.selenium.entities;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.*;
import java.util.Random;

@Data
public class UserAccount {
    private String userName;
    private String email;
    private String password;

    private static UserAccount getUserAccount() {
        UserAccount user = new UserAccount();

        user.userName = "Gevorg";
        user.email = "test" + Math.abs(new Random().nextInt()) + "@yopmail.com";
        user.password = "123456";
        return user;
    }

    private static URI getUrlParameters(UserAccount user) {

        URI uri = UriComponentsBuilder
                .fromUriString("https://ancient-taiga-22967.herokuapp.com/register.do")
                .queryParam("name", user.getUserName())
                .queryParam("email", user.getEmail())
                .queryParam("password", user.getPassword())
                .queryParam("confirmationPassword", user.getPassword())
                .build().toUri();
        return uri;
    }

    public static UserAccount getRegisteredUserAccount() {
        UserAccount user = getUserAccount();

        HttpComponentsClientHttpRequestFactory factory;
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        factory = new HttpComponentsClientHttpRequestFactory();
        template.setRequestFactory(factory);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        URI uri = getUrlParameters(user);

        try {
            template.postForEntity(uri, "",  String.class);
            return user;
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        } catch (HttpServerErrorException e1) {
            System.out.println(" pet plan server not responded");
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(getRegisteredUserAccount());
    }
}
