package com.dev.myninjaregister.ninjaregister1.Missions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionsRepository extends JpaRepository<MissionsModel ,Long> {
}
