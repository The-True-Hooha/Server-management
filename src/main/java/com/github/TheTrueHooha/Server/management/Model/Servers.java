package com.github.TheTrueHooha.Server.management.Model;

import com.github.TheTrueHooha.Server.management.Enums.ServerStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Servers {
    //defining the parameters for the models server management

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = false)
    @NotEmpty(message = "please input name of your server, can not be null")
    private String serverName;

    @Column(unique = true)
    @NotEmpty(message = "please input IP_address, can not be null")
    private String ipAddress;

    private String memory;
    private String type;
    private String imageUrl;
    private ServerStatus serverStatus;
}
