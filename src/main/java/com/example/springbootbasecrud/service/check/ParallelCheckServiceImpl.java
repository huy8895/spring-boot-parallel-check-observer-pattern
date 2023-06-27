package com.example.springbootbasecrud.service.check;

import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service

public class ParallelCheckServiceImpl implements ParallelCheckService {
	
	private final Map<RuleCheckType, CheckerObserver> map = new HashMap<>();
	
	public void subscriber(CheckerObserver observer, RuleCheckType type){
		map.put(type, observer);
	};
	
	private List<CompletableFuture<Boolean>> parallelCheckAll(){
		return map.values().stream().map(s -> CompletableFuture.supplyAsync(s::check))
				.collect(Collectors.toList());
	}
	
	@Override
	public Object check() {
		
		List<CompletableFuture<Boolean>> completableFutures = parallelCheckAll();
		CompletableFuture<Void> allFutures = CompletableFuture
				.allOf(completableFutures.toArray(CompletableFuture[]::new));

		CompletableFuture<List<Boolean>> allPageContentsFuture = allFutures.thenApply(v -> completableFutures.stream().map(CompletableFuture::join)
	                                                                                                     .collect(Collectors.toList()));
		try {
			return allPageContentsFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}
}
