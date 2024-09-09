package ai.rnt.customerFeedback.entity;

import static java.time.LocalDateTime.now;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@MappedSuperclass
@Getter
@Setter
public abstract class Auditable implements Serializable {

	private static final long serialVersionUID = -406869010295120058L;

	@Column(name = "created_by", nullable = false, updatable = false)
	private Integer createdBy;

	@Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP", updatable = false)
	private LocalDateTime createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime updatedDate;

	@Column(name = "deleted_by")
	protected Integer deletedBy;

	@Column(name = "deleted_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime deletedDate;

	@PrePersist
	public void beforPersist() {
//		if(nonNull(getContext()) && nonNull(getContext().getAuthentication()) 
//				&& nonNull(getContext().getAuthentication().getDetails())) {
//			UserDetail details =(UserDetail) getContext().getAuthentication().getDetails();
//			this.createdBy = details.getStaffId();
//		}else {
//			this.createdBy = 1;
//		}
//		this.createdBy = 1;
//		this.createdDate = now();
	}

	@PreUpdate
	public void beforUpdate() {
//		if(nonNull(getContext()) && nonNull(getContext().getAuthentication()) 
//				&& nonNull(getContext().getAuthentication().getDetails())) {
//		UserDetail details =(UserDetail) getContext().getAuthentication().getDetails();
//			this.updatedBy = details.getStaffId();
//		}else {
//			this.updatedBy = 1;
//		}
		this.updatedBy = 1;
		this.updatedDate = now();
	}

	@PreRemove
	public void beforDelete() {
		/*
		 * if(nonNull(getContext()) && nonNull(getContext().getAuthentication()) &&
		 * nonNull(getContext().getAuthentication().getDetails())) { UserDetail details
		 * =(UserDetail) getContext().getAuthentication().getDetails(); this.deletedBy =
		 * details.getStaffId(); }else { this.deletedBy = 1; }
		 */
		
		this.deletedBy = 1;
		this.deletedDate = now();
	}
}
//@formatter:on