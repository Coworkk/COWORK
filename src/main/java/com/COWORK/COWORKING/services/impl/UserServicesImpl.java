package com.COWORK.COWORKING.services.impl;


import com.COWORK.COWORKING.dtos.requests.LogInRequest;
import com.COWORK.COWORKING.dtos.requests.RefreshTokenRequest;
import com.COWORK.COWORKING.dtos.requests.RoleResponse;
import com.COWORK.COWORKING.dtos.requests.UserRequest;
import com.COWORK.COWORKING.dtos.responses.LogInResponse;
import com.COWORK.COWORKING.dtos.responses.ResetPasswordResponse;
import com.COWORK.COWORKING.dtos.responses.UserResponse;
import com.COWORK.COWORKING.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.keycloak.representations.idm.CredentialRepresentation.PASSWORD;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServicesImpl implements UserService {
    @Value("${app.keycloak.realm}")
    private  String realm;
    private final Keycloak keycloak;
    private final RestTemplate restTemplate;



    @Override
    public UserResponse createUser(UserRequest userRequest) {
        UserRepresentation userRepresentation = getUserRepresentation(userRequest);
        UsersResource usersResource = getUserResources();
        getUserResources().create(userRepresentation);
        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(userRequest.getUsername(), true);
        UserRepresentation user = userRepresentations.get(0);
        sendVerificationEmail(user.getId());
        return UserResponse.builder().firstName(user.getFirstName()).lastName(user.getLastName())
        .id(user.getId()).username(user.getUsername()).build();
    }




    private UserRepresentation getUserRepresentation(UserRequest userRequest) {
        if(!getUserResources().search(userRequest.getUsername()).isEmpty()) {
            throw  new RuntimeException("user already exist exception");   }
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(userRequest.getFirstName());
        userRepresentation.setLastName(userRequest.getLastName());
        userRepresentation.setUsername(userRequest.getUsername());
        userRepresentation.setEmailVerified(false);
        userRepresentation.setEmail(userRequest.getUsername());
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(userRequest.getPassword());
        credentialRepresentation.setType(PASSWORD);
        userRepresentation.setCredentials(List.of(credentialRepresentation));
        return userRepresentation;
    }



    @Override
    public void sendVerificationEmail(String userId) {
        UsersResource usersResource = getUserResources();
        usersResource.get(userId).sendVerifyEmail();
    }



    @Override
    public ResetPasswordResponse resetPassword(String username){
        UsersResource usersResource = getUserResources();
       List<UserRepresentation> userRepresentations = usersResource.searchByUsername(username,false);
       UserRepresentation userRepresentation = userRepresentations.get(0);
       UserResource resource = usersResource.get(userRepresentation.getId());
       resource.executeActionsEmail(List.of("UPDATE_PASSWORD"));
       return ResetPasswordResponse.builder().message("the link to reset your password has been sent to "+username).build();
    }



    @Override
    public ResponseEntity<?> logIn(LogInRequest logInRequest) {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("client_id", "COWORK");
            formData.add("grant_type", "password");
            formData.add("username", logInRequest.getUsername());
            formData.add("password", logInRequest.getPassword());
            LogInResponse logInResponse = new LogInResponse();
            return sendAuthRequest(formData);


    }
    @Override
    public ResponseEntity<?> refreshToken(RefreshTokenRequest refreshTokenRequest) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id","COWORK" );
        formData.add("grant_type", "refresh_token");
        formData.add("refresh_token", refreshTokenRequest.getRefreshToken());
        return  sendAuthRequest(formData);
    }





      private ResponseEntity<?> sendAuthRequest(MultiValueMap<String, String> formData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(
                "http://localhost:8080/realms/"+realm+"/protocol/openid-connect/token",
                request,
                Map.class
        );

        return ResponseEntity.ok(response.getBody());
    }

    @Override
    public RoleResponse assignRole(String userId, String roleName) {
        UsersResource usersResource = getUserResources();
        RoleRepresentation representation = getRoleResources().get(roleName).toRepresentation();
        usersResource.get(userId).roles().realmLevel().add(Collections.singletonList(representation));
        return RoleResponse.builder().message("role successfully assigned").build();
    }
    private ClientRepresentation getClient() {
        return keycloak.realm(realm).clients().findByClientId("COWORK").get(0);
    }
    private ClientResource getClientResource() {
     return keycloak.realm(realm).clients().get(getClient().getId());
    }

    public RoleResponse addARoleToProject(String roleName) {
            RoleRepresentation representation =new RoleRepresentation();
            representation.setName(roleName);
            getClientResource().roles().create(representation);
            representation = new RoleRepresentation();
            representation.setName("app_"+roleName.toLowerCase());
            getRoleResources().create(representation);
            RoleRepresentation createdClientRole = getClientResource().roles().get(roleName).toRepresentation();
            RoleRepresentation createdRealmRole = getRoleResources().get("app_"+roleName.toLowerCase()).toRepresentation();
            getRoleResources().get("app_"+roleName.toLowerCase()).addComposites(Collections.singletonList(createdClientRole));
            return RoleResponse.builder().message("Role successfully created").build();
    }
    private RolesResource getRoleResources(){return keycloak.realm(realm).roles();}
    private UsersResource getUserResources(){return keycloak.realm(realm).users();}


}
