package com.example.springbootbasecrud.service.check.observer;

import com.example.springbootbasecrud.service.check.CheckerObserver;
import com.example.springbootbasecrud.service.check.ParallelCheckService;
import com.example.springbootbasecrud.service.check.ParallelCheckServiceImpl;
import com.example.springbootbasecrud.service.check.RuleCheckType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@ConditionalOnProperty(name = "parallel-check.rule1", havingValue = "true")
public class Rule1CheckServiceImpl implements CheckerObserver {
	
	public Rule1CheckServiceImpl(ParallelCheckServiceImpl parallelCheckService) {
		parallelCheckService.subscriber(this, RuleCheckType.RULE_1);
	}
	
	@Override
	public boolean check() {
		log.info("start RULE_1 check");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		log.info("end RULE_1 check");
		return true;
	}
}
