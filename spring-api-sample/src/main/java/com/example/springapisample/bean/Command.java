package com.example.springapisample.bean;

import javax.validation.constraints.NotNull;

public class Command {

	@NotNull
    private int id;

	@NotNull
	private String commandName;

	private String parameters;


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

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

}
