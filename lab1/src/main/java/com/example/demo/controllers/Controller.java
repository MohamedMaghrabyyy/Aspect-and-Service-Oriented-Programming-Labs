package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Collections;

@RestController
public class Controller {

    @PostMapping("/sort-list")
    public List<Integer> sortList(@RequestBody List<Integer> numbers)
    {
        Collections.sort(numbers);
        return numbers;
    }
    @GetMapping("/get-number-from-list")
    public int getNthNumber(@RequestParam int idx, @RequestParam List<Integer> numbers) {
        if (idx < 0 || idx >= numbers.size())
        {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return numbers.get(idx);
    }

    @PutMapping("/modify-number-in-list")
    public List<Integer> modifyNthNumber(@RequestParam int idx, @RequestParam List<Integer> numbers, @RequestParam int newValue) {
        if (idx < 0 || idx >= numbers.size())
        {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        numbers.set(idx, newValue);
        return numbers;
    }

    @DeleteMapping("/delete-number-from-list")
    public List<Integer> removeLastNumber(@RequestParam List<Integer> numbers) {
        if (!numbers.isEmpty()) {
            numbers.removeLast();
        }
        return numbers;
    }


}
