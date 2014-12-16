package org.netpod.core.repository;
import org.netpod.core.domain.entity.ToDoEntry;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ToDoRepository extends JpaRepository<ToDoEntry, Long> {

}
