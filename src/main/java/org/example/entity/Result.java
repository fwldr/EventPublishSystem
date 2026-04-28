package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;//状态码 1失败 0成功
    private String message;//提示信息
    private T data;//数据


    public static <E> Result<E> success(E data) {
        return new Result<>(0, "success", data);

    }
    public static <E> Result<E> success(String message,E data) {
        return new Result<>(0, message, data);

    }
    public static Result success(){
        return new Result(0, "success", null);
    }

    public static Result success(String message){
        return new Result(0, message, null);
    }


    public static  Result error(String message) {
        return new Result(1, message, null);
    }




}
