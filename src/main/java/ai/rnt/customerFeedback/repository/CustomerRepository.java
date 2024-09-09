package ai.rnt.customerFeedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ai.rnt.customerFeedback.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query(value = "select c.*,am.address_id,am.contact_person_name,am.contact_person_email,am.contact_person_no from customer c,address_master am\r\n"
			+ "where c.customer_id=am.customer_id\r\n" + "and c.customer_status = 'A'\r\n"
			+ "and am.contact_person_name is not null and am.contact_person_name !=''\r\n"
			+ "and am.contact_person_no is not null and am.contact_person_no !=''\r\n"
			+ "and am.contact_person_email is not null and am.contact_person_email !=''", nativeQuery = true)
	List<Customer> findAllCustomersForCustomerSatifaction();

	Customer findByCompanyName(String companyName);

	@Query(value = "select * from customer where customer_status = 'A'", nativeQuery = true)
	List<Customer> findAllActiveCustomers();
}
