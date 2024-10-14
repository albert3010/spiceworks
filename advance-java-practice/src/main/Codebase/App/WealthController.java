//package App;
//
//package com.example.wealthapp.controller;
//
//import com.example.wealthapp.model.Wealth;
//import com.example.wealthapp.repository.WealthRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/wealth")
//public class WealthController {
//
//    @Autowired
//    private WealthRepository wealthRepository;
//
//    @GetMapping
//    public List<Wealth> getAllWealth() {
//        return wealthRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Wealth> getWealthById(@PathVariable Long id) {
//        Optional<Wealth> wealth = wealthRepository.findById(id);
//        return wealth.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public Wealth createWealth(@RequestBody Wealth wealth) {
//        return wealthRepository.save(wealth);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Wealth> updateWealth(@PathVariable Long id, @RequestBody Wealth wealthDetails) {
//        Optional<Wealth> wealthOptional = wealthRepository.findById(id);
//
//        if (wealthOptional.isPresent()) {
//            Wealth wealth = wealthOptional.get();
//            wealth.setWealthMap(wealthDetails.getWealthMap());
//            Wealth updatedWealth = wealthRepository.save(wealth);
//            return ResponseEntity.ok(updatedWealth);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<HttpStatus> deleteWealth(@PathVariable Long id) {
//        try {
//            wealthRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
