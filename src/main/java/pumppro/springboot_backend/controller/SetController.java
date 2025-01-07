package pumppro.springboot_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pumppro.springboot_backend.dtos.SetDto;
import pumppro.springboot_backend.exception.ResourceNotFoundException;
import pumppro.springboot_backend.model.Set;
import pumppro.springboot_backend.service.SetService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/sets")
@RequiredArgsConstructor
public class SetController {

    @Autowired
    private SetService setService;

    @GetMapping
    public ResponseEntity<List<SetDto>> getAllSets() {
        List<SetDto> sets = setService.getAllSets();
        return ResponseEntity.ok(sets);
    }

    @PostMapping
    public ResponseEntity<SetDto> createSet(@RequestBody SetDto setDto) {
        SetDto createdSet = setService.createSet(setDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetDto> getSetById(@PathVariable long id) {
        SetDto set = setService.getSetById(id);
        return ResponseEntity.ok(set);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SetDto> updateSet(@PathVariable long id, @RequestBody SetDto setDto) {
        SetDto updatedSet = setService.updateSet(id, setDto);
        return ResponseEntity.ok(updatedSet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSet(@PathVariable long id) {
        setService.deleteSet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
