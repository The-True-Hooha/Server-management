package com.github.TheTrueHooha.Server.management.Enums;

public enum ServerStatus {

    //indicating the server status
    SERVER_RUNNING("SERVER_UP"),
    SERVER_DOWN("SERVER_DOWN");

    private final String status;

    //constructor that queries the server status
    ServerStatus(String serverStatus) {
        this.status = serverStatus;
    }

    //getter that returns the serve status
    public String getServerStatus() {
        return this.status;
    }
}
