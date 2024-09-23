package com.apple.shop.cream;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ClothRepository extends JpaRepository<Cloth, Long> {
    Page<Cloth> findPageBy(Pageable page);
}
