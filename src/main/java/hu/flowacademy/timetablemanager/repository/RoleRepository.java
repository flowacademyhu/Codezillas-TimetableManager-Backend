package hu.flowacademy.timetablemanager.repository;

import hu.flowacademy.timetablemanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String roleName);
}
