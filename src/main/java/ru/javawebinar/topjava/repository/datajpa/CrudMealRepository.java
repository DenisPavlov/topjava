package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    int deleteByIdAndUserId(int id, int userId);

    Meal findByIdAndUserId(int id, int userId);

    List<Meal> findAllByUserIdOrderByDateTimeDesc(int userId);

    List<Meal> findByDateTimeBetweenAndUserIdOrderByDateTimeDesc(LocalDateTime startDate, LocalDateTime endDate, int userId);

    @EntityGraph(value = "Meal.user", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Meal m WHERE m.id=?1 AND m.user.id=?2")
    Meal getWithUser(int id, int userId);
}
