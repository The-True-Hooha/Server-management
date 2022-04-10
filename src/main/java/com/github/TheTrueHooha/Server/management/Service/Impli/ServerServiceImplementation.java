package com.github.TheTrueHooha.Server.management.Service.Impli;

import com.github.TheTrueHooha.Server.management.Enums.ServerStatus;
import com.github.TheTrueHooha.Server.management.Model.Servers;
import com.github.TheTrueHooha.Server.management.Repository.ServerRepository;
import com.github.TheTrueHooha.Server.management.Service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j

public class ServerServiceImplementation implements ServerService {

    private final ServerRepository serverRepository;

    //creates a new server and saves to the repo
    @Override
    public Servers createServer(Servers servers) {
        log.info("saving your new server: {}", servers.getServerName());
        servers.setImageUrl(setServerImageUrl());
        return serverRepository.save(servers);
    }

    //method pings the server and return a timeout to show active status
    @Override
    public Servers pingServer(String ipAddress) throws IOException {
        log.info("pinging...IP: {}", ipAddress);
        Servers servers = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        servers.setServerStatus(address.isReachable(10000) ? ServerStatus.SERVER_RUNNING : ServerStatus.SERVER_DOWN);
        serverRepository.save(servers);
        return servers;
    }

    //gets the list of all the servers
    @Override
    public Collection<Servers> listServers(int limit) {
        log.info("fetching your list of servers");
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Servers getServers(Long id) {
        log.info("fetching your server by id: {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Servers updateServer(Servers servers) {
        log.info("updating server: {}", servers.getServerName());
        return serverRepository.save(servers);
    }

    @Override
    public Boolean deleteServer(Long id) {
        log.info("deleting server with id: {]", id);
        serverRepository.deleteById(id);
        return TRUE;
    }

    //TODO: download server pictures to set as the url
    private String setServerImageUrl() {
        String[] images = {""}; //TODO: array index should replace the server image names 5 images to download
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("servers/image/" + images[new Random().nextInt(5)]).toUriString();
    }

}
