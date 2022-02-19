package com.zee.zee5pp.utils;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomNamingStrategy extends PhysicalNamingStrategyStandardImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4530385002336118182L;

	private final static String POSTFIX = "_table";
	// by default all tables should be ended with name _table
	// we don't want to apply this _table for each and every entity specification
	// we want to set it as a thumb rule

	@Override
	public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment context) {

		if (identifier == null) {
			return null;
		}
		final String newName = identifier.getText() + POSTFIX;
		// Table Name ==>
		// 1. If @Table is there it will use that name
		// 2. If @Table not there then it will take entity name
		// 3. If nothing then by default it will take the class name as the entity name.

		return Identifier.toIdentifier(newName);

	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
		if (name == null)
			return null;
		return Identifier.toIdentifier(name.getText().toLowerCase());
	}

}
