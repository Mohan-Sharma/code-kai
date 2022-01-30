package com.code.kai.cashbackengine.persistence;

import com.code.kai.cashbackengine.domain.model.ClaimCashBack;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Mohan Sharma Created on 13/07/18.
 */
public interface ClaimRepository extends MongoRepository<ClaimCashBack, String>
{
	ClaimCashBack findOneByCashbackIdAndCustomerPk(final String cashbackId, final String customerPk);

	List<ClaimCashBack> findAllByCustomerPkAndStatusIn(String customerPk, List<String> status);

	List<ClaimCashBack> findAllByCustomerPk(String customerPk);

	ClaimCashBack findOneByClaimIdAndCustomerPk(String claimId, String customerPk);
}
