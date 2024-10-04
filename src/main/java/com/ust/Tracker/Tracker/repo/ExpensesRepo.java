package com.ust.Tracker.Tracker.repo;

import com.ust.Tracker.Tracker.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpensesRepo extends JpaRepository<Expenses, UUID> {
}
