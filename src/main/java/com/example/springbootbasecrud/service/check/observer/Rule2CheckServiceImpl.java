package com.example.springbootbasecrud.service.check.observer;

import com.example.springbootbasecrud.service.check.CheckerObserver;
import com.example.springbootbasecrud.service.check.ParallelCheckServiceImpl;
import com.example.springbootbasecrud.service.check.RuleCheckType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@ConditionalOnProperty(name = "parallel-check.rule2", havingValue = "true")
public class Rule2CheckServiceImpl implements CheckerObserver {
	
	public Rule2CheckServiceImpl(ParallelCheckServiceImpl parallelCheckService) {
		parallelCheckService.subscriber(this, RuleCheckType.RULE_2);
	}
	
	@Override
	public boolean check() {
		log.info("start RULE_2 check");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		log.info("end RULE_2 check");
		return true;
	}
}
