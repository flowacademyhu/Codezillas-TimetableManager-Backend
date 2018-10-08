package hu.flowacademy.timetablemanager.repository;

import hu.flowacademy.timetablemanager.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

    @Query("select c from Class c " +
            "where c.startDate between :start_date_start AND :start_date_end")
    List<Class> filter(@Param("start_date_start") Long startDateStart,
                       @Param("start_date_end") Long startDateEnd);

}
