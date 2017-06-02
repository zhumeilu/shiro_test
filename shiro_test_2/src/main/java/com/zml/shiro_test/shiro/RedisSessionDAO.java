package com.zml.shiro_test.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import com.zml.shiro_test.redis.RedisClient;

public class RedisSessionDAO extends AbstractSessionDAO {

	RedisClient redisClient;
	private String prefixStr = "shiro_session:";
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		this.saveSession(session);
	}

	@Override
	public void delete(Session session) {
		if (session == null || session.getId() == null) {
//			logger.error("session or session id is null");
			return;
		}
		redisClient.delete(getStringKey(session.getId()));
	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();

		Set<String> keys = redisClient.keySet(this.prefixStr + "*");
		if (keys != null && keys.size() > 0) {
			for (String key : keys) {
				Session s = redisClient.getObject(key);
				sessions.add(s);
			}
		}
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.saveSession(session);
		return sessionId;
	}

	private void saveSession(Session session) {
		if (session == null || session.getId() == null) {
//			logger.error("session or session id is null");
			return;
		}
		// byte[] key = getByteKey(session.getId());
		// byte[] value = SerializationUtils.serialize(session);
		// session.setTimeout(redisManager.getExpire()*1000);
		String key = getStringKey(session.getId());
		this.redisClient.set(key, Integer.parseInt(session.getTimeout() + ""),
				session);		
	}

	private String getStringKey(Serializable id) {
		return prefixStr+id;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
//			logger.error("session id is null");
			return null;
		}
		Session s = redisClient.getObject(this.getStringKey(sessionId));
		return s;
	}
}
