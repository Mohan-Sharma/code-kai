package com.code.kai.cashbackengine.persistence;

import com.code.kai.cashbackengine.domain.model.CashBackOffer;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Mohan Sharma Created on 13/07/18.
 */
public interface CashBackOfferRepository extends MongoRepository<CashBackOffer, String>
{
	List<CashBackOffer> findAllByActive(Boolean active, Sort priority);

	CashBackOffer findOneByCashbackIdAndActive(String cashbackId, Boolean active);
}
