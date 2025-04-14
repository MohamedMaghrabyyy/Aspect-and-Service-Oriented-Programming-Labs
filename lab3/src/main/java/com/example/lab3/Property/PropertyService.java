package com.example.lab3.Property;

import com.example.lab3.Host.Host;
import com.example.lab3.Host.HostRepository;
import com.example.lab3.Property.DTO.createPropertyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final HostRepository hostRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository, HostRepository hostRepository) {
        this.propertyRepository = propertyRepository;
        this.hostRepository = hostRepository;
    }
    public Property addProperty(createPropertyDTO dto) {
        // Fetch the Host using the provided host ID
        Host host = hostRepository.findById(dto.getHostId())
                .orElseThrow(() -> new RuntimeException("Host not found"));

        // Create a new Property and associate it with the Host
        Property property = new Property();
        property.setTitle(dto.getTitle());
        property.setDescription(dto.getDescription());
        property.setPricePerNight(dto.getPricePerNight());
        property.setHost(host); // Link Host to Property

        return propertyRepository.save(property);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(Integer id) {
        return propertyRepository.findById(id);
    }

    public List<Property> getPropertiesByHostId(Integer hostId) {
        return propertyRepository.findByHostId(hostId);
    }

}

