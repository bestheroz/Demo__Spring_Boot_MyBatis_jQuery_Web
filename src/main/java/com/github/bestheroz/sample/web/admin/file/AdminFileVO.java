package com.github.bestheroz.sample.web.admin.file;

import org.joda.time.LocalDateTime;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class AdminFileVO implements Serializable {
    private Integer fileSeq;
    private String fileName;
    private String fileNameExt;
    private String mimeType;
    private String updatedBy;
    private LocalDateTime updated;

    @SuppressWarnings("unused")
    public Integer getFileSeq() {
        return this.fileSeq;
    }

    public void setFileSeq(final Integer fileSeq) {
        this.fileSeq = fileSeq;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public String getFileNameExt() {
        return this.fileNameExt;
    }

    public void setFileNameExt(final String fileNameExt) {
        this.fileNameExt = fileNameExt;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(final String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdated() {
        return this.updated;
    }

    public void setUpdated(final LocalDateTime updated) {
        this.updated = updated;
    }

}
