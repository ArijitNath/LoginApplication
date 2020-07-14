package org.midas.analytics.loginapplication.repositories;

import org.midas.analytics.loginapplication.model.LoginDetails;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<LoginDetails, String>{

}
