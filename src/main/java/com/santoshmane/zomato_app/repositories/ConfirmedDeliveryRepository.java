package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.dtos.ConfirmedDeliveryDto;
import com.santoshmane.zomato_app.entities.ConfirmedDelivery;
import com.santoshmane.zomato_app.entities.DeliveryPartner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmedDeliveryRepository extends JpaRepository<ConfirmedDelivery,Long> {
    Page<ConfirmedDelivery> findByDeliveryPartner(DeliveryPartner deliveryPartner, PageRequest pageRequest);
}
