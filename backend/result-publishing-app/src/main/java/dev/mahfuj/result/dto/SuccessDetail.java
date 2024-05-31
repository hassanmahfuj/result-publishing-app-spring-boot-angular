package dev.mahfuj.result.dto;

import org.springframework.http.HttpStatus;

public class SuccessDetail {
    private int status = 200;
    private String detail;
    private Object data;

    public SuccessDetail(int status, String detail, Object data) {
        this.status = status;
        this.detail = detail;
        this.data = data;
    }

    public SuccessDetail(HttpStatus httpStatus, String detail) {
        this.status = httpStatus.value();
        this.detail = detail;
    }

    public SuccessDetail(String detail) {
        this.detail = detail;
    }

    public SuccessDetail(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
