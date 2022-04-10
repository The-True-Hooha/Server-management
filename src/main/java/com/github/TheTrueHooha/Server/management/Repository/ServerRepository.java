package com.github.TheTrueHooha.Server.management.Repository;

import com.github.TheTrueHooha.Server.management.Model.Servers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Servers, Long> {

    //method tha finds the IP by its address
    Servers findByIpAddress(String ipAddress);

}
