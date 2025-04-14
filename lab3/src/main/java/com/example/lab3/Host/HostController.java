package com.example.lab3.Host;

import com.example.lab3.Host.DTO.createHostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
public class HostController {

    @Autowired
    private HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @PostMapping
    public ResponseEntity<Host> createHost(@RequestBody createHostDTO hostDTO) {
        Host savedHost = hostService.createHost(hostDTO);
        return ResponseEntity.status(201).body(savedHost);
    }

    @GetMapping
    public ResponseEntity<List<Host>> getAllHosts() {
        List<Host> hosts = hostService.getAllHosts();
        return ResponseEntity.ok(hosts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> getHostById(@PathVariable Integer id) {
        Host host = hostService.getHostById(id);
        if (host != null) {
            return ResponseEntity.ok(host);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
