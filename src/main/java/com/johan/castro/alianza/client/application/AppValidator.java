package com.johan.castro.alianza.client.application;

import com.johan.castro.alianza.client.domain.Client;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

public class AppValidator {

    public static List<String> validate(Client client) {
        Set<ConstraintViolation<Client>> violations = getValidator().validate(client);
        if(!violations.isEmpty()){
            List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).toList();
            return errorMessages;
        }
        return null;
    }

    private static Validator getValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}
