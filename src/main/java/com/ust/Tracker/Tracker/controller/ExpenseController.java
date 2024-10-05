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

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity <List<Expenses>> getAllExpenses(){
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @PostMapping("/addexpenses")
    public ResponseEntity<Expenses> addExpense(@RequestBody @Valid Expensesdto dto){
        return new ResponseEntity<>(expenseService.addExpense(dto), HttpStatus.CREATED);
    }
    @GetMapping("/getexpenses/{id}")
    public ResponseEntity<Expenses> getExpense(@PathVariable UUID id) throws ExpenseNotFoundException {
        return ResponseEntity.ok(expenseService.getExpense(id));
    }
    @GetMapping("/month/{month}/{year}")
    public ResponseEntity<Map<String,Double>> getTotalsForMonth(@PathVariable int month,@PathVariable int year){
        Map<String,Double> totals=expenseService.getTotalsForMonth(month,year);
        return ResponseEntity.ok(totals);
    }
}
