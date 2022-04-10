package com.github.TheTrueHooha.Server.management.Controllers;

import com.github.TheTrueHooha.Server.management.Model.Feedback;
import com.github.TheTrueHooha.Server.management.Service.Impli.ServerServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/vi")
@RequiredArgsConstructor

public class ServerController {

    private final ServerServiceImplementation serverServiceImplementation;

    @GetMapping("/servers/list")
    public ResponseEntity<Feedback> getServers() {
        return ResponseEntity.ok(
                Feedback.builder()
                        .dateTime(LocalDateTime.now())
                        .data(Map.of("servers", serverServiceImplementation.listServers(25)))
                        .feedbackMessage("servers are being retrived")
                        .httpStatus(OK)
                        .apiStatusCode(OK.value())
                        .build()
        );
    }
}
