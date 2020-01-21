package org.worldbuild.kafka.modal;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private HttpStatus status=HttpStatus.OK;
    private int code=HttpStatus.OK.value();
    private String msg;
    private T data;

    public Response() {
    }

    public Response(T data) {
        this.data = data;
    }

    public Response(HttpStatus status,String msg) {
        this.status = status;
        this.code=status.value();
        this.msg=msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
