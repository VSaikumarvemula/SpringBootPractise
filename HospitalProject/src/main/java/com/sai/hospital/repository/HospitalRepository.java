package com.sai.hospital.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sai.hospital.entity.Hospital;

@Repository
public interface HospitalRepository  extends JpaRepository<Hospital, Long>{

	

}
