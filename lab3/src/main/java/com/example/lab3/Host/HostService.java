package com.example.lab3.Host;

import com.example.lab3.Host.DTO.createHostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostService {

    @Autowired
    private HostRepository hostRepository;

    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public Host createHost(createHostDTO dto) {
        Host host = new Host(
                dto.getEmail(),
                dto.getPassword(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPhoneNumber()
        );
        return hostRepository.save(host);
    }

    public List<Host> getAllHosts() {
        return hostRepository.findAll();
    }

    public Host getHostById(Integer id) {
        Optional<Host> optionalHost = hostRepository.findById(id);
        return optionalHost.orElse(null);
    }

}
