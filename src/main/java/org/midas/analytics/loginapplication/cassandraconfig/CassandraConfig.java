package org.midas.analytics.loginapplication.cassandraconfig;

import java.util.Arrays;
import java.util.List;

import org.midas.analytics.loginapplication.constants.LoginConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {
	
	@Override
	protected String getKeyspaceName() {
		return LoginConstants.KEYSPACE;
	}
	
	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}
	
	@Override
	public List<CreateKeyspaceSpecification> getKeyspaceCreations() {
		CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(LoginConstants.KEYSPACE)
				.with(KeyspaceOption.DURABLE_WRITES, true)
				.ifNotExists();
		
		return Arrays.asList(specification);
	}
	
	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "org.midas.analytics.loginapplication.model" };
	}
	
	@Override
	protected String getLocalDataCenter() {
	    return "datacenter1";
	}
	
}
