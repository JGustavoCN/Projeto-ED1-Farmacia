package br.edu.ifs.farmacia.report;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import raven.toast.Notifications;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ReportManager {

    private static ReportManager instance;

    private JasperReport reportEstoque;
    private JasperReport reportVendas;

    public static ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    private ReportManager() {
    }

    public void compileReport() throws JRException {
        InputStream estoqueStream = getReportStream("print/estoque.jrxml");
        InputStream vendasStream = getReportStream("print/vendas.jrxml");

        if (estoqueStream == null) {
            throw new JRException("Arquivo estoque.jrxml não encontrado no JAR.");
        }
        if (vendasStream == null) {
            throw new JRException("Arquivo vendas.jrxml não encontrado no JAR.");
        }

        reportEstoque = JasperCompileManager.compileReport(estoqueStream);
        reportVendas = JasperCompileManager.compileReport(vendasStream);
    }

    private InputStream getReportStream(String reportPath) {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(reportPath);
        if (stream == null) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Arquivo de relatório não encontrado: " + reportPath);
        }
        return stream;
    }

    public void printReportEstoque(ParameterReportEstoque data) throws JRException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("totalEntrada", data.getTotalEntrada());
        parameters.put("totalSaida", data.getTotalSaida());
        parameters.put("totalLucro", data.getTotalLucro());
        parameters.put("totalQuantidade", data.getTotalQuantidade());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(reportEstoque, parameters, dataSource);
        view(print);
    }

    public void printReportVendas(ParameterReportVendas data) throws JRException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("totalVendido", data.getTotalVendido());
        parameters.put("totalQuantidade", data.getTotalQuantidade());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(reportVendas, parameters, dataSource);
        view(print);
    }

    private void view(JasperPrint print) throws JRException {
        JasperViewer.viewReport(print, false);
    }
}