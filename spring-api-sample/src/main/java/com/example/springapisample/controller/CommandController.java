package com.example.springapisample.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapisample.bean.Command;
import com.example.springapisample.domain.model.CommandEntity;
import com.example.springapisample.domain.service.CommandService;

@RestController
public class CommandController {
    private static Log log = LogFactory.getLog(CommandController.class);

    @Autowired
    CommandService commandService;

    @PostMapping
    @RequestMapping(value = "/command")
    public List<CommandEntity> doCommand (@RequestBody @Validated Command command) {
    	log.info("===== START commandId=[" + command.getId() + "] =====");

    	// コマンドステータス情報を初期ステータスで登録
    	CommandEntity entity = new CommandEntity();
    	BeanUtils.copyProperties(command, entity);
    	entity.setStatus("0");	// 初期ステータス
    	commandService.insert(entity);

    	// 非同期でコマンド実行
    	commandService.doCommandAsync(command);

    	// コマンド終了確認
//    	commandService.terminateCommandAsync(command);

    	log.info("===== RETURN commandId=[" + command.getId() + "] =====");
        return commandService.selectAll();
    }
}
