package com.vhra.dashfi.values;

import com.vhra.dashfi.ValueDetail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockValuesDataSource implements ValuesDataSource {
    private static List<ValueDetail> mValueDetailList;

    public MockValuesDataSource() {
        init();
    }

    public List<ValueDetail> getValues() {
        return mValueDetailList;
    }

    @Override
    public void add(ValueDetail valueDetail) {
        createValue(valueDetail.getTitle(),
                valueDetail.getValue(),
                valueDetail.getLabels());
    }

    @Override
    public void updateValue(ValueDetail updatedValue) {
        ValueDetail valueDetail = mValueDetailList.get(updatedValue.getId());
        if (valueDetail != null) {
            mValueDetailList.set(valueDetail.getId(), updatedValue);
        }
    }

    private void init() {
        if (mValueDetailList != null) return;
        mValueDetailList = new ArrayList<>();
        createValue("NETSHOES*NETSHOES (08/10)", 59.92, "vestimenta", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("MERCPAGO*Avell (07/12)", 387.66, "meupc", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("KABUM (07/12)", 33.38, "meupc", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("ESPOSENDE LJ 812 (03/05)", 45.99, "vestimenta", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("C A MODAS LJ 470 (02/03)", 36.32, "vestimenta", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("IBYTE COMPUTADORES (02/10)", 34.99, "meupc", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("TACO (02/02)", 62.95, "vestimenta", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("PIOLA BAR", 53.94, "viagem_moto", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("MATIZ JAGUARIUNA", 45.50, "viagem_moto", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("MINUTO PAO LJ 5409", 27.14, "viagem_moto", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("MATIZ JAGUARIUNA", 45.50, "viagem_moto", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("JOSE CARLOS GABRIELLI", 37.20, "viagem_moto", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("DUTY PAID VIRACOPOS 1", 62.89, "viagem_moto", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("TBB DOM PEDRO SHOPPING", 20.50, "viagem_moto", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("TBB DOM PEDRO SHOPPING", 32.90, "viagem_moto", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("NETFLIX.COM", 45.90, "meugasto", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("REST E LANCH MARCHESIN", 49.06, "viagem_moto", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 11.11, "transporte", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 4.14, "transporte", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("PAG*FarmaciaDo", 38.95, "medicamento", "agosto", "gastos", "2019", "mastercard", "free");

        createValue("VENEZA SUPERMERCADO", 21.96, "alimentação", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("GRANJA MUITO TUDO",   43.20, "alimentação", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("VENEZA SUPERMERCADO", 26.59, "alimentação", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("VENEZA SUPERMERCADO", 23.98, "alimentação", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("VENEZA SUPERMERCADO", 145.09, "alimentação", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("VENEZA SUPERMERCADO", 23.56, "alimentação", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("VENEZA SUPERMERCADO", 16.45, "alimentação", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("VENEZA SUPERMERCADO", 26.95, "alimentação", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("VENEZA SUPERMERCADO", 25.16, "alimentação", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("VENEZA SUPERMERCADO", 81.07, "alimentação", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("VENEZA SUPERMERCADO", 21.42, "alimentação", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("VENEZA SUPERMERCADO", 56.93,"alimentação", "agosto", "gastos", "2019", "nubank");
        createValue("VENEZA SUPERMERCADO", 73.06,"alimentação", "agosto", "gastos", "2019", "nubank");
        createValue("Render mais",         79.19,"alimentação", "agosto", "gastos", "2019", "nubank");
        createValue("VENEZA SUPERMERCADO", 56.35,"alimentação", "agosto", "gastos", "2019", "nubank");
        createValue("VENEZA SUPERMERCADO", 82.28,"alimentação", "agosto", "gastos", "2019", "nubank");
        createValue("VENEZA SUPERMERCADO", 26.59,"alimentação", "agosto", "gastos", "2019", "nubank");
        createValue("VENEZA SUPERMERCADO", 129.81,"alimentação", "agosto", "gastos", "2019", "nubank");
        createValue("Render mais",          46.48,"alimentação", "agosto", "gastos", "2019", "nubank");
        createValue("VENEZA SUPERMERCADO", 48.79,"alimentação", "agosto", "gastos", "2019", "nubank");
        createValue("VENEZA SUPERMERCADO", 150.00,"alimentação", "agosto", "gastos", "2019", "nubank");

        createValue("Uber Do Brasil Tecnolo", 16.55, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 8.17, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("EBANX*SPOTIFY", 26.90, "meugasto", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("PET MANIA", 32.00, "casa_geral", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 19.32, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("JULIETTO 02", 25.90, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("JULIETTO 02", 25.90, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 19.72, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("COMODORO RESTAURANTES", 14.67, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 14.34, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("SEU BOTECO", 25.90, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 6.08, "transporte", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 8.85, "transporte", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("BILHETAGEM ELETRONICA", 20.00, "transporte", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("VENDA BOM JESUS", 20.90, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("JARDIM DO PARA", 20.00, "meugasto", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("MAGALU *MAGAZINELUIZ(01/10)", 72.99, "meupc", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 23.31, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 26.89, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 5.91, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("PAG*FarmaciaDo", 12.22, "medicamento", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 33.80, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 27.45, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 32.73, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 15.78, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("COMODOR RESTAURANTE", 17.04, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 15.35, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("VENDA BOM JESUS", 22.99, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 21.14, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("BONAPARTE ALFANDEGA", 28.40, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("FARMACIA DO TRABALHADO", 53.58, "medicamento	", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 27.37, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("COMODORO RESTAURANTES", 17.96, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 9.75, "transporte", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 19.15, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 5.91, "transporte", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("PORTO DO RECIFE STEAKH", 40.90, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 13.62, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 13.64, "transporte", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 30.48, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 11.82, "transporte", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 7.44, "transporte", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 8.05, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 11.04, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 26.40, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 6.00, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 11.01, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 20.35, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("BILHETAGEM ELETRONICA", 50.00, "transporte", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("COMODORO RESTAURANTES", 15.53, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 21.01, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 24.00, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("IFOOD*SPOONROCKET", 26.50, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("IFOOD*IFOOD", 61.80, "meugasto", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 8.23, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 12.22, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 20.44, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 13.23, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 4.73, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("VENDA BOM JESUS", 22.99, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 12.24, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 16.26, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 21.50, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 6.85, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 22.10, "vinicius", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 13.47, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("FARMACIA DO TRABALHADO", 45.58, "medicamento", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("Uber Do Brasil Tecnolo", 13.92, "uber_eu", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("COMODORO RESTAURANTES", 11.98, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("COMODORO RESTAURANTES", 16.84, "almoço", "agosto", "gastos", "2019", "mastercard", "free");
        createValue("CO WOK ANTIGO", 19.90, "almoço", "agosto", "gastos", "2019", "mastercard", "free");

        createValue("Paypal_Kabum – 9/10", 17.67,"meupc", "agosto", "gastos", "2019", "nubank");
        createValue("Mp Racoes", 20.00,"casa_geral", "agosto", "gastos", "2019", "nubank");
        createValue("Pag*Daneildasilvalima – 1/3", 376.68,"vinicius", "agosto", "gastos", "2019", "nubank");
        createValue("Paypal*Trixjap", 518.05,"inglês", "agosto", "gastos", "2019", "nubank");
        createValue("IOF de “Paypal *Trixjap”", 33.05,"inglês", "agosto", "gastos", "2019", "nubank");
        createValue("PET MANIA", 24.00,"casa_geral", "agosto", "gastos", "2019", "nubank");
        createValue("Farmacia do trabalhado", 93.64,"medicamento	", "agosto", "gastos", "2019", "nubank");
        createValue("Farmacia do trabalhado", 15.00,"medicamento", "agosto", "gastos", "2019", "nubank");
        createValue("Mercpago*Siscomatech – 1/12", 24.37,"meupc", "agosto", "gastos", "2019", "nubank");
        createValue("Pag*Shalon", 20.47,"casa_geral", "agosto", "gastos", "2019", "nubank");
        createValue("Pag*Shalon", 70.00,"casa_geral", "agosto", "gastos", "2019", "nubank");
        createValue("PET MANIA", 12.00,"casa_geral", "agosto", "gastos", "2019", "nubank");
        createValue("Mp Racoes", 150.00,"vinicius", "agosto", "gastos", "2019", "nubank");
        createValue("Pag*Fernandoalvesdeol", 12.00,"casa_geral", "agosto", "gastos", "2019", "nubank");
        createValue("Evernote Brasil", 48.00,"meugasto", "agosto", "gastos", "2019", "nubank");
        createValue("PET MANIA", 24.00,"casa_geral", "agosto", "gastos", "2019", "nubank");
        createValue("Farmacia do trabalhado", 14.00,"medicamento", "agosto", "gastos", "2019", "nubank");
        createValue("Farmacia do trabalhado", 35.80,"medicamento", "agosto", "gastos", "2019", "nubank");

        createValue("Fatura da Claro", 61.30, "agosto", "gastos", "2019", "claro");
        createValue("Fatura da Vivo", 305.61, "agosto", "gastos", "2019", "vivo");
        createValue("Mensalidade da Unibratec", 373.66, "agosto", "gastos", "2019", "unibratec");
        createValue("Aluguel de Agosot/2019", 350, "agosto", "gastos", "2019", "aluguel");



        createValue("Salarario de Agosto/2019", 5000.00, "ganho", "agosto", "2019", "receita");
        createValue("Salarario de Agosto/2019", 1500.00, "ganho", "agosto", "2019", "receita");
    }

    private void createValue(String title, double value, List<String> labels) {
        int id = mValueDetailList.size();
        mValueDetailList.add(new ValueDetail() {
            @Override
            public int getId() {
                return id;
            }

            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public double getValue() {
                return value;
            }

            @Override
            public List<String> getLabels() {
                return labels;
            }
        });
    }

    private void createValue(String title, double value, String... a) {
        createValue(title, value, Arrays.asList(a));
    }
}
