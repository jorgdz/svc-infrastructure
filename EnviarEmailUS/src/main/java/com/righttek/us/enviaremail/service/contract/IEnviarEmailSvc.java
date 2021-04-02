package com.righttek.us.enviaremail.service.contract;

import javax.mail.MessagingException;

import com.righttek.modelocanonico.email.EmailNotificacionAtraso;
import com.righttek.modelocanonico.email.EmailSimple;

/**
 * 
 * @author JORGE
 *
 */
public interface IEnviarEmailSvc {

	void enviarEmailSimple(EmailSimple email);
	
	void enviarNotificacionAtraso(EmailNotificacionAtraso email) throws MessagingException;
}
