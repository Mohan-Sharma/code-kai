package com.code.kai.cashbackengine.domain.model;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Mohan Sharma Created on 12/07/18.
 */
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> implements Serializable
{
	@CreatedBy
	private T createdBy;

	@CreatedDate
	@Temporal(TIMESTAMP)
	private LocalDateTime creationDatetime;

	@LastModifiedDate
	@Temporal(TIMESTAMP)
	private LocalDateTime modificationDatetime;
}
