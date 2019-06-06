package com.example.springapisample.domain.model;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "command_status")
public class CommandEntity {

	@Id
    private int id;

	private String commandName;

	private String status;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
