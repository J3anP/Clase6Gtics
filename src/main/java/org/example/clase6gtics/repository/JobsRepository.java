package org.example.clase6gtics.repository;

import org.example.clase6gtics.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends JpaRepository<Jobs,String> {

}
