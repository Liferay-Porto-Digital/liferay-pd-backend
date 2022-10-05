package br.com.liferay.liferaypdbackend.repositories;

import br.com.liferay.liferaypdbackend.models.product.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IFormRepository extends JpaRepository<Form, UUID> {
}
