package com.safety.car.utils.constants;

public class Constants {
    //USER SERVICE
    public static final String USER_EMAIL_EXISTS = "User with email: %s, already exists";
    public static final String EMAIL_CHANGE_REJECTION = "Cannot change email of the user!";
    public static final String DRIVER_AGE_ERROR = "Drivers age is %d, it should be between 18 and 65!";

    //USER REPO
    public static final String USER_NOT_FOUND_ID = "User with id: %d, was not found!";
    public static final String PREMIUM_ID_ERROR = "No such premium values with id %d";
    public static final String PREMIUM_ERROR = "There are no premium values in the system";
    public static final String USER_NOT_FOUND_EMAIL = "User with email: %s, was not found!";

    public static final String ANONYMOUS_USER_CONTROLLER = "Guest user";
    public static final String USER_NOT_FOUND_CONTROLLER = "User not found";

    //MODEL
    public static final String MODEL_EMPTY_ERROR = "There are no models!";
    public static final String POLICY_EMPTY_ERROR = "There are no policies!";
    public static final String MODEL_ID_ERROR = "Model with id %d not found!";
    public static final String POLICY_ID_ERROR = "There is not policy with id %d!";
    public static final String MODEL_BRAND_ID_ERROR = "There are no models with brand id %d!";
    public static final String CRITERIA_TABLE_EMPTY_ERROR = "The estimated price cannot be calculated by your criterion";

    //CAR
    public static final String GUEST_USER = "Guest User";
    public static final String CAR_ID_NOT_FOUND = "Car with id %d not found!";
    public static final String ESTIMATED_PRICE_REST = "The estimated price is %.2f";
    public static final String CAR_WITH_BRAND_UPDATE = "Car with brand %s updated!";
    public static final String CAR_WITH_BRAND_CREATED = "Car with brand %s created!";
    public static final String POLICY_ID_NOT_FOUND = "Policy with id: %d, was not found!";
    public static final String CRITERIA_ERROR = "Nothing looks the same or similar as your criterias.";
    public static final String FILL_FORM_POLICY = "Please fill the form before continuing to requesting policy";

    //ADDRESS
    public static final String ADDRESS_NOT_FOUND = "Address with name: %s, was not found!";
    public static final String ADDRESS_ID_NOT_FOUND = "Address with id: %s, was not found!";

    //MVC REGISTER
    public static final String INVALID_LINK = "The link is invalid or broken";
    public static final String REGISTER_PASSWORD_NOT_MATCH = "Passwords do not match, please try again.";
    public static final String SUCCESSFULLY_CONFIRMED_ACCOUNT = "You have successfully confirmed your account!";
    public static final String REGISTER_USER_EMAIL_ERROR = "User with the same Email already exists, please try again.";

    //VERIFICATION
    public static final String TOKEN_NOT_VALID = "Token not valid";
}