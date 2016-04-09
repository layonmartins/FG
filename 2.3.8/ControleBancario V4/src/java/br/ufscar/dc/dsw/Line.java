package br.ufscar.dc.dsw;

import java.util.Date;

public class Line implements Comparable<Line>{
    
    private final Date data;
    
    private final String tipo;
    
    private final String motivo;
    
    private final Double valor;
    
    private final Double saldo;

    public Line(Date data, String tipo, Double saldo, Double valor, String motivo) {
        this.data = data;
        this.tipo = tipo;
        this.saldo = saldo;
        this.valor = valor;
        this.motivo = motivo;
    }
    
    public Line(Date data, String tipo, double saldo) {
        this(data, tipo, saldo, null, null);
    }
    

    public Date getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public Double getValor() {
        return valor;
    }

    public Double getSaldo() {
        return saldo;
    }    

    
    @Override
    public int compareTo(Line o) {
        return this.data.compareTo(o.data);
    }

    @Override
    public String toString() {
        return data.toString() + " - " + valor + " - " + saldo;
    }
    
}
