package com.kadal.response;import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Response{
    public String message;
    public String status;
    public Object result;
}
