package com.github.TheTrueHooha.Server.management.Service;

import com.github.TheTrueHooha.Server.management.Model.Servers;

import java.util.Collection;

public interface ServerService {

    //creates a new server and saves to the database
    Servers createServer (Servers servers);

    //list the servers created
    Collection<Servers> listServers(int limit);

    //gets the id of the servers
    Servers getServers (Long id);

    //to updates from the list of the servers
    Servers updateServer (Servers servers);

    //to delete a server by the id
    Boolean deleteServer(Long id);
}
