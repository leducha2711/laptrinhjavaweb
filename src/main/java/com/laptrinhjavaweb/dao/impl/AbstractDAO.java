package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.laptrinhjavaweb.dao.IGenericDAO;
import com.laptrinhjavaweb.mapper.IRowMapper;

public class AbstractDAO<T> implements IGenericDAO<T> {

	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	public Connection getConnection() {
		try {
			Class.forName(resourceBundle.getString("className"));
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<T>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			setParameters(preparedStatement, parameters);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
		
	}

	private void setParameters(PreparedStatement preparedStatement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if(parameter instanceof Long) {
					preparedStatement.setLong(index,(Long) parameter);
				}else if(parameter instanceof String) {
					preparedStatement.setString(index, (String) parameter);
				}else if(parameter instanceof Integer) {
					preparedStatement.setInt(index, (int) parameter);
				}else if(parameter instanceof Timestamp) {
					preparedStatement.setTimestamp(index, (Timestamp) parameter);
				}else if(parameter == null) {
					preparedStatement.setNull(index, Types.NULL);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection= getConnection();
			connection.setAutoCommit(false);
			preparedStatement =connection.prepareStatement(sql);
			setParameters(preparedStatement, parameters);
			preparedStatement.executeUpdate();
			connection.commit();
		}catch (SQLException e) {
			if(connection!=null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet= null;
		Long id= null;
		try {
			connection= getConnection();
			connection.setAutoCommit(false);
			preparedStatement =connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameters(preparedStatement, parameters);
			preparedStatement.executeUpdate();
			resultSet= preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		}catch (SQLException e) {
			if(connection!=null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}

	}

	@Override
	public int count(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet= null;
		try {
			int count =0;
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			setParameters(preparedStatement, parameters);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			return count;
		}catch (SQLException e) {
			return 0;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return 0;
			}
		}
	}
	
}
