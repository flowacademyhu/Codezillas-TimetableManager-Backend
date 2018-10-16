package hu.flowacademy.timetablemanager.repository;


import hu.flowacademy.timetablemanager.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}