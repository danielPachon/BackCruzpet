package com.cruzpet.security.dto;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.UserList;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequestMapping("/v1.0/oauth")
@CrossOrigin
public class OauthController {

    @Value("${google.clientId}")
    private String googleClientId;

    @PostMapping("/google")
    public ResponseEntity<?> google(@RequestBody TokenDtoSocials tokenDtoSocials) throws IOException {

        final NetHttpTransport netHttpTransport = new NetHttpTransport();
        final GsonFactory gsonFactory = GsonFactory.getDefaultInstance();

        GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(netHttpTransport, gsonFactory)
                .setAudience(Collections.singletonList(googleClientId));

        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDtoSocials.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @PostMapping("/facebook")
    public ResponseEntity<?> facebook(@RequestBody TokenDtoSocials tokenDtoSocials) throws IOException {

        Facebook facebook = new FacebookTemplate(tokenDtoSocials.getValue());
        final String[] fields = {"email", "picture", "last_name", "first_name"};
        User user = facebook.fetchObject("me", User.class, fields);
        return new ResponseEntity(user, HttpStatus.OK);

    }

    @PostMapping("/twitter")
    public ResponseEntity<?> twitter(@RequestBody TokenDtoSocials tokenDtoSocials) throws IOException {

        Twitter twitter = new TwitterTemplate(tokenDtoSocials.getValue());
        TwitterProfile profile = twitter.userOperations().getUserProfile();
        return new ResponseEntity(profile, HttpStatus.OK);

    }


}
