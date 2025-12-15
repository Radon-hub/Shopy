package org.radon.shopy.address.infrastructure.repository;

import org.radon.shopy.address.infrastructure.repository.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AddressJpaRepository extends JpaRepository<AddressEntity,Long> {}
