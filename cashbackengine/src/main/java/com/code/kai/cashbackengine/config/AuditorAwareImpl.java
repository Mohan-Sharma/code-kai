package com.code.kai.cashbackengine.config;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;

/**
 * @author Mohan Sharma Created on 12/07/18.
 */
public class AuditorAwareImpl implements AuditorAware<String>
{
	@Override
	public Optional<String> getCurrentAuditor()
	{
		return Optional.of("admin");
	}
}
