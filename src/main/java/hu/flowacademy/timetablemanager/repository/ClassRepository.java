package hu.flowacademy.timetablemanager.repository;

import hu.flowacademy.timetablemanager.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
}
