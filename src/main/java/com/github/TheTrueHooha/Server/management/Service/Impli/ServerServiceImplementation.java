package com.github.TheTrueHooha.Server.management.Service.Impli;

import com.github.TheTrueHooha.Server.management.Enums.ServerStatus;
import com.github.TheTrueHooha.Server.management.Model.Servers;
import com.github.TheTrueHooha.Server.management.Repository.ServerRepository;
import com.github.TheTrueHooha.Server.management.Service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

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

    @Override
    public Collection<Servers> listServers(int limit) {
        return null;
    }

    @Override
    public Servers getServers(Long id) {
        return null;
    }

    @Override
    public Servers updateServer(Servers servers) {
        return null;
    }

    @Override
    public Boolean deleteServer(Long id) {
        return null;
    }

    private String setServerImageUrl() {
        return null;
    }

}
