package com.github.bestheroz.sample.web.tablevo.samplefilemst;

import org.joda.time.LocalDateTime;

import java.io.Serializable;
import java.util.Arrays;

@SuppressWarnings("ALL")
public class TableSampleFileMstVO implements Serializable {
    private Integer fileSeq;
    private String fileName;
    private String fileNameExt;
    private String mimeType;
    private Byte[] fileData; // Arrays.copyOf(value, value.length)
    private String createdBy;
    private LocalDateTime created;
    private String updatedBy;
    private LocalDateTime updated;

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

    public Byte[] getFileData() {
        return Arrays.copyOf(this.fileData, this.fileData.length);
    }

    public void setFileData(final Byte[] fileData) {
        this.fileData = Arrays.copyOf(fileData, fileData.length);
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(final LocalDateTime created) {
        this.created = created;
    }

    @SuppressWarnings("unused")
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(final String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdated() {
        return this.updated;
    }

    @SuppressWarnings("unused")
    public void setUpdated(final LocalDateTime updated) {
        this.updated = updated;
    }

}
