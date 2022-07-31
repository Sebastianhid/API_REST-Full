package com.sebastianhidalgofullstock.app.repository;


import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sebastianhidalgofullstock.app.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{



}
