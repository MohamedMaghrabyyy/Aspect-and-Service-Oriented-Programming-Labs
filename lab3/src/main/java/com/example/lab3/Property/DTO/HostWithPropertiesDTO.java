package com.example.lab3.Property.DTO;

import com.example.lab3.Host.Host;
import com.example.lab3.Property.Property;
import java.util.List;

public class HostWithPropertiesDTO {

    private Host host;
    private List<Property> properties;

    public HostWithPropertiesDTO(Host host, List<Property> properties) {
        this.host = host;
        this.properties = properties;
    }

    // Getters and Setters
    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
