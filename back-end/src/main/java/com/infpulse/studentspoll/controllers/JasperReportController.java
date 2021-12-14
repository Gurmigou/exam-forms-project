package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.service.JasperService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.SQLException;

@Controller
@RequestMapping("/api")
@CrossOrigin("*")
public class JasperReportController {

    private final JasperService jasperService;

    @Autowired
    public JasperReportController(JasperService jasperService) {
        this.jasperService = jasperService;
    }

    /**
     * @param response  returns stream of the report in the .pdf format
     * @param principal from Spring Security
     * @param id        unique formID
     */
    @PostMapping("/report/{id}")
    public void export(HttpServletResponse response, Principal principal, @PathVariable Long id)
            throws IOException, JRException, SQLException {

//        response.setContentType("application/x-download");
//        response.setHeader("Content-Disposition", "attachment; filename=\"usersAnswers.pdf\"");

//        OutputStream out = response.getOutputStream();
        JasperPrint jasperPrint = jasperService.exportPdfFile(principal.getName(), id);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, out);

        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("employeeReport.pdf"));

        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor("baeldung");
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);

        exporter.exportReport();
    }
}