package org.ihtsdo.otf.rest.client.terminologyserver;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.ihtsdo.sso.integration.SecurityUtil;

import java.util.concurrent.TimeUnit;

public class SnowOwlRestClientFactory {

	private String snowOwlUrl;
	private String snowOwlReasonerId;
	private boolean snowOwlUseExternalClassificationService;
	private final Cache<String, SnowOwlRestClient> clientCache;

	public SnowOwlRestClientFactory(String snowOwlUrl, String snowOwlReasonerId, boolean snowOwlUseExternalClassificationService) {
		this.snowOwlUrl = snowOwlUrl;
		this.snowOwlReasonerId = snowOwlReasonerId;
		this.snowOwlUseExternalClassificationService = snowOwlUseExternalClassificationService;
		clientCache = CacheBuilder.newBuilder()
				.expireAfterAccess(5, TimeUnit.MINUTES)
				.build();
	}

	/**
	 * Creates a Snow Owl client using the authentication context of the current thread.
	 * @return
	 */
	public SnowOwlRestClient getClient() {
		String authenticationToken = SecurityUtil.getAuthenticationToken();
		SnowOwlRestClient client = clientCache.getIfPresent(authenticationToken);
		if (client == null) {
			synchronized (clientCache) {
				client = clientCache.getIfPresent(authenticationToken);
				if (client == null) {
					client = new SnowOwlRestClient(snowOwlUrl, authenticationToken);
					client.setReasonerId(snowOwlReasonerId);
					client.setUseExternalClassificationService(snowOwlUseExternalClassificationService);
					clientCache.put(authenticationToken, client);
				}
			}
		}
		return client;
	}

}
