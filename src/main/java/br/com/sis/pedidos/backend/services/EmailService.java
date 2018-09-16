package br.com.sis.pedidos.backend.services;

import br.com.sis.pedidos.backend.domain.Cliente;
import org.springframework.mail.SimpleMailMessage;

import br.com.sis.pedidos.backend.domain.Pedido;

import javax.mail.internet.MimeMessage;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Pedido obj);

	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
