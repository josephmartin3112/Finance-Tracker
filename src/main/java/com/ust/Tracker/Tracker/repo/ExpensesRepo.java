package com.ust.Tracker.Tracker.repo;

import com.ust.Tracker.Tracker.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ExpensesRepo extends JpaRepository<Expenses, UUID> {
    @Query("SELECT SUM(e.amount) FROM Expenses e WHERE MONTH(e.createDate)=:month AND YEAR(e.createDate)=:year AND e.typetransactions='credit'")
    Double findTotalCreditsByMonthAndYear(@Param("month") int month,@Param("year") int year);

    @Query("SELECT SUM(e.amount) FROM Expenses e WHERE MONTH(e.createDate)=:month AND YEAR(e.createDate)=:year AND e.typetransactions='debit'")
    Double findTotalDebitsByMonthAndYear(@Param("month") int month,@Param("year") int year);

}
