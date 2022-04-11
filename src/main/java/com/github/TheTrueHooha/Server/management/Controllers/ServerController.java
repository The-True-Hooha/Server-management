package com.github.TheTrueHooha.Server.management.Controllers;

import com.github.TheTrueHooha.Server.management.Model.APIResponse;
import com.github.TheTrueHooha.Server.management.Model.Servers;
import com.github.TheTrueHooha.Server.management.Service.Impli.ServerServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static com.github.TheTrueHooha.Server.management.Enums.ServerStatus.*;
import static java.time.LocalDateTime.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/api/vi")
@RequiredArgsConstructor

public class ServerController {

    private final ServerServiceImplementation serverServiceImplementation;

    //gets all the list of the servers in the database
    @GetMapping("/servers/list")
    public ResponseEntity<APIResponse> getServers() {
        return ResponseEntity.ok(
                APIResponse.builder()
                        .dateTime(now())
                        .data(Map.of("servers", serverServiceImplementation.listServers(25)))
                        .feedbackMessage("servers are being fetched")
                        .httpStatus(OK)
                        .apiStatusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/servers/ping{ipAddress}")
    public ResponseEntity<APIResponse> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Servers servers = serverServiceImplementation.pingServer(ipAddress);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .dateTime(now())
                        .data(Map.of("server", servers))
                        .feedbackMessage(servers.getServerStatus() == SERVER_RUNNING
                                ? "ping successful" : "ping failed")
                        .apiStatusCode(OK.value())
                        .httpStatus(OK)
                        .build()
        );
    }

    @PostMapping("/servers/add-new")
    public ResponseEntity<APIResponse> addNewServer(@RequestBody @Valid Servers servers){
        return ResponseEntity.ok(
                APIResponse.builder()
                        .dateTime(now())
                        .data(Map.of("server", serverServiceImplementation.createServer(servers)))
                        .feedbackMessage("your server has successfully being created")
                        .apiStatusCode(CREATED.value())
                        .httpStatus(CREATED)
                        .build()

        );
    }

    @GetMapping("/servers/get/{id}")
    public ResponseEntity<APIResponse> getServerById (@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                APIResponse.builder()
                        .data(Map.of("server", serverServiceImplementation.getServers(id)))
                        .dateTime(now())
                        .feedbackMessage("server has been fetched successfully")
                        .apiStatusCode(OK.value())
                        .httpStatus(OK)
                        .build()
        );
    }

    @DeleteMapping("/server/delete/{id}")
    public ResponseEntity<APIResponse> deleteServerById(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                APIResponse.builder()
                        .httpStatus(OK)
                        .data(Map.of("server deleted with id", serverServiceImplementation.deleteServer(id)))
                        .dateTime(now())
                        .feedbackMessage("server has successfully been deleted")
                        .apiStatusCode(OK.value())
                        .build()
        );
    }

    @GetMapping(path = "/servers/image/{filename}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerByImage(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "Downloads/server/images/" + filename));
    }
}
