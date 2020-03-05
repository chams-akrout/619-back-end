package com.barcode.BarcodeProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barcode.BarcodeProject.model.Role;

@Repository
public interface IRoleDao extends JpaRepository<Role, Integer> {
	Role findByRole(String role);

}
