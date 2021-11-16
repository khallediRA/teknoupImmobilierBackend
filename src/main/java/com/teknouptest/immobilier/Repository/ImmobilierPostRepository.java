package com.teknouptest.immobilier.Repository;

import com.teknouptest.immobilier.model.ImmobilierPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmobilierPostRepository extends JpaRepository<ImmobilierPost, Long> {

}
