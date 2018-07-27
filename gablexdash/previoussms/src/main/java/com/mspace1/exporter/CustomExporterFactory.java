package com.mspace1.exporter;


import javax.faces.FacesException;
import javax.faces.context.FacesContext;

import org.primefaces.extensions.component.exporter.*;

import com.mspace1.controller.ExporterController;


public class CustomExporterFactory implements ExporterFactory {

    static public enum ExporterType {
        PDF,
        XLSX
    }

    @Override
    public Exporter getExporterForType(String type) {

        Exporter exporter = null;

        FacesContext context = FacesContext.getCurrentInstance();
        ExporterController bean = (ExporterController) context.getApplication().evaluateExpressionGet(context, "#{exporterController}", ExporterController.class);
        Boolean   customExport=bean.getCustomExporter();

        try {
            ExporterType exporterType = ExporterType.valueOf(type.toUpperCase());

            switch (exporterType) {

                case PDF:
                    if(customExport) {
                    exporter = new PDFCustomExporter();
                    }
                    else {
                    exporter = new PDFExporter();
                    }
                    break;

                case XLSX:
                    if(customExport) {
                        exporter = new ExcelCustomExporter();
                    }
                    else {
                        exporter = new ExcelExporter();
                    }
                    break;


                default: {
                    if(customExport) {
                        exporter = new PDFCustomExporter();
                    }
                    else {
                        exporter = new PDFExporter();
                    }
                    break;
                }

            }
        } catch (IllegalArgumentException e) {
            throw new FacesException(e);
        }

        return exporter;
    }

}