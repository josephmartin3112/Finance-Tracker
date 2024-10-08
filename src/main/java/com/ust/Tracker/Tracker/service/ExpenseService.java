package com.ust.Tracker.Tracker.service;

import com.ust.Tracker.Tracker.dto.Expensesdto;
import com.ust.Tracker.Tracker.exception.ExpenseNotFoundException;
import com.ust.Tracker.Tracker.model.Expenses;
import com.ust.Tracker.Tracker.repo.ExpensesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ExpenseService {
    @Autowired
    private ExpensesRepo expensesrepo;

    public Expenses addExpense(Expensesdto dto) {
        Expenses expense = new Expenses();
        expense.setName(dto.getName());
        expense.setTypetransactions(dto.getTypetranscations());
        expense.setAmount(dto.getAmount());
        expense.setDescription(dto.getDescription());
        return expensesrepo.save(expense);
    }

    public List<Expenses> getAllExpenses() {
        List<Expenses> expenses=expensesrepo.findAll();
        return expenses;
    }

    public Expenses getExpense(UUID id) throws ExpenseNotFoundException {
        Expenses expense=expensesrepo.findById(id)
                .orElseThrow(()->new ExpenseNotFoundException("Expensive not found with id: "+id));
        return expense;
    }

    public Map<String,Double> getTotalsForMonth(int month, int year){
        Double totalCredits=expensesrepo.findTotalCreditsByMonthAndYear(month,year);
        Double totalDebits=expensesrepo.findTotalDebitsByMonthAndYear(month,year);

        Map<String,Double> totals=new HashMap<>();
        totals.put("totalCredits",totalCredits!=null? totalCredits:0.0);
        totals.put("totalDebits",totalDebits!=null? totalDebits:0.0);

        return totals;
    }
}