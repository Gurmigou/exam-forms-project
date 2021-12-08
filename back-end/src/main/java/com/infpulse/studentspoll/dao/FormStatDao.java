package com.infpulse.studentspoll.dao;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Transactional
@Repository
public class FormStatDao {

    public static final String FORM_STATS_PATH = "classpath:resources/jasperReports/formStats.jrxml";
    private final JdbcTemplate jdbcTemplate;
    private final ResourceLoader resourceLoader;

    @Autowired
    public FormStatDao(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }

    public JasperPrint exportFormStatPdf(String email, Long formId)
            throws SQLException, JRException, IOException {
        Connection conn = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();

        String path = resourceLoader.getResource(FORM_STATS_PATH)
                .getURI().getPath();
        JasperReport jasperReport = JasperCompileManager.compileReport(path);
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("USER_EMAIL", email);
        parameters.put("FORM_ID", formId);

        return JasperFillManager.fillReport(jasperReport, parameters, conn);
    }
}
