package com.example.springapisample.controller;

import static org.assertj.core.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springapisample.bean.Command;
import com.example.springapisample.domain.model.CommandEntity;
import com.example.springapisample.domain.service.CommandService;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CommandControllerTest {

    private Command command = new Command();

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    CommandService commandService;

    @Before
    public void initialize() {
    }

    @Test
    public void testDoCommand() throws URISyntaxException {
    	List<CommandEntity> commandList = commandService.selectAll();
    	command.setId(commandList.size() + 1);
    	command.setCommandName("urmmv");
    	command.setParameters("XXXX YYYY");

        RequestEntity<Command> requestEntity = RequestEntity.post(new URI("/command")).accept(MediaType.APPLICATION_JSON).body(command);

        ResponseEntity<CommandEntity> responseEntity = restTemplate.exchange(requestEntity, CommandEntity.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
