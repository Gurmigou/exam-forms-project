package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.dao.FormStatDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class JasperService {

    private final FormStatDao formStatDao;

    @Autowired
    public JasperService(FormStatDao formStatDao) {
        this.formStatDao = formStatDao;
    }

    public JasperPrint exportPdfFile(String email, Long formId)
            throws SQLException, JRException, IOException {
        return formStatDao.exportFormStatPdf(email, formId);
    }
}
