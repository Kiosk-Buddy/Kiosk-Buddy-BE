package com.kioskbuddy.common.ui;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeapotController {

    @GetMapping("/teapot")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ErrorDetails handleTeapot() {
        return new ErrorDetails(418, "I'm a teapot. The server refuses to make coffee in the teapot.");
    }
}
