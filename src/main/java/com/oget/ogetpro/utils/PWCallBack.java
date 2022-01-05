package com.oget.ogetpro.utils;

import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

public class PWCallBack implements CallbackHandler {
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; ++i) {
			if (!(callbacks[i] instanceof WSPasswordCallback)) {
				throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
			}

			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
			ResourceBundle rs = null;

			try {
				rs = ResourceBundle.getBundle("seguridad");
			} catch (MissingResourceException var6) {
				throw new RuntimeException("Preferencias para WSS no encontradas");
			}

			String pass = rs.getString("org.apache.ws.security.crypto.merlin.keystore.password");
			pc.setPassword(pass);
		}

	}
}