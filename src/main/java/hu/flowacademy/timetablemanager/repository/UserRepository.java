package hu.flowacademy.timetablemanager.repository;


import hu.flowacademy.timetablemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByGroupId(Long id);

    //TODO: find by group name.

    @Query("select u from User u " +
            "left join u.subjects as subjects where :subject_id = subjects.id")
    List<User> findAllBySubjectId(@Param("subject_id") Long subjectId);

    User findByEmail(String email);
}