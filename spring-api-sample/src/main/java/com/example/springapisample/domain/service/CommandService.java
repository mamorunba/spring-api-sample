package com.example.springapisample.domain.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.springapisample.bean.Command;
import com.example.springapisample.controller.CommandController;
import com.example.springapisample.domain.dao.CommandDao;
import com.example.springapisample.domain.model.CommandEntity;
import com.example.springapisample.exception.ApplicationException;
import com.example.springapisample.exception.CommandCompleteFailureException;
import com.example.springapisample.exception.CommandException;

@Service
public class CommandService {
    private static Log log = LogFactory.getLog(CommandController.class);
    /** コマンド実行状況の確認を繰り返す回数 */
    private static final int REPEAT_TIMES = 60;
    /** コマンド実行状況の確認を行う頻度(ミリ秒) */
    private static final int FREQUENCY_SECONDS = 5000;

	@Autowired
	CommandDao commandDao;

	public List<CommandEntity> selectAll() {
		return commandDao.selectAll();
	}

	public int insert(CommandEntity entity) {
		return commandDao.insert(entity);
	}

	public int update(CommandEntity entity) {
		return commandDao.update(entity);
	}

	@Async
	public void doCommandAsync(Command command) {
    	log.debug("!!! start !!!");

		// ステータスを実行中に変更
    	CommandEntity entity = new CommandEntity();
    	BeanUtils.copyProperties(command, entity);
    	entity.setStatus("1");	// 実行中
    	update(entity);

    	try {
    		Thread.sleep(30000); // 30秒間だけ処理を止める
    	} catch (InterruptedException e) {
    	}

    	// ステータスを正常終了に変更
    	entity.setStatus("2");	// 正常終了
    	update(entity);

    	log.debug("!!! end !!!");
	}

	@Async
	@Retryable(include = CommandCompleteFailureException.class, backoff = @Backoff(delay = FREQUENCY_SECONDS), maxAttempts = REPEAT_TIMES)
	public void terminateCommandAsync(Command command) {

		CommandEntity entity = commandDao.selectById(command.getId());

		// エラーの場合
		if (entity.getStatus().contentEquals("2")) {
	    	log.info("===== END commandId=[" + command.getId() + "] command error =====");
			throw new CommandException("Comand error");
		}

		// 正常終了の場合、ループを抜ける
		if (entity.getStatus().contentEquals("2")) {
	    	log.info("===== END commandId=[" + command.getId() + "] command success =====");
			return;
		}

		// リトライ
    	log.info("----- continue commandId=[" + command.getId() + "] -----");
    	throw new CommandCompleteFailureException();
	}

    /**
     * ApplicationExceptionが発生した場合に呼び出されるメソッド
     *
     * @param ApplicationException e アプリ独自定義のException
     */
    @Recover
    public void recover(ApplicationException e) {
        throw new ApplicationException(e.getMessage());
    }

    /**
     * コマンド実行状況取得を指定回数分リトライ実行しても終了しなかった場合に呼び出されるメソッド
     *
     * CommandCompleteFailureExceptionが発生した場合に呼び出される
     */
    @Recover
    public void recover() {
        throw new ApplicationException("Command status check task Timeout.");
    }

}