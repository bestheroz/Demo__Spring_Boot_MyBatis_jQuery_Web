package com.github.bestheroz.standard.common.util;

import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.tika.Tika;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);
    private static final String FILE_ROOT_PATH = "/workspace/uploadRootPath/";
    private static final String STR_DOT = ".";
    private static final String STR_INFO_MESSAGE = "Target for uploading file : {}";
    private static final String STR_UNDERLINE = "_";
    private static final Tika TIKA_INSTANCE = new Tika();

    protected FileUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean deleteDirectory(final File file) {
        forceDelete(file);
        LOGGER.info("Target for deleting dir : {}", file.getAbsolutePath());
        return true;
    }

    public static void deleteDirectory(final String filePath) {
        deleteFile(getFile(filePath));
    }

    public static void deleteFile(final File file) {
        forceDelete(file);
        LOGGER.info("Target for deleting file : {}", file.getAbsolutePath());
    }

    public static void deleteFile(final String filePath) {
        deleteFile(getFile(filePath));
    }

    private static File forceDelete(final File file) {
        try {
            org.apache.commons.io.FileUtils.forceDelete(file);
        } catch (final IOException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            // throw new CommonResponseException(e);
        }
        return file;
    }

    public static String getEncodedFileName(final HttpServletRequest request, final String fileName) {
        try {
            final String header = request.getHeader("User-Agent");

            String encodedFilename;
            if (StringUtils.contains(header, "MSIE")) {
                encodedFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8.displayName()).replaceAll("\\+", "%20");
            } else if (StringUtils.contains(header, "Trident")) {
                encodedFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8.displayName()).replaceAll("\\+", "%20");
            } else if (StringUtils.contains(header, "Chrome")) {
                final StringBuilder sb = new StringBuilder();
                for (int i = 0; i < fileName.length(); i++) {
                    final char c = fileName.charAt(i);
                    if (c > '~') {
                        sb.append(URLEncoder.encode("" + c, StandardCharsets.UTF_8.displayName()));
                    } else {
                        sb.append(c);
                    }
                }
                encodedFilename = sb.toString();
            } else {
                encodedFilename = "\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"";
                if (StringUtils.contains(header, "Opera")) {
                    // } else if (StringUtils.contains(header, "Safari")) {
                    // encodedFilename = "\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"";
                    // encodedFilename = URLDecoder.decode(encodedFilename, StandardCharsets.UTF_8.displayName());
                } else {
                    encodedFilename = URLDecoder.decode(encodedFilename, StandardCharsets.UTF_8.displayName());
                }
            }
            return encodedFilename;
        } catch (final UnsupportedEncodingException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new BusinessException(e);
        }
    }

    public static File getFile(final String filePath) {
        final String path = RegExUtils.replaceAll(getFileRoot() + filePath, "\\\\", "/").replaceAll("//", "/");
        final File file = org.apache.commons.io.FileUtils.getFile(path);
        if (file.isDirectory() && !StringUtils.endsWith(path, "/")) {
            LOGGER.warn("{} : {}", path, ExceptionCode.FAIL_DIR_PATH_MUST_ENDS_WITH_SLASH.toString());
            throw new BusinessException(ExceptionCode.FAIL_DIR_PATH_MUST_ENDS_WITH_SLASH);
        }
        return file;
    }

    public static File getDirectory(final String dirPath) {
        final String path = RegExUtils.replaceAll(getFileRoot() + dirPath, "\\\\", "/").replaceAll("//", "/");
        if (!StringUtils.endsWith(path, "/")) {
            LOGGER.warn("{} : {}", path, ExceptionCode.FAIL_DIR_PATH_MUST_ENDS_WITH_SLASH.toString());
            throw new BusinessException(ExceptionCode.FAIL_DIR_PATH_MUST_ENDS_WITH_SLASH);
        }
        final File file = getFile(path);
        if (!NullUtils.exists(file)) {
            try {
                org.apache.commons.io.FileUtils.forceMkdir(file);
            } catch (final IOException e) {
                LOGGER.warn(ExceptionUtils.getStackTrace(e));
                // ignored
            }
        }
        return file;
    }

    private static String getFileRoot() {
        if (StringUtils.containsIgnoreCase(System.getProperty("os.name"), "win")) {
            return "C:" + FILE_ROOT_PATH;
        } else {
            return FILE_ROOT_PATH;
        }
    }

    public static boolean isExistsFile(final String filePath) {
        return NullUtils.exists(getFile(filePath));
    }

    public static List<File> uploadAllFiles(final MultipartHttpServletRequest mRequest, final String targetDirPath) {
        final Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        if (NullUtils.size(fileMap) < 1) {
            return null;
        }
        getDirectory(targetDirPath);
        final Iterator<String> fileNames = mRequest.getFileNames();
        final List<File> savedFiles = new ArrayList<>();
        while (NullUtils.hasNext(fileNames)) {
            final MultipartFile multipartFile = fileMap.get(fileNames.next());
            validateFile(multipartFile);

            final File file = uploadMultipartFile(targetDirPath, multipartFile);
            savedFiles.add(file);
            LOGGER.info(STR_INFO_MESSAGE, file.getAbsolutePath());
        }
        return savedFiles;
    }

    private static File uploadMultipartFile(final String targetDirPath, final MultipartFile multipartFile) {
        final StringBuilder fileName = new StringBuilder(80);
        fileName.append(DateTime.now().toString(DateUtils.YYYYMMDDHHMMSS)).append(STR_UNDERLINE).append(DigestUtils.md5Hex(multipartFile.getOriginalFilename()));
        if (StringUtils.isNotEmpty(getExtension(multipartFile))) {
            fileName.append(STR_DOT).append(getExtension(multipartFile));
        }
        final File file = getFile(targetDirPath + "/" + fileName);
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), file);
        } catch (final IOException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new BusinessException(e);
        }
        return file;
    }

    public static File uploadFile(final MultipartFile multipartFile, final String targetDirPath) {
        if (NullUtils.isEmpty(multipartFile)) {
            return null;
        }
        validateFile(multipartFile);
        getDirectory(targetDirPath);
        final File file = uploadMultipartFile(targetDirPath, multipartFile);
        LOGGER.info(STR_INFO_MESSAGE, file.getAbsolutePath());
        return file;
    }

    // 업로드 하려는 파일의 검증(MultipartFile 이용)
    public static void validateFile(final MultipartFile multipartFile) {
        if (FileType.ILLEGAL.extList.contains(getExtension(multipartFile))) {
            LOGGER.warn("{}{}", ExceptionCode.FAIL_FILE_SIZE.toString(), multipartFile.getOriginalFilename());
            throw new BusinessException(ExceptionCode.FAIL_FILE_SIZE);
        }
        if (FileType.ILLEGAL.mimeTypeList.contains(getMimeType(multipartFile))) {
            LOGGER.warn("{}{}", ExceptionCode.FAIL_FILE_MIMETYPE.toString(), multipartFile.getOriginalFilename());
            throw new BusinessException(ExceptionCode.FAIL_FILE_MIMETYPE);
        }
    }

    // 업로드된 파일의 검증(File 이용)
    public static void validateFile(final File file) {
        if (FileType.ILLEGAL.extList.contains(getExtension(file))) {
            LOGGER.warn("{}{}", ExceptionCode.FAIL_FILE_SIZE.toString(), file.getAbsolutePath());
            throw new BusinessException(ExceptionCode.FAIL_FILE_SIZE);
        }
        if (FileType.ILLEGAL.mimeTypeList.contains(getMimeType(file))) {
            LOGGER.warn("{}{}", ExceptionCode.FAIL_FILE_MIMETYPE.toString(), file.getAbsolutePath());
            throw new BusinessException(ExceptionCode.FAIL_FILE_MIMETYPE);
        }
    }

    // 업로드 하려는 파일의 검증(MultipartFile 이용)
    public static void validateFile(final MultipartFile multipartFile, final FileType fileType) {
        try {
            if (!fileType.extList.contains(getExtension(multipartFile))) {
                LOGGER.warn("{}{}", ExceptionCode.FAIL_FILE_SIZE.toString(), multipartFile.getOriginalFilename());
                throw new BusinessException(ExceptionCode.FAIL_FILE_SIZE);
            }
            if (!fileType.mimeTypeList.contains(getMimeType(multipartFile))) {
                LOGGER.warn("{}{}", ExceptionCode.FAIL_FILE_MIMETYPE.toString(), multipartFile.getOriginalFilename());
                throw new BusinessException(ExceptionCode.FAIL_FILE_MIMETYPE);
            }

            validateFile(multipartFile);
        } catch (final BusinessException e) {
            throw setResultCodeByFileType(e, fileType);
        }
    }

    // 업로드된 파일의 검증(File 이용)
    public static void validateFile(final File file, final FileType fileType) {
        try {
            if (!fileType.extList.contains(getExtension(file))) {
                LOGGER.warn("{}{}", ExceptionCode.FAIL_FILE_SIZE.toString(), file.getAbsolutePath());
                throw new BusinessException(ExceptionCode.FAIL_FILE_SIZE);
            }
            if (!fileType.mimeTypeList.contains(getMimeType(file))) {
                LOGGER.warn("{}{}", ExceptionCode.FAIL_FILE_MIMETYPE.toString(), file.getAbsolutePath());
                throw new BusinessException(ExceptionCode.FAIL_FILE_MIMETYPE);
            }

            validateFile(file);
        } catch (final BusinessException e) {
            throw setResultCodeByFileType(e, fileType);
        }
    }

    private static BusinessException setResultCodeByFileType(final BusinessException e, final FileType fileType) {
        if (FileType.IMAGE.equals(fileType)) {
            if (e.isEquals(ExceptionCode.FAIL_FILE_SIZE)) {
                return new BusinessException(ExceptionCode.FAIL_IMAGE_EXT);
            } else if (e.isEquals(ExceptionCode.FAIL_FILE_MIMETYPE)) {
                return new BusinessException(ExceptionCode.FAIL_IMAGE_MIMETYPE);
            }
        } else if (FileType.EXCEL.equals(fileType)) {
            if (e.isEquals(ExceptionCode.FAIL_FILE_SIZE)) {
                return new BusinessException(ExceptionCode.FAIL_EXCEL_EXT);
            } else if (e.isEquals(ExceptionCode.FAIL_FILE_MIMETYPE)) {
                return new BusinessException(ExceptionCode.FAIL_EXCEL_MIMETYPE);
            }
        } else if (FileType.WORD.equals(fileType)) {
            if (e.isEquals(ExceptionCode.FAIL_FILE_SIZE)) {
                throw new BusinessException(ExceptionCode.FAIL_WORD_EXT);
            } else if (e.isEquals(ExceptionCode.FAIL_FILE_MIMETYPE)) {
                throw new BusinessException(ExceptionCode.FAIL_WORD_MIMETYPE);
            }
        } else if (FileType.PDF.equals(fileType)) {
            if (e.isEquals(ExceptionCode.FAIL_FILE_SIZE)) {
                throw new BusinessException(ExceptionCode.FAIL_PDF_EXT);
            } else if (e.isEquals(ExceptionCode.FAIL_FILE_MIMETYPE)) {
                throw new BusinessException(ExceptionCode.FAIL_PDF_MIMETYPE);
            }
        }
        return e;
    }

    public static boolean isFileType(final MultipartFile multipartFile, final FileType fileType) {
        return fileType.extList.contains(getExtension(multipartFile)) && fileType.mimeTypeList.contains(getMimeType(multipartFile));
    }

    public static boolean isFileType(final File file, final FileType fileType) {
        return fileType.extList.contains(getExtension(file)) && fileType.mimeTypeList.contains(getMimeType(file));
    }

    public static String getMimeType(final MultipartFile multipartFile) {
        try {
            if (multipartFile == null) {
                return null;
            }
            return TIKA_INSTANCE.detect(multipartFile.getBytes()).toLowerCase();
        } catch (final IOException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new BusinessException(e);
        }
    }

    public static String getMimeType(final File file) {
        try {
            if (file == null) {
                return null;
            }
            return TIKA_INSTANCE.detect(file).toLowerCase();
        } catch (final IOException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new BusinessException(e);
        }
    }

    public static String getFullName(final MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }
        return getFullName(multipartFile.getOriginalFilename());
    }

    public static String getFullName(final File file) {
        if (file == null) {
            return null;
        }
        return getFullName(file.getName());
    }

    public static String getFullName(final String fileName) {
        return FilenameUtils.getName(fileName);
    }

    public static String getBaseName(final MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }
        return getBaseName(multipartFile.getOriginalFilename());
    }

    public static String getBaseName(final File file) {
        if (file == null) {
            return null;
        }
        return getBaseName(file.getName());
    }

    public static String getBaseName(final String fileName) {
        return FilenameUtils.getBaseName(fileName);
    }

    public static String getExtension(final MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }
        return getExtension(multipartFile.getOriginalFilename());
    }

    public static String getExtension(final File file) {
        if (file == null) {
            return null;
        }
        return getExtension(file.getName());
    }

    public static String getExtension(final String fileName) {
        return FilenameUtils.getExtension(fileName).toLowerCase(Locale.getDefault());
    }

    public enum FileType {
        IMAGE(ImmutableSet.of("gif", "jpg", "jpeg", "tif", "tiff", "png", "bmp"), ImmutableSet.of("image/gif", "image/jpeg", "image/pjpeg", "image/tiff", "image/x-tiff", "image/png", "image/bmp")),

        EXCEL(ImmutableSet.of("xlsx", "xls"),
                ImmutableSet.of("application/excel", "application/vnd.ms-excel", "application/x-excel", "application/x-msexcel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")),

        WORD(ImmutableSet.of("docx", "doc", "dotx"), ImmutableSet.of("application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.template")),

        PDF(ImmutableSet.of("pdf"), ImmutableSet.of("application/pdf", "application/x-pdf")),

        ILLEGAL(ImmutableSet.of("exe", "sh", "csh", "ai"),
                ImmutableSet.of("application/octet-stream", "application/x-sh", "application/x-shar", "text/x-script.sh", "application/x-csh", "text/x-script.csh", "application/postscript"));

        private final Set<String> extList;
        private final Set<String> mimeTypeList;

        FileType(final Set<String> extList, final Set<String> mimeTypeList) {
            this.extList = extList;
            this.mimeTypeList = mimeTypeList;
        }
    }
}
