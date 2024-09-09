//package ai.rnt.customerFeedback.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import ai.rnt.customerFeedback.entity.Client;
//
//public interface ClientRepository extends JpaRepository<Client, Integer> {
//
//	@Query(value = "select pseudo_name from portfolio_db.pf_customer_master where company_name =?1", nativeQuery = true)
//	String getCompanyPseudoName(String companyName);
//}
