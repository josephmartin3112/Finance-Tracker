package com.ust.Tracker.Tracker.controller;


import com.ust.Tracker.Tracker.dto.Expensesdto;
import com.ust.Tracker.Tracker.exception.ExpenseNotFoundException;
import com.ust.Tracker.Tracker.model.Expenses;
import com.ust.Tracker.Tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/addexpenses")
    public ResponseEntity<Expenses> addExpense(@RequestBody @Valid Expensesdto dto){
        return new ResponseEntity<>(expenseService.addExpense(dto), HttpStatus.CREATED);
    }
    @GetMapping("/getexpenses/{id}")
    public ResponseEntity<Expenses> getExpense(@PathVariable UUID id) throws ExpenseNotFoundException {
        return ResponseEntity.ok(expenseService.getExpense(id));
    }
}
