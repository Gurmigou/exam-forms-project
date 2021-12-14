package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.service.JasperService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @RequestMapping(value = "/report/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] export(HttpServletResponse response, Principal principal, @PathVariable Long id)
            throws IOException, JRException, SQLException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"usersAnswers.pdf\"");
        JasperPrint jasperPrint = jasperService.exportPdfFile(principal.getName(), id);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}