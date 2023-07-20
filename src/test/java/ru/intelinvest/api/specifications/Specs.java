package ru.intelinvest.api.specifications;

import ru.intelinvest.api.authorization.AuthorizationApi;
import io.restassured.specification.RequestSpecification;
import ru.intelinvest.helpers.CustomAllureListener;

import static io.restassured.RestAssured.with;

public class Specs {
    public static RequestSpecification requestPostSpec = with()
            .header("Authorization", "Bearer " + AuthorizationApi.authInfo.getToken())
            .contentType("application/json;charset=UTF-8")
            .log().all()
            .filter(CustomAllureListener.withCustomTemplates());
    public static RequestSpecification requestGetSpec = with()
            .header("Authorization", "Bearer " + AuthorizationApi.authInfo.getToken())
            .log().all()
            .filter(CustomAllureListener.withCustomTemplates());

}