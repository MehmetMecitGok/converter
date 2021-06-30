package com.trendyol.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.webservice.entity.LinkConverter;

@Repository
public interface ConverterRepository extends JpaRepository<LinkConverter, Long> {

}
