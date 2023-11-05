package com.yourcompany.taskapi.dto;

public class ServiceResponse<T> {
    private String status;
    private T data;
    public ServiceResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }
    public void setData(T data){
        this.data = data;
    }
    public T getData(){
        return data;
    }
}
