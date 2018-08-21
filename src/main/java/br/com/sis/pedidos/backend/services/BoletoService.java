package br.com.sis.pedidos.backend.services;

import br.com.sis.pedidos.backend.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {
    public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date instantePagamento){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(instantePagamento);
        calendario.add(Calendar.DAY_OF_MONTH,7);
        pgto.setDataPagamento(calendario.getTime());
    }
}
