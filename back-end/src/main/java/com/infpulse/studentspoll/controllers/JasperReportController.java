package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.service.JasperService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.SQLException;

@RestController
@RequestMapping("/api")
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

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment; filename=\"usersAnswers.pdf\"");

        OutputStream out = response.getOutputStream();
        JasperPrint jasperPrint = jasperService.exportPdfFile(principal.getName(), id);
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    }
}
